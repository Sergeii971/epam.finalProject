package com.verbovskiy.finalproject.controller.command.impl.jump_command;

import com.verbovskiy.finalproject.controller.AttributeKey;
import com.verbovskiy.finalproject.controller.command.ActionCommand;
import com.verbovskiy.finalproject.controller.command.CommandParameter;
import com.verbovskiy.finalproject.controller.command.PageName;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AuthorizationPageCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.isNew()) {
            session.setAttribute(AttributeKey.LOCALE, AttributeKey.DEFAULT_LOCALE);
            session.setAttribute(AttributeKey.CURRENT_PAGE, PageName.AUTHORIZATION.getPath());
            session.setAttribute(CommandParameter.IS_ACTIVE, false);
        }
        return PageName.AUTHORIZATION.getPath();
    }
}
