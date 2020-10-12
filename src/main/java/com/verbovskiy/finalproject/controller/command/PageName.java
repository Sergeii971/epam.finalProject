package com.verbovskiy.finalproject.controller.command;

public enum  PageName {
    AUTHORIZATION("jsp/authorization.jsp"),
    REGISTRATION("jsp/registration.jsp"),
    ERROR("/jsp/error.jsp"),
    USER_INTERFACE("jsp/userInterface.jsp"),
    ADMIN_INTERFACE("jsp/adminInterface.jsp"),
    CONFIRMATION("jsp/userEmailConfirmation.jsp");

    private String path;

    PageName(String path){
        this.path = path;
    }

    public String getPath(){
        return path;
    }
}
