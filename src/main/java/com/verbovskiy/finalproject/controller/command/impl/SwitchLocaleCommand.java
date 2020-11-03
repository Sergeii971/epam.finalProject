package com.verbovskiy.finalproject.controller.command.impl;

import com.verbovskiy.finalproject.controller.AttributeKey;
import com.verbovskiy.finalproject.controller.RequestAttributeHandler;
import com.verbovskiy.finalproject.controller.command.ActionCommand;
import com.verbovskiy.finalproject.controller.command.RequestParameter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

public class SwitchLocaleCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String locale = request.getParameter(AttributeKey.LOCALE);
        session.setAttribute(AttributeKey.LOCALE, locale);
        RequestAttributeHandler requestAttributeHandler =
                (RequestAttributeHandler) session.getAttribute(AttributeKey.REQUEST_ATTRIBUTE_HANDLER);
        Map<String, Object> attributes = requestAttributeHandler.getAttributes();
        for (Map.Entry<String, Object> entry : attributes.entrySet()) {
            request.setAttribute(entry.getKey(), entry.getValue());
        }
        return (String) session.getAttribute(AttributeKey.CURRENT_PAGE);
    }
}
