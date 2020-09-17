package com.verbovskiy.finalproject.controller.command;

public enum  PageName {
    AUTHORIZATION("/jsp/authorization.jsp"),
    REGISTRATION("/jsp/register.jsp"),
    ERROR("/jsp/error.jsp"),
    USER_INTERFACE("jsp/userInterface.jsp"),
    ADMIN_INTERFACE("jsp/adminInterface.jsp");

    private String path;

    PageName(String path){
        this.path = path;
    }

    public String getPath(){
        return path;
    }
}
