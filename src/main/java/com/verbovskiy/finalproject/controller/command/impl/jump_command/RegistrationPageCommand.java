package com.verbovskiy.finalproject.controller.command.impl.jump_command;

import com.verbovskiy.finalproject.controller.command.ActionCommand;
import com.verbovskiy.finalproject.controller.command.PageName;

import javax.servlet.http.HttpServletRequest;

public class RegistrationPageCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        return PageName.REGISTRATION.getPath();
    }
}
