package com.verbovskiy.finalproject.controller.command.impl.jump;

import com.verbovskiy.finalproject.controller.command.ActionCommand;
import com.verbovskiy.finalproject.controller.command.PageType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * The type add car page command.
 *
 * @author Verbovskiy Sergei
 * @version 1.0
 */
public class AddCarPageCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        return PageType.ADD_CAR.getPath();
    }
}
