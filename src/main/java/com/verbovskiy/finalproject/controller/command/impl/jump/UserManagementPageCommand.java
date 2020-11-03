package com.verbovskiy.finalproject.controller.command.impl.jump;

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

public class UserManagementPageCommand implements ActionCommand {
    private final Logger logger = LogManager.getLogger(UserManagementPageCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserService service = new UserService();
        String page = PageType.ERROR.getPath();

        try {
            List<User> users = service.findAllUser();
            session.setAttribute(AttributeKey.USER_LIST, users);
            List<User> notConfirmedUsers = service.findNotConfirmedUsers();
            if (notConfirmedUsers.size() != 0) {
                session.setAttribute(AttributeKey.IS_NOT_CONFIRMED_USER_EXIST, true);
            }
            addComeBackPagePath(session, PageType.USER_MANAGEMENT.getPath());

            page = PageType.USER_MANAGEMENT.getPath();
        } catch (ServiceException e) {
        logger.log(Level.ERROR, e);
        }
        return page;
    }
}
