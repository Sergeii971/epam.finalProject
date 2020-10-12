package com.verbovskiy.finalproject.controller.command.impl;

import com.verbovskiy.finalproject.controller.command.ActionCommand;
import com.verbovskiy.finalproject.controller.command.PageName;

import javax.servlet.http.HttpServletRequest;

public class AuthorizationPageCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        return PageName.AUTHORIZATION.getPath();
    }
}
