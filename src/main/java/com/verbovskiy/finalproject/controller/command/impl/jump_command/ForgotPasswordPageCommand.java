package com.verbovskiy.finalproject.controller.command.impl.jump_command;

import com.verbovskiy.finalproject.controller.command.ActionCommand;
import com.verbovskiy.finalproject.controller.command.CommandParameter;
import com.verbovskiy.finalproject.controller.command.PageName;

import javax.servlet.http.HttpServletRequest;

public class ForgotPasswordPageCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String email = request.getParameter(CommandParameter.EMAIL_PARAMETER);
        System.out.println(email);
        if (email != null && !email.isEmpty()) {
            request.setAttribute(CommandParameter.EMAIL_PARAMETER, email);
        }
        return PageName.FORGOT_PASSWORD.getPath();
    }
}
