package com.verbovskiy.finalproject.controller.filter;

import com.verbovskiy.finalproject.controller.AttributeKey;
import com.verbovskiy.finalproject.controller.command.CommandType;
import com.verbovskiy.finalproject.controller.command.PageType;
import com.verbovskiy.finalproject.controller.command.RequestParameter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

/**
 * The type Servlet security filter.
 *
 * @author Verbovskiy Sergei
 * @version 1.0
 */
@WebFilter(filterName = "ServletSecurityFilter", urlPatterns = { "/controller" })
public class ServletSecurityFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {
        boolean result;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String commandName = request.getParameter(RequestParameter.COMMAND_NAME);
        if (commandName == null) {
            result = false;
        } else {
            CommandType[] commands = CommandType.values();
            result = Arrays.stream(commands).anyMatch(value -> value.name().equals(commandName.toUpperCase()));
        }
        if (!result) {
            HttpSession session = request.getSession();
            session.setAttribute(AttributeKey.CURRENT_PAGE, PageType.AUTHORIZATION.getPath());
            session.setAttribute(RequestParameter.EMAIL, RequestParameter.EMPTY_VALUE);
            session.setAttribute(RequestParameter.IS_ADMIN, false);
            session.setAttribute(AttributeKey.COME_BACK_PAGES, new HashMap<>());
            RequestDispatcher dispatcher = request.getServletContext().
                    getRequestDispatcher(PageType.AUTHORIZATION.getPath());
            dispatcher.forward(request, response);
        }
        if (!response.isCommitted()) {
            chain.doFilter(request, response);
        }
    }
}
