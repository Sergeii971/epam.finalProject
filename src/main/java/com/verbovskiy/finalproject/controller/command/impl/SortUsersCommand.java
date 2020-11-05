package com.verbovskiy.finalproject.controller.command.impl;

import com.verbovskiy.finalproject.controller.AttributeKey;
import com.verbovskiy.finalproject.controller.command.ActionCommand;
import com.verbovskiy.finalproject.controller.command.PageType;
import com.verbovskiy.finalproject.controller.command.RequestParameter;
import com.verbovskiy.finalproject.exception.ServiceException;
import com.verbovskiy.finalproject.model.entity.User;
import com.verbovskiy.finalproject.model.service.UserService;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class SortUsersCommand implements ActionCommand {
    private final Logger logger = LogManager.getLogger(SortUsersCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String sortType = request.getParameter(RequestParameter.SORT_TYPE);
        String page = PageType.ERROR.getPath();
        List<User> users = (List<User>) session.getAttribute(AttributeKey.USER_LIST);

        try {
            UserService service = new UserService();
            service.sortUsers(sortType, users);
            session.setAttribute(AttributeKey.USER_LIST, users);
            page = PageType.USER_MANAGEMENT.getPath();
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        return page;
    }
}
