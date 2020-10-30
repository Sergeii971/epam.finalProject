package com.verbovskiy.finalproject.controller.command.impl;

import com.verbovskiy.finalproject.controller.AttributeKey;
import com.verbovskiy.finalproject.controller.command.ActionCommand;
import com.verbovskiy.finalproject.controller.command.RequestParameter;
import com.verbovskiy.finalproject.controller.command.PageType;
import com.verbovskiy.finalproject.exception.ServiceException;
import com.verbovskiy.finalproject.model.service.UserService;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

public class AuthenticationCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(AuthenticationCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute(RequestParameter.IS_ACTIVE, false);
        UserService userService = new UserService();
        String accountLogin = request.getParameter(RequestParameter.LOGIN);
        String accountPassword = request.getParameter(RequestParameter.PASSWORD);
        String page = PageType.ERROR.getPath();
        HttpSession session = request.getSession();
        String currentPage = (String) session.getAttribute(AttributeKey.CURRENT_PAGE);

        try {
            if (userService.verifyAccount(accountLogin, accountPassword)) {
                   if(!userService.isBlocked(accountLogin)) {
                    session.setAttribute(RequestParameter.IS_ACTIVE, true);
                    Map<String, String> pages = (Map<String, String>) session.getAttribute(AttributeKey.COME_BACK_PAGES);
                    if (userService.isAdmin(accountLogin)) {
                        page = PageType.ADMIN_INTERFACE.getPath();
                        session.setAttribute(RequestParameter.IS_ADMIN, true);
                    } else {
                        page = PageType.USER_INTERFACE.getPath();
                        session.setAttribute(RequestParameter.IS_ADMIN, false);
                    }
                    pages.put(page, currentPage);
                    session.setAttribute(AttributeKey.COME_BACK_PAGES, pages);
                } else {
                       session.setAttribute(RequestParameter.LOGIN, accountLogin);
                       session.setAttribute(AttributeKey.SUCCESSFUL_ACTIVATION, false);
                       page = PageType.AUTHORIZATION.getPath();
                }
            } else {
                session.setAttribute(RequestParameter.LOGIN, accountLogin);
                session.setAttribute(AttributeKey.SUCCESSFUL_AUTHENTICATION, false);
                page = PageType.AUTHORIZATION.getPath();
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        return page;
    }
}
