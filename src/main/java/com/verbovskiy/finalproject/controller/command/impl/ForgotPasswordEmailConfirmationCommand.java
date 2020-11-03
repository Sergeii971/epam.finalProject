package com.verbovskiy.finalproject.controller.command.impl;

import com.verbovskiy.finalproject.controller.AttributeKey;
import com.verbovskiy.finalproject.controller.command.ActionCommand;
import com.verbovskiy.finalproject.controller.command.PageType;
import com.verbovskiy.finalproject.controller.command.RequestParameter;
import com.verbovskiy.finalproject.exception.ServiceException;
import com.verbovskiy.finalproject.model.service.UserService;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ForgotPasswordEmailConfirmationCommand implements ActionCommand {
    private final Logger logger = LogManager.getLogger(ForgotPasswordEmailConfirmationCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String confirmationKey = request.getParameter(RequestParameter.CONFIRM);
        UserService service = new UserService();
        String page = PageType.ERROR.getPath();
        HttpSession session = request.getSession();

        try {
            if (service.ConfirmUser(confirmationKey)) {
                addComeBackPagePath(session, PageType.INPUT_NEW_PASSWORD_IN_FORGOT_PASSWORD.getPath());
                page = PageType.INPUT_NEW_PASSWORD_IN_FORGOT_PASSWORD.getPath();
            } else {
                request.setAttribute(AttributeKey.SUCCESSFUL_EMAIL_CONFIRMATION, false);
                page = PageType.FORGOT_PASSWORD.getPath();
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        return page;
    }
}
