package com.verbovskiy.finalproject.controller.command;

import com.verbovskiy.finalproject.controller.command.impl.*;
import com.verbovskiy.finalproject.controller.command.impl.jump_command.AuthorizationPageCommand;
import com.verbovskiy.finalproject.controller.command.impl.jump_command.ForgotPasswordPageCommand;

public enum CommandType {
    ADD_USER(new AddUserCommand()),
    AUTHORIZATION(new AuthorizationCommand()),
    EMAIL_CONFIRMATION(new EmailConfirmationCommand()),
    MOVE_AUTHORIZATION_PAGE(new AuthorizationPageCommand()),
    FORGOT_PASSWORD(new ForgotPasswordCommand()),
    MOVE_FORGOT_PASSWORD_PAGE(new ForgotPasswordPageCommand()),
    SWITCH_LOCALE(new SwitchLocaleCommand()),
    SEND_RECOVERY_KEY(new SendRecoveryKeyCommand());

    private final ActionCommand command;

    CommandType(ActionCommand command) {
        this.command = command;
    }

    public ActionCommand getCommand() {
        return command;
    }
}
