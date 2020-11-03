package com.verbovskiy.finalproject.controller.filter;

import com.verbovskiy.finalproject.controller.AttributeKey;
import com.verbovskiy.finalproject.controller.command.PageType;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;

@WebFilter( urlPatterns = {  "/jsp/*" })
public class PageRedirectSecurityFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession();
        String currentPage = httpRequest.getServletPath();
        Optional<PageType> page = PageType.findByPath(currentPage);

        if (((!page.isPresent()) || (!page.get().isLinkAvailable())) && (!httpResponse.isCommitted())) {
            String jumpPage = PageType.AUTHORIZATION.getPath();
            session.setAttribute(AttributeKey.CURRENT_PAGE, jumpPage);
            session.setAttribute(AttributeKey.COME_BACK_PAGES, new HashMap<>());
            session.setAttribute(AttributeKey.LOCALE, AttributeKey.DEFAULT_LOCALE);
            RequestDispatcher dispatcher = httpRequest.getServletContext().
                    getRequestDispatcher(jumpPage);
            dispatcher.forward(httpRequest, httpResponse);
       }
        chain.doFilter(request, response);
    }
}
