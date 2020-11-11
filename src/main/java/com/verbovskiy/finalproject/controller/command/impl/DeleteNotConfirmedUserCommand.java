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

public class DeleteNotConfirmedUserCommand implements ActionCommand {
    private final Logger logger = LogManager.getLogger(DeleteNotConfirmedUserCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserService service = new UserServiceImpl();
        int userIndex = Integer.parseInt(request.getParameter(RequestParameter.USER_INDEX));
        String page = PageType.ERROR.getPath();

        try {
            List<User> users = (List<User>) session.getAttribute(AttributeKey.USER_LIST);
            User user = users.get(userIndex);
            service.remove(user.getEmail());
            users.remove(user);
            int toIndex = (int) session.getAttribute(AttributeKey.TO_INDEX);
            if (users.size() < toIndex) {
                toIndex = users.size();
                session.setAttribute(AttributeKey.TO_INDEX, toIndex);
            }
            session.setAttribute(AttributeKey.USER_LIST, users);
            List<User> isNotConfirmedUsers = service.findNotConfirmedUsers();
            if (isNotConfirmedUsers.size() == 0) {
                session.setAttribute(AttributeKey.IS_NOT_CONFIRMED_USER_EXIST, false);
            }
            page = PageType.USER_MANAGEMENT.getPath();
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Error while updating user status", e);
        }
        return page;
    }
}
