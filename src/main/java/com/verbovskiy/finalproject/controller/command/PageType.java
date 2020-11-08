package com.verbovskiy.finalproject.controller.command;

import java.util.Optional;

public enum PageType {
    AUTHORIZATION("/jsp/authorization.jsp", true),
    REGISTRATION("/jsp/registration.jsp", true),
    ERROR("/jsp/error/error500.jsp", false),
    USER_INTERFACE("/jsp/userInterface.jsp", false),
    ADMIN_INTERFACE("/jsp/adminInterface.jsp", false),
    CONFIRMATION("/jsp/userEmailConfirmation.jsp", false),
    FORGOT_PASSWORD("/jsp/forgotPassword.jsp", true),
    USER_MANAGEMENT("/jsp/userManagement.jsp", false),
    ADD_CAR("/jsp/addCar.jsp", false),
    ADMIN_SHOW_CAR("/jsp/adminShowCars.jsp", false),
    INPUT_NEW_PASSWORD_IN_FORGOT_PASSWORD("/jsp/inputNewPasswordInForgotPassword.jsp", true),
    SWITCH_LOCALE("/jsp/switchLocale.jsp", false);

    private final String path;
    private final boolean linkAvailable;

    PageType(String path, boolean linkAvailable) {
        this.path = path;
        this.linkAvailable = linkAvailable;
    }

    public String getPath(){
        return path;
    }

    public boolean isLinkAvailable() {
        return linkAvailable;
    }

    public static Optional<PageType> findByPath(String path) {
        Optional<PageType> page = Optional.empty();
        if (path != null) {
            for (PageType pageType : PageType.values()) {
                if (pageType.getPath().equals(path)) {
                    page = Optional.of(pageType);
                    break;
                }
            }
        }
        return page;
    }
}
