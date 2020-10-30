package com.verbovskiy.finalproject.controller.command.impl.jump;

import com.verbovskiy.finalproject.controller.AttributeKey;
import com.verbovskiy.finalproject.controller.command.ActionCommand;
import com.verbovskiy.finalproject.controller.command.RequestParameter;
import com.verbovskiy.finalproject.controller.command.PageType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ForgotPasswordPageCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String email = request.getParameter(RequestParameter.EMAIL);
        HttpSession session = request.getSession();
        addComeBackPagePath(session, PageType.FORGOT_PASSWORD.getPath());
        if (email != null && !email.isEmpty()) {
            session.setAttribute(RequestParameter.EMAIL, email);
        }
        return PageType.FORGOT_PASSWORD.getPath();
    }
}
