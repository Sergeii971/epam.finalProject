package com.verbovskiy.finalproject.controller.command.impl.jump;

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
import java.util.Optional;

/**
 * The type user management page command.
 *
 * @author Verbovskiy Sergei
 * @version 1.0
 */
public class UserManagementPageCommand implements ActionCommand {
    private final Logger logger = LogManager.getLogger(UserManagementPageCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserService service = new UserServiceImpl();
        String adminEmail = (String) session.getAttribute(RequestParameter.EMAIL);
        String page = PageType.ERROR.getPath();

        try {
            List<User> users = service.findAllUser();
            Optional<User> admin = service.findAdminByEmail(adminEmail);
            admin.ifPresent(users::remove);
            if (users.isEmpty()) {
                session.setAttribute(RequestParameter.IS_EMPTY, true);
            } else {
                session.setAttribute(RequestParameter.IS_EMPTY, false);
                int toIndex = Constant.NUMBER_OF_USER_PER_PAGE;
                if (users.size() < toIndex) {
                    toIndex = users.size();
                }
                session.setAttribute(AttributeKey.USER_LIST, users);
                session.setAttribute(AttributeKey.FROM_INDEX, 0);
                session.setAttribute(AttributeKey.TO_INDEX, toIndex);
                List<User> notConfirmedUsers = service.findNotConfirmedUsers();
                if (notConfirmedUsers.size() != 0) {
                    session.setAttribute(AttributeKey.IS_NOT_CONFIRMED_USER_EXIST, true);
                }
            }
            page = PageType.USER_MANAGEMENT.getPath();
        } catch (ServiceException e) {
        logger.log(Level.ERROR, e);
        }
        return page;
    }
}
