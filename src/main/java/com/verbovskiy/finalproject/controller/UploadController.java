package com.verbovskiy.finalproject.controller;

import com.verbovskiy.finalproject.controller.command.PageType;
import com.verbovskiy.finalproject.controller.command.RequestParameter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

/**
 * The {@code UploadController} class represents file upload controller.
 *
 * @author Verbovskiy Sergei
 * @version 1.0
 */
@WebServlet(urlPatterns = "/upload_controller", name = "uploadController")
@MultipartConfig(maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50)
public class UploadController extends HttpServlet {
    private static final String UPLOAD_LOCATION = "upload.location";
    private static final String EXPANSION_START = ".";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String applicationFileDirectory = request.getServletContext().getInitParameter(UPLOAD_LOCATION);
        Path path = Paths.get(applicationFileDirectory);
        StringBuilder folderBuilder = new StringBuilder();

        folderBuilder.append(applicationFileDirectory);
        folderBuilder.append(File.separator);
        Path fullFolderPath = Paths.get(folderBuilder.toString());

        if (Files.notExists(path)) {
            Files.createDirectory(path);
        }
        request.getParts().forEach(part -> {
            try {
                String fileName = part.getSubmittedFileName();
                if (fileName.isEmpty()) {
                 request.setAttribute(RequestParameter.IS_IMAGE_SELECTED, false);
                } else {
                    String randFilename = UUID.randomUUID() + fileName.substring(fileName.lastIndexOf(EXPANSION_START));
                    StringBuilder imagePathBuilder = new StringBuilder();

                    imagePathBuilder.append(fullFolderPath);
                    imagePathBuilder.append(File.separator);
                    imagePathBuilder.append(randFilename);
                    String imagePath = imagePathBuilder.toString();

                    part.write(imagePath);
                    request.setAttribute(RequestParameter.IS_IMAGE_LOADED, true);
                    HttpSession session = request.getSession();
                    session.setAttribute(RequestParameter.IMAGE_NAME, randFilename);
                }
            } catch (IOException e) {
                request.setAttribute(RequestParameter.IS_IMAGE_LOADED, false);
            }
        });
        request.getRequestDispatcher(PageType.ADD_CAR.getPath()).forward(request, response);
    }
}
