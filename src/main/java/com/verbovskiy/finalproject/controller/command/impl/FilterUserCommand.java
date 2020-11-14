package com.verbovskiy.finalproject.controller.command.impl;

import com.verbovskiy.finalproject.controller.AttributeKey;
import com.verbovskiy.finalproject.controller.command.ActionCommand;
import com.verbovskiy.finalproject.controller.command.Constant;
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
 * The type filter user command.
 *
 * @author Verbovskiy Sergei
 * @version 1.0
 */
public class FilterUserCommand implements ActionCommand {
    private final Logger logger = LogManager.getLogger(FilterUserCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute(RequestParameter.IS_EMPTY, false);
        String userStatus = request.getParameter(RequestParameter.USER_STATUS);
        String page = PageType.ERROR.getPath();
        try {
            UserService service = new UserServiceImpl();
            List<User> users = service.filterUsers(userStatus);
            int fromIndex = 0;
            int toIndex = Constant.NUMBER_OF_USER_PER_PAGE;
            if (users.isEmpty()) {
                session.setAttribute(RequestParameter.IS_EMPTY, true);
            }
            if (users.size() < toIndex) {
                toIndex = users.size();
            }
            session.setAttribute(AttributeKey.USER_LIST, users);
            session.setAttribute(AttributeKey.TO_INDEX, toIndex);
            session.setAttribute(AttributeKey.FROM_INDEX, fromIndex);
            page = PageType.USER_MANAGEMENT.getPath();
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        return page;
    }
}
