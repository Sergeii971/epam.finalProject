package com.verbovskiy.finalproject.controller.command.impl.jump;

import com.verbovskiy.finalproject.controller.command.ActionCommand;
import com.verbovskiy.finalproject.controller.command.PageType;
import com.verbovskiy.finalproject.controller.command.RequestParameter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;

/**
 * The type registration page command.
 *
 * @author Verbovskiy Sergei
 * @version 1.0
 */
public class RegistrationPageCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        addComeBackPagePath(session, PageType.REGISTRATION.getPath());
        String email = (String) session.getAttribute(RequestParameter.EMAIL);
        if (email != null && !email.isEmpty()) {
         session.removeAttribute(RequestParameter.EMAIL);
        }
        return PageType.REGISTRATION.getPath();
    }
}
