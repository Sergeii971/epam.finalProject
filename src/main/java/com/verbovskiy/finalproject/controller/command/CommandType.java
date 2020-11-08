package com.verbovskiy.finalproject.controller.command;

import com.verbovskiy.finalproject.controller.command.impl.*;
import com.verbovskiy.finalproject.controller.command.impl.jump.*;

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
    DELETE_CAR(new DeleteCarCommand()),
    CAR_NEXT_PAGE(new CarNextPageCommand()),
    CAR_PREVIOUS_PAGE(new CarPreviousPageCommand()),
    FILTER_USERS(new FilterUserCommand()),
    DELETE_NOT_CONFIRMED_USER(new DeleteNotConfirmedUserCommand()),
    CHECK_NEW_PASSWORD(new CheckNewPasswordCommand()),
    SEARCH_USER(new SearchUserCommand()),
    SORT_USERS(new SortUsersCommand()),
    ADD_CAR(new AddCarCommand()),
    ADMIN_SHOW_CARS_PAGE(new AdminShowCarsPageCommand()),
    ADD_CAR_PAGE(new AddCarPageCommand()),
    CHANGE_CAR_IS_AVAILABLE_STATUS(new ChangeCarIsAvailableStatusCommand()),
    SEND_RECOVERY_KEY(new SendRecoveryKeyCommand());

    private final ActionCommand command;

    CommandType(ActionCommand command) {
        this.command = command;
    }

    public ActionCommand getCommand() {
        return command;
    }
}
