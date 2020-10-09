package com.verbovskiy.finalproject.controller;

import com.verbovskiy.finalproject.controller.command.ActionCommand;
import com.verbovskiy.finalproject.controller.command.CommandProvider;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/controller", name = "servlet")
public class Controller extends HttpServlet {
    private static final String COMMAND_PARAMETER = "command";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        CommandProvider commandProvider = new CommandProvider();
        ActionCommand command = commandProvider.defineCommand(request.getParameter(COMMAND_PARAMETER));
        String page = command.execute(request);
        request.getRequestDispatcher(page).forward(request, response);
    }
}
