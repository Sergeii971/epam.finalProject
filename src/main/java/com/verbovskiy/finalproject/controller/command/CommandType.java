package com.verbovskiy.finalproject.controller.command;

import com.verbovskiy.finalproject.controller.command.impl.AddUserCommand;
import com.verbovskiy.finalproject.controller.command.impl.AuthorizationCommand;
import com.verbovskiy.finalproject.controller.command.impl.EmailConfirmationCommand;
import com.verbovskiy.finalproject.controller.command.impl.AuthorizationPageCommand;

public enum CommandType {
    ADD_USER(new AddUserCommand()),
    AUTHORIZATION(new AuthorizationCommand()),
    EMAIL_CONFIRMATION(new EmailConfirmationCommand()),
    MOVE_AUTHORIZATION_PAGE(new AuthorizationPageCommand());

    private final ActionCommand command;

    CommandType(ActionCommand command) {
        this.command = command;
    }

    public ActionCommand getCommand() {
        return command;
    }
}
