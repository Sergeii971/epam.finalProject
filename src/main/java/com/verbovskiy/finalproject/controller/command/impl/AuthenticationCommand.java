package com.verbovskiy.finalproject.controller.command.impl;

import com.verbovskiy.finalproject.controller.AttributeKey;
import com.verbovskiy.finalproject.controller.command.ActionCommand;
import com.verbovskiy.finalproject.controller.command.CommandParameter;
import com.verbovskiy.finalproject.controller.command.PageName;
import com.verbovskiy.finalproject.exception.ServiceException;
import com.verbovskiy.finalproject.model.service.UserService;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AuthenticationCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(AuthenticationCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute(CommandParameter.IS_ACTIVE, false);
        UserService userService = new UserService();
        String accountLogin = request.getParameter(CommandParameter.LOGIN_PARAMETER);
        String accountPassword = request.getParameter(CommandParameter.PASSWORD_PARAMETER);
        String page = PageName.ERROR.getPath();
        HttpSession session = request.getSession();

        try {
            if (userService.verifyAccount(accountLogin, accountPassword)) {
                   if(!userService.isBlocked(accountLogin)) {
                    session.setAttribute(CommandParameter.IS_ACTIVE, true);
                    if (userService.isAdmin(accountLogin)) {
                        page = PageName.ADMIN_INTERFACE.getPath();
                    } else {
                        page = PageName.USER_INTERFACE.getPath();
                    }
                } else {
                       session.setAttribute(CommandParameter.LOGIN_PARAMETER, accountLogin);
                       session.setAttribute(AttributeKey.SUCCESSFUL_ACTIVATION, false);
                       page = PageName.AUTHORIZATION.getPath();
                }
            } else {
                session.setAttribute(CommandParameter.LOGIN_PARAMETER, accountLogin);
                session.setAttribute(AttributeKey.SUCCESSFUL_AUTHENTICATION, false);
                page = PageName.AUTHORIZATION.getPath();
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        return page;
    }
}
