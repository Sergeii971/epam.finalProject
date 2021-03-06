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

/**
 * The type registration email confirmation command.
 *
 * @author Verbovskiy Sergei
 * @version 1.0
 */
public class RegistrationEmailConfirmationCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(RegistrationEmailConfirmationCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String confirmationKey = request.getParameter(RequestParameter.CONFIRM);
        UserService service = new UserServiceImpl();
        String page = PageType.ERROR.getPath();
        HttpSession session = request.getSession();
        try {
            if (service.ConfirmUser(confirmationKey)) {
                removeComeBackPagePath(session, PageType.USER_INTERFACE.getPath());
                page = PageType.USER_INTERFACE.getPath();
                session.removeAttribute(RequestParameter.NAME);
                session.removeAttribute(RequestParameter.SURNAME);
                session.removeAttribute(AttributeKey.SUCCESSFUL_EMAIL_CONFIRMATION);
                session.setAttribute(RequestParameter.IS_ADMIN, false);
            } else {
                request.setAttribute(AttributeKey.SUCCESSFUL_EMAIL_CONFIRMATION, false);
                page = PageType.CONFIRMATION.getPath();
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        return page;
    }
}
