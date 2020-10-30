package com.verbovskiy.finalproject.controller.filter;

import com.verbovskiy.finalproject.controller.AttributeKey;
import com.verbovskiy.finalproject.controller.command.RequestParameter;
import com.verbovskiy.finalproject.controller.command.PageType;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
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
        Boolean isActive = (Boolean) session.getAttribute(RequestParameter.IS_ACTIVE);
        Boolean isAdmin = (Boolean) session.getAttribute(RequestParameter.IS_ADMIN);
        Optional<PageType> page = PageType.findByPath(currentPage);

//            if ((isActive == null) || (!page.isPresent()) || (isAdmin == null)
//                    || ((isAdmin) && (page.get().isAdminPage())) || ((!isAdmin) && (!page.get().isAdminPage())) ||
//                    ((page.get().isRequireAuthorization()) && (!isActive))) {
//                if (page.isPresent()) {
//                    session.setAttribute(AttributeKey.CURRENT_PAGE, currentPage);
//                } else {
//                    session.setAttribute(AttributeKey.CURRENT_PAGE, PageType.AUTHORIZATION.getPath());
//                }
//                session.setAttribute(RequestParameter.IS_ACTIVE, false);
//                session.setAttribute(AttributeKey.LOCALE, AttributeKey.DEFAULT_LOCALE);
//                session.setAttribute(RequestParameter.IS_ADMIN, false);
                RequestDispatcher dispatcher = httpRequest.getServletContext().
                        getRequestDispatcher((String) session.getAttribute(AttributeKey.CURRENT_PAGE));
                dispatcher.forward(httpRequest, httpResponse);
           //}

        chain.doFilter(request, response);
    }
}
