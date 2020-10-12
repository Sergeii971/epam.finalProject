package com.verbovskiy.finalproject.controller.command.impl;

import com.verbovskiy.finalproject.controller.command.ActionCommand;
import com.verbovskiy.finalproject.controller.command.CommandParameter;
import com.verbovskiy.finalproject.controller.command.PageName;
import com.verbovskiy.finalproject.exception.ServiceException;
import com.verbovskiy.finalproject.model.service.UserService;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class EmailConfirmationCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(EmailConfirmationCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String confirmationKey = request.getParameter(CommandParameter.CONFIRM_PARAMETER);
        UserService service = new UserService();
        String page = PageName.ERROR.getPath();
        try {
            if (service.ConfirmUser(confirmationKey)) {
                page = PageName.USER_INTERFACE.getPath();
            } else {

            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        return page;
    }
}
