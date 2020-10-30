package com.verbovskiy.finalproject.controller.command.impl.jump;

import com.verbovskiy.finalproject.controller.AttributeKey;
import com.verbovskiy.finalproject.controller.command.ActionCommand;
import com.verbovskiy.finalproject.controller.command.RequestParameter;
import com.verbovskiy.finalproject.controller.command.PageType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class AuthorizationPageCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.isNew()) {
            session.setAttribute(AttributeKey.LOCALE, AttributeKey.DEFAULT_LOCALE);
            session.setAttribute(RequestParameter.IS_ACTIVE, false);
            session.setAttribute(RequestParameter.IS_ADMIN, false);
            Map<String, String> pages = new HashMap<>();
            session.setAttribute(AttributeKey.COME_BACK_PAGES, pages);
        }
        return PageType.AUTHORIZATION.getPath();
    }
}
