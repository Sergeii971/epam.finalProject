package com.verbovskiy.finalproject.controller;

import com.verbovskiy.finalproject.controller.command.ActionCommand;
import com.verbovskiy.finalproject.controller.command.CommandProvider;
import com.verbovskiy.finalproject.model.connection.ConnectionPool;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/controller", name = "servlet")
public class Controller extends HttpServlet {
    private static final String COMMAND_NAME = "command";

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
        ActionCommand command = commandProvider.defineCommand(request.getParameter(COMMAND_NAME));
        String page = command.execute(request);
        HttpSession session = request.getSession();
        session.setAttribute(AttributeKey.CURRENT_PAGE, page);
        request.getRequestDispatcher(page).forward(request, response);
    }

    @Override
    public void destroy() {
        ConnectionPool.getInstance().destroyPool();
    }
}
