package com.verbovskiy.finalproject.model.entity;

public enum BoxType {
    MECHANICS("mechanics"),
    AUTOMATION("automation");

    private final String box;

    BoxType(String box) {
        this.box = box;
    }

    public String getBox() {
        return box;
    }
}
