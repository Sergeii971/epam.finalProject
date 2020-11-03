package com.verbovskiy.finalproject.controller.command;

import com.verbovskiy.finalproject.controller.command.impl.*;
import com.verbovskiy.finalproject.controller.command.impl.jump.AuthorizationPageCommand;
import com.verbovskiy.finalproject.controller.command.impl.jump.ForgotPasswordPageCommand;
import com.verbovskiy.finalproject.controller.command.impl.jump.RegistrationPageCommand;
import com.verbovskiy.finalproject.controller.command.impl.jump.UserManagementPageCommand;

public enum CommandType {
    ADD_USER(new AddUserCommand()),
    AUTHENTICATION(new AuthenticationCommand()),
    EMAIL_CONFIRMATION(new RegistrationEmailConfirmationCommand()),
    FORGOT_PASSWORD_EMAIL_CONFIRMATION(new ForgotPasswordEmailConfirmationCommand()),
    MOVE_AUTHORIZATION_PAGE(new AuthorizationPageCommand()),
    MOVE_FORGOT_PASSWORD_PAGE(new ForgotPasswordPageCommand()),
    USER_MANAGEMENT_PAGE(new UserManagementPageCommand()),
    SWITCH_LOCALE(new SwitchLocaleCommand()),
    REGISTRATION_PAGE(new RegistrationPageCommand()),
    COME_BACK(new ComeBackCommand()),
    CHANGE_USER_BLOCK_STATUS(new ChangeUserBlockStatusCommand()),
    FILTER_USERS(new FilterUserCommand()),
    DELETE_NOT_CONFIRMED_USER(new DeleteNotConfirmedUserCommand()),
    CHECK_NEW_PASSWORD(new CheckNewPasswordCommand()),
    SEND_RECOVERY_KEY(new SendRecoveryKeyCommand());

    private final ActionCommand command;

    CommandType(ActionCommand command) {
        this.command = command;
    }

    public ActionCommand getCommand() {
        return command;
    }
}
