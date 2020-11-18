package com.verbovskiy.finalproject.controller.command;

import com.verbovskiy.finalproject.controller.command.impl.*;
import com.verbovskiy.finalproject.controller.command.impl.jump.*;

/**
 * The enum Command type.
 *
 * @author Verbovskiy Sergei
 * @version 1.0
 */
public enum CommandType {
    ADD_USER(new AddUserCommand()),
    AUTHENTICATION(new AuthenticationCommand()),
    EMAIL_CONFIRMATION(new RegistrationEmailConfirmationCommand()),
    FORGOT_PASSWORD_EMAIL_CONFIRMATION(new ForgotPasswordEmailConfirmationCommand()),
    MOVE_HOME_PAGE(new HomePageCommand()),
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
    SHOW_CARS_PAGE(new ShowCarsPageCommand()),
    BUY_CAR(new BuyCarCommand()),
    BUY_CAR_PAGE(new BuyCarPageCommand()),
    ADMIN_SHOW_ORDERS_PAGE(new AdminShowOrdersPageCommand()),
    USER_SHOW_ORDERS_PAGE(new UserShowOrdersPageCommand()),
    USER_MANAGEMENT_NEXT_PAGE(new UserManagementNextPageCommand()),
    USER_MANAGEMENT_PREVIOUS_PAGE(new UserManagementPreviousPageCommand()),
    ADMIN_DELETE_ORDER(new AdminDeleteOrderCommand()),
    USER_DELETE_ORDER(new UserDeleteOrderCommand()),
    ORDER_NEXT_PAGE(new OrderNextPageCommand()),
    ORDER_PREVIOUS_PAGE(new OrderPreviousPageCommand()),
    FIND_ORDER(new FindOrderCommand()),
    CHANGE_ORDER_STATUS(new ChangeOrderStatusCommand()),
    ADD_CAR_PAGE(new AddCarPageCommand()),
    FIND_CAR(new FindCarCommand()),
    CHANGE_CAR_IS_AVAILABLE_STATUS(new ChangeCarAvailableStatusCommand()),
    SEND_RECOVERY_KEY(new SendRecoveryKeyCommand());

    private final ActionCommand command;

    CommandType(ActionCommand command) {
        this.command = command;
    }

    /**
     * Gets command.
     *
     * @return the command
     */
    public ActionCommand getCommand() {
        return command;
    }
}
