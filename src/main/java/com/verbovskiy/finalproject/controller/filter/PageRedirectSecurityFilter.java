package com.verbovskiy.finalproject.controller.filter;

import com.verbovskiy.finalproject.controller.AttributeKey;
import com.verbovskiy.finalproject.controller.command.CommandParameter;
import com.verbovskiy.finalproject.controller.command.PageName;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@WebFilter( urlPatterns = {  "/jsp/*" })
public class PageRedirectSecurityFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession();
        String currentPage = (String) session.getAttribute(AttributeKey.CURRENT_PAGE);
        Boolean isActive = (Boolean) session.getAttribute(CommandParameter.IS_ACTIVE);
        Optional<PageName> page = PageName.findByPath(currentPage);

            if ((isActive == null) || ((page.isPresent()) && (page.get().isRequireAuthorization()) && (!isActive))) {
                session.setAttribute(AttributeKey.CURRENT_PAGE, PageName.AUTHORIZATION.getPath());
                session.setAttribute(CommandParameter.IS_ACTIVE, false);
                session.setAttribute(AttributeKey.LOCALE, AttributeKey.DEFAULT_LOCALE);
                RequestDispatcher dispatcher = httpRequest.getServletContext().
                        getRequestDispatcher(PageName.AUTHORIZATION.getPath());
                dispatcher.forward(httpRequest, httpResponse);
            }
        chain.doFilter(request, response);
    }
}
