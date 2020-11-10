package com.verbovskiy.finalproject.controller;

import com.verbovskiy.finalproject.controller.command.ActionCommand;
import com.verbovskiy.finalproject.controller.command.CommandProvider;
import com.verbovskiy.finalproject.controller.command.RequestParameter;
import com.verbovskiy.finalproject.model.connection.ConnectionPool;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/controller", name = "servlet")
public class Controller extends HttpServlet {

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
        ActionCommand command = CommandProvider.defineCommand(request.getParameter(RequestParameter.COMMAND_NAME));
        String page = command.execute(request);
        HttpSession session = request.getSession();
        session.setAttribute(AttributeKey.CURRENT_PAGE, page);
        RequestAttributeHandler handler = new RequestAttributeHandler();
        handler.setAttributes(request);
        session.setAttribute(AttributeKey.REQUEST_ATTRIBUTE_HANDLER, handler);
        request.getRequestDispatcher(page).forward(request, response);
    }

    @Override
    public void destroy() {
        ConnectionPool.getInstance().destroyPool();
    }
}
