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

public class EmailConfirmationCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(EmailConfirmationCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String confirmationKey = request.getParameter(RequestParameter.CONFIRM);
        UserService service = new UserService();
        String page = PageType.ERROR.getPath();
        try {
            if (service.ConfirmUser(confirmationKey)) {
                HttpSession session = request.getSession();
                addComeBackPagePath(session, PageType.USER_INTERFACE.getPath());
                page = PageType.USER_INTERFACE.getPath();
            } else {

            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        return page;
    }
}
