package com.verbovskiy.finalproject.controller.command.impl.jump;

import com.verbovskiy.finalproject.controller.command.ActionCommand;
import com.verbovskiy.finalproject.controller.command.PageType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AddGoodPageCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        addComeBackPagePath(session, PageType.USER_MANAGEMENT.getPath());
        return PageType.ADD_GOOD.getPath();
    }
}
