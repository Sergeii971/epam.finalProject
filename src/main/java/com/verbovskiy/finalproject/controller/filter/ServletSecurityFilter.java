package com.verbovskiy.finalproject.controller.filter;

import com.verbovskiy.finalproject.controller.command.CommandType;
import com.verbovskiy.finalproject.controller.command.PageType;
import com.verbovskiy.finalproject.controller.command.RequestParameter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@WebFilter(filterName = "ServletSecurityFilter", urlPatterns = { "/controller" })
public class ServletSecurityFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String commandName = request.getParameter(RequestParameter.COMMAND_NAME);
        CommandType[] commands = CommandType.values();
        boolean isPresent = Arrays.stream(commands).anyMatch(value -> value.name().equals(commandName.toUpperCase()));
        if (!isPresent) {
            RequestDispatcher dispatcher = request.getServletContext().
                    getRequestDispatcher(PageType.AUTHORIZATION.getPath());
            dispatcher.forward(request, response);
        }
        if (!response.isCommitted()) {
            chain.doFilter(request, response);
        }
    }
}
