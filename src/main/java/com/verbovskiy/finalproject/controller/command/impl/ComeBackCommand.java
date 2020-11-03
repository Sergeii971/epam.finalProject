package com.verbovskiy.finalproject.controller.command.impl;

import com.verbovskiy.finalproject.controller.AttributeKey;
import com.verbovskiy.finalproject.controller.command.ActionCommand;
import com.verbovskiy.finalproject.controller.command.PageType;
import com.verbovskiy.finalproject.controller.command.RequestParameter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

public class ComeBackCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute(RequestParameter.EMAIL);
        if (email == null || email.isEmpty()) {
            session.removeAttribute(RequestParameter.EMAIL);
        }
        Map<String, String> pages = (Map<String, String>) session.getAttribute(AttributeKey.COME_BACK_PAGES);
        String currentPage= (String) session.getAttribute(AttributeKey.CURRENT_PAGE);
        String previousPage = pages.get(currentPage);
        pages.remove(currentPage);
        session.setAttribute(AttributeKey.COME_BACK_PAGES, pages);
        return previousPage;
    }
}
