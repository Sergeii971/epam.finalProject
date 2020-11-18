package com.verbovskiy.finalproject.controller.command;

import com.verbovskiy.finalproject.controller.AttributeKey;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * The interface Action command.
 *
 * @author Verbovskiy Sergei
 * @version 1.0
 */
public interface ActionCommand {
    /**
     * Main abstract method for all commands
     *
     * @param request the http request
     * @return the page where to forward/redirect
     */
    String execute(HttpServletRequest request);

    /**
     * remembers the current and previous pages
     *
     * @param session the http request
     * @param currentPage the current page
     */
    default void addComeBackPagePath(HttpSession session, String currentPage) {
        Map<String, String> pages = (Map<String, String>) session.getAttribute(AttributeKey.COME_BACK_PAGES);
        String previousPage = (String) session.getAttribute(AttributeKey.CURRENT_PAGE);

        pages.put(currentPage, previousPage);
        session.setAttribute(AttributeKey.COME_BACK_PAGES, pages);
    }

    /**
     * clears the system of all transitions between the current and previous pages
     *
     * @param session the http request
     * @param currentPage the current page
     */
    default void removeComeBackPagePath(HttpSession session, String currentPage) {
        Map<String, String> pages = (Map<String, String>) session.getAttribute(AttributeKey.COME_BACK_PAGES);
        pages.clear();
        pages.put(currentPage, PageType.HOME.getPath());
    }
}

