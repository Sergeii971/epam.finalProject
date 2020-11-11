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

public class CheckNewPasswordCommand implements ActionCommand {
    private final Logger logger = LogManager.getLogger(CheckNewPasswordCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        UserService service = new UserServiceImpl();
        String page = PageType.ERROR.getPath();
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute(RequestParameter.EMAIL);
        String password = request.getParameter(RequestParameter.PASSWORD);
        String passwordConfirmation = request.getParameter(RequestParameter.PASSWORD_CONFIRMATION);

        try {
            if (service.updatePassword(email, password, passwordConfirmation)) {
                session.removeAttribute(RequestParameter.EMAIL);
                if (service.isAdmin(email)) {
                    removeComeBackPagePath(session, PageType.ADMIN_INTERFACE.getPath());
                    page = PageType.ADMIN_INTERFACE.getPath();
                } else {
                    removeComeBackPagePath(session, PageType.USER_INTERFACE.getPath());
                    page = PageType.USER_INTERFACE.getPath();
                }
            } else {
                request.setAttribute(AttributeKey.SUCCESSFUL_PASSWORD_CHANGE, false);
                page = PageType.INPUT_NEW_PASSWORD_IN_FORGOT_PASSWORD.getPath();
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        return page;
    }
}
