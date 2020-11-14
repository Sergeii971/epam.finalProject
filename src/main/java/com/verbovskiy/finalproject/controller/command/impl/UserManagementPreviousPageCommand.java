package com.verbovskiy.finalproject.controller.command.impl;

import com.verbovskiy.finalproject.controller.AttributeKey;
import com.verbovskiy.finalproject.controller.command.ActionCommand;
import com.verbovskiy.finalproject.controller.command.Constant;
import com.verbovskiy.finalproject.controller.command.PageType;
import com.verbovskiy.finalproject.controller.command.RequestParameter;
import com.verbovskiy.finalproject.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * The type user management previous page command.
 *
 * @author Verbovskiy Sergei
 * @version 1.0
 */
public class UserManagementPreviousPageCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        int fromIndex = (int) session.getAttribute(AttributeKey.FROM_INDEX);
        int toIndex = (int) session.getAttribute(AttributeKey.TO_INDEX);
        List<User> users = (List<User>) session.getAttribute(AttributeKey.USER_LIST);
        fromIndex -= Constant.NUMBER_OF_USER_PER_PAGE;
        if (toIndex == users.size()) {
            toIndex -= users.size() % Constant.NUMBER_OF_USER_PER_PAGE;
        } else {
            toIndex -= Constant.NUMBER_OF_USER_PER_PAGE;
        }
        if (fromIndex < Constant.NUMBER_OF_USER_PER_PAGE) {
            toIndex = Constant.NUMBER_OF_USER_PER_PAGE;
            fromIndex = 0;
        }
        session.setAttribute(AttributeKey.TO_INDEX, toIndex);
        session.setAttribute(AttributeKey.FROM_INDEX, fromIndex);
        return PageType.USER_MANAGEMENT.getPath();
    }
}
