package com.verbovskiy.finalproject.controller.command.impl.jump_command;

import com.verbovskiy.finalproject.controller.AttributeKey;
import com.verbovskiy.finalproject.controller.command.ActionCommand;
import com.verbovskiy.finalproject.controller.command.PageName;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AuthorizationPageCommand implements ActionCommand {
    private static final String DEFAULT_LOCALE = "en";

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.isNew()) {
            session.setAttribute(AttributeKey.LOCALE, DEFAULT_LOCALE);
        }
        return PageName.AUTHORIZATION.getPath();
    }
}
