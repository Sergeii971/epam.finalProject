package com.verbovskiy.finalproject.controller.command.impl;

import com.verbovskiy.finalproject.controller.AttributeKey;
import com.verbovskiy.finalproject.controller.command.ActionCommand;
import com.verbovskiy.finalproject.controller.command.PageType;
import com.verbovskiy.finalproject.controller.command.RequestParameter;
import com.verbovskiy.finalproject.exception.ServiceException;
import com.verbovskiy.finalproject.model.entity.User;
import com.verbovskiy.finalproject.model.service.UserService;
import com.verbovskiy.finalproject.model.service.impl.UserServiceImpl;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * The type change user block status command.
 *
 * @author Verbovskiy Sergei
 * @version 1.0
 */
public class ChangeUserBlockStatusCommand implements ActionCommand {
    private final Logger logger = LogManager.getLogger(ChangeUserBlockStatusCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserService service = new UserServiceImpl();
        String statusStringFormat = request.getParameter(RequestParameter.USER_STATUS);
        boolean userBlockStatus = Boolean.parseBoolean(statusStringFormat);
        int userIndex = Integer.parseInt(request.getParameter(RequestParameter.USER_INDEX));
        String page = PageType.ERROR.getPath();

        try {
            List<User> users = (List<User>) session.getAttribute(AttributeKey.USER_LIST);
            User user = users.get(userIndex);
            service.updateUserBlockStatus(user.getEmail(), userBlockStatus);
            user.getAccount().setBlocked(userBlockStatus);
            session.setAttribute(AttributeKey.USER_LIST, users);
            page = PageType.USER_MANAGEMENT.getPath();
        } catch (ServiceException e) {
            logger.log(Level.ERROR,  e);
        }
        return page;
    }
}
