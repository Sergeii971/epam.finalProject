package com.verbovskiy.finalproject.controller.command;

import java.util.Optional;

public enum  PageName {
    AUTHORIZATION("/jsp/authorization.jsp", false),
    REGISTRATION("/jsp/registration.jsp", false),
    ERROR("/jsp/error.jsp", false),
    USER_INTERFACE("/jsp/userInterface.jsp", true),
    ADMIN_INTERFACE("/jsp/adminInterface.jsp", true),
    CONFIRMATION("/jsp/userEmailConfirmation.jsp", false),
    FORGOT_PASSWORD("/jsp/forgotPassword.jsp", false),
    SWITCH_LOCALE("/jsp/switchLocale.jsp", false);

    private final String path;
    private final boolean isRequireAuthorization;

    PageName(String path, boolean isRequireAuthorization) {
        this.path = path;
        this.isRequireAuthorization = isRequireAuthorization;
    }

    public String getPath(){
        return path;
    }

    public boolean isRequireAuthorization() {
        return isRequireAuthorization;
    }

    public static Optional<PageName> findByPath(String path) {
        Optional<PageName> page = Optional.empty();

        for (PageName pageName : PageName.values()) {
            if (pageName.getPath().equals(path)) {
                page = Optional.of(pageName);
                break;
            }
        }
        return page;
    }
}
