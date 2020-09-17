package com.verbovskiy.finalproject.controller.command.impl;

import com.verbovskiy.finalproject.controller.command.ActionCommand;
import com.verbovskiy.finalproject.controller.command.PageName;
import com.verbovskiy.finalproject.exception.ServiceException;
import com.verbovskiy.finalproject.model.service.UserService;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class AuthorizationCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(AuthorizationCommand.class);
    private static final String LOGIN_PARAMETER = "login";
    private static final String PASSWORD_PARAMETER = "password";

    @Override
    public String execute(HttpServletRequest request) {
        UserService userService = new UserService();
        String userLogin = request.getParameter(LOGIN_PARAMETER);
        String userPassword = request.getParameter(PASSWORD_PARAMETER);
        String page = PageName.ERROR.getPath();

        try {
            if (userService.verifyUser(userLogin, userPassword)) {
                if (userService.isAdmin(userLogin)) {
                    page = PageName.ADMIN_INTERFACE.getPath();
                } else {
                    page = PageName.USER_INTERFACE.getPath();
                }
            } else {
                page = PageName.AUTHORIZATION.getPath();
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        return page;
    }
}
