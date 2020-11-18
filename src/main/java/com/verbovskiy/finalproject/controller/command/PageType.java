package com.verbovskiy.finalproject.controller.command;

import java.util.Optional;

/**
 * The enum page type
 *
 * @author Verbovskiy Sergei
 * @version 1.0
 */
public enum PageType {
    HOME("/jsp/home.jsp", true),
    INDEX("/index.jsp", true),
    REGISTRATION("/jsp/registration.jsp", true),
    ERROR("/jsp/error/error500.jsp", false),
    USER_INTERFACE("/jsp/userInterface.jsp", false),
    ADMIN_INTERFACE("/jsp/adminInterface.jsp", false),
    CONFIRMATION("/jsp/userEmailConfirmation.jsp", false),
    FORGOT_PASSWORD("/jsp/forgotPassword.jsp", true),
    USER_MANAGEMENT("/jsp/userManagement.jsp", false),
    ADD_CAR("/jsp/addCar.jsp", false),
    BUY_CAR("/jsp/buyCar.jsp", false),
    SHOW_CAR("/jsp/showCars.jsp", false),
    ADMIN_SHOW_ORDER("/jsp/adminShowOrders.jsp", false),
    USER_SHOW_ORDER("/jsp/userShowOrders.jsp", false),
    INPUT_NEW_PASSWORD_IN_FORGOT_PASSWORD("/jsp/inputNewPasswordInForgotPassword.jsp", true),
    SWITCH_LOCALE("/jsp/switchLocale.jsp", false);

    private final String path;
    private final boolean linkAvailable;

    PageType(String path, boolean linkAvailable) {
        this.path = path;
        this.linkAvailable = linkAvailable;
    }

    /**
     * Gets path.
     *
     * @return the path
     */
    public String getPath(){
        return path;
    }

    /**
     * is link available
     *
     * @return boolean
     */
    public boolean isLinkAvailable() {
        return linkAvailable;
    }

    /**
     * looking for the same path in enum
     *
     * @param path the path
     * @return page type
     */
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
