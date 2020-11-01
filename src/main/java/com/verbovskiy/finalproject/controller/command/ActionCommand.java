package com.verbovskiy.finalproject.controller.command;

import com.verbovskiy.finalproject.controller.AttributeKey;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

public interface ActionCommand {
    String execute(HttpServletRequest request);

    default void addComeBackPagePath(HttpSession session, String currentPage) {
        Map<String, String> pages = (Map<String, String>) session.getAttribute(AttributeKey.COME_BACK_PAGES);
        String previousPage = (String) session.getAttribute(AttributeKey.CURRENT_PAGE);

        pages.put(currentPage, previousPage);
        session.setAttribute(AttributeKey.COME_BACK_PAGES, pages);
    }

    default void removeComeBackPagePath(HttpSession session, String currentPage) {
        Map<String, String> pages = (Map<String, String>) session.getAttribute(AttributeKey.COME_BACK_PAGES);
        pages.clear();
        session.setAttribute(RequestParameter.IS_ACTIVE, false);
        pages.put(currentPage, PageType.AUTHORIZATION.getPath());
    }
}

