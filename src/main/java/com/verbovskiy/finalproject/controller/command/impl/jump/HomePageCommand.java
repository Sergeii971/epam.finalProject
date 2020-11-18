package com.verbovskiy.finalproject.controller.command.impl.jump;

import com.verbovskiy.finalproject.controller.AttributeKey;
import com.verbovskiy.finalproject.controller.command.ActionCommand;
import com.verbovskiy.finalproject.controller.command.RequestParameter;
import com.verbovskiy.finalproject.controller.command.PageType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * The type authorization page command.
 *
 * @author Verbovskiy Sergei
 * @version 1.0
 */
public class HomePageCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();

        session.setAttribute(RequestParameter.EMAIL, RequestParameter.EMPTY_VALUE);
        session.setAttribute(RequestParameter.IS_ADMIN, false);
        session.setAttribute(AttributeKey.LOCALE, AttributeKey.DEFAULT_LOCALE);
        Map<String, String> pages = new HashMap<>();
        session.setAttribute(AttributeKey.COME_BACK_PAGES, pages);
        return PageType.HOME.getPath();
    }
}
