package com.verbovskiy.finalproject.controller.command;

import com.verbovskiy.finalproject.controller.command.impl.*;
import com.verbovskiy.finalproject.controller.command.impl.jump.AuthorizationPageCommand;
import com.verbovskiy.finalproject.controller.command.impl.jump.ForgotPasswordPageCommand;
import com.verbovskiy.finalproject.controller.command.impl.jump.RegistrationPageCommand;
import com.verbovskiy.finalproject.controller.command.impl.jump.UserManagementPageCommand;

public enum CommandType {
    ADD_USER(new AddUserCommand()),
    AUTHENTICATION(new AuthenticationCommand()),
    EMAIL_CONFIRMATION(new EmailConfirmationCommand()),
    MOVE_AUTHORIZATION_PAGE(new AuthorizationPageCommand()),
    FORGOT_PASSWORD(new ForgotPasswordCommand()),
    MOVE_FORGOT_PASSWORD_PAGE(new ForgotPasswordPageCommand()),
    USER_MANAGEMENT_PAGE(new UserManagementPageCommand()),
    SWITCH_LOCALE(new SwitchLocaleCommand()),
    REGISTRATION_PAGE(new RegistrationPageCommand()),
    COME_BACK(new ComeBackCommand()),
    SEND_RECOVERY_KEY(new SendRecoveryKeyCommand());

    private final ActionCommand command;

    CommandType(ActionCommand command) {
        this.command = command;
    }

    public ActionCommand getCommand() {
        return command;
    }
}
