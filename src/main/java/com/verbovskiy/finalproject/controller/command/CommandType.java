package com.verbovskiy.finalproject.controller.command;

import com.verbovskiy.finalproject.controller.command.impl.AddUserCommand;
import com.verbovskiy.finalproject.controller.command.impl.AuthorizationCommand;

public enum CommandType {
    ADD_USER(new AddUserCommand()),
    AUTHORIZATION(new AuthorizationCommand());

    private final ActionCommand command;

    CommandType(ActionCommand command) {
        this.command = command;
    }

    public ActionCommand getCommand() {
        return command;
    }
}
