package com.verbovskiy.finalproject.controller.command;

import com.verbovskiy.finalproject.controller.command.impl.AddUserCommand;

public enum CommandType {
    ADD_USER(new AddUserCommand());

    private final ActionCommand command;

    CommandType(ActionCommand command) {
        this.command = command;
    }

    public ActionCommand getCommand() {
        return command;
    }
}
