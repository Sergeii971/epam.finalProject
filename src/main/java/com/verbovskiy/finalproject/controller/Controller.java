package com.verbovskiy.finalproject.controller;

import com.verbovskiy.finalproject.controller.command.ActionCommand;
import com.verbovskiy.finalproject.controller.command.CommandProvider;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/controller", name = "servlet")
public class Controller extends HttpServlet {
    private static final String COMMAND_PARAMETER = "command";
    private static final String CURRENT_PAGE = "current_page";

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
        HttpSession session = request.getSession();
        session.setAttribute(CURRENT_PAGE, page);
        request.getRequestDispatcher(page).forward(request, response);
    }
}
