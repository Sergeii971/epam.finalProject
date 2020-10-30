package com.verbovskiy.finalproject.controller.command;

import java.util.Optional;

public enum PageType {
    AUTHORIZATION("/jsp/authorization.jsp", false, false),
    REGISTRATION("/jsp/registration.jsp", false, false),
    ERROR("/jsp/error.jsp", false, false),
    USER_INTERFACE("/jsp/userInterface.jsp", true, false),
    ADMIN_INTERFACE("/jsp/adminInterface.jsp", true, true),
    CONFIRMATION("/jsp/userEmailConfirmation.jsp", true, false),
    FORGOT_PASSWORD("/jsp/forgotPassword.jsp", false, false),
    USER_MANAGEMENT("/jsp/userManagement.jsp", true, true),
    SWITCH_LOCALE("/jsp/switchLocale.jsp", false, false);

    private final String path;
    private final boolean isRequireAuthorization;
    private final boolean isAdminPage;

    PageType(String path, boolean isRequireAuthorization, boolean isAdminPage) {
        this.path = path;
        this.isRequireAuthorization = isRequireAuthorization;
        this.isAdminPage = isAdminPage;
    }

    public String getPath(){
        return path;
    }

    public boolean isRequireAuthorization() {
        return isRequireAuthorization;
    }

    public boolean isAdminPage() {
        return isAdminPage;
    }

    public static Optional<PageType> findByPath(String path) {
        Optional<PageType> page = Optional.empty();

        for (PageType pageType : PageType.values()) {
            if (pageType.getPath().equals(path)) {
                page = Optional.of(pageType);
                break;
            }
        }
        return page;
    }
}
