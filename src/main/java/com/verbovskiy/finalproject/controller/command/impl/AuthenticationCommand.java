package com.verbovskiy.finalproject.controller.command.impl;

import com.verbovskiy.finalproject.controller.AttributeKey;
import com.verbovskiy.finalproject.controller.command.ActionCommand;
import com.verbovskiy.finalproject.controller.command.PageType;
import com.verbovskiy.finalproject.controller.command.RequestParameter;
import com.verbovskiy.finalproject.exception.ServiceException;
import com.verbovskiy.finalproject.model.service.UserService;
import com.verbovskiy.finalproject.model.service.impl.UserServiceImpl;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AuthenticationCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(AuthenticationCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        UserService service = new UserServiceImpl();
        String email = request.getParameter(RequestParameter.LOGIN);
        String password = request.getParameter(RequestParameter.PASSWORD);
        String page = PageType.ERROR.getPath();
        HttpSession session = request.getSession();

        try {
            if (service.verifyAccount(email, password)) {
                   if(!service.isBlocked(email) || service.isConfirmed(email)) {
                       session.setAttribute(RequestParameter.EMAIL, email);
                       if (service.isAdmin(email)) {
                           session.setAttribute(RequestParameter.IS_ADMIN, true);
                           page = PageType.ADMIN_INTERFACE.getPath();
                       } else {
                           session.setAttribute(RequestParameter.IS_ADMIN, false);
                           page = PageType.USER_INTERFACE.getPath();
                       }
                    addComeBackPagePath(session, page);
                } else {
                       request.setAttribute(RequestParameter.LOGIN, email);
                       request.setAttribute(AttributeKey.SUCCESSFUL_ACTIVATION, false);
                       page = PageType.AUTHORIZATION.getPath();
                }
            } else {
                request.setAttribute(RequestParameter.LOGIN, email);
                request.setAttribute(AttributeKey.SUCCESSFUL_AUTHENTICATION, false);
                page = PageType.AUTHORIZATION.getPath();
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        return page;
    }
}
