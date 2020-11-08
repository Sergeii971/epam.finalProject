package com.verbovskiy.finalproject.model.entity;

public enum CarColor {
    BLACK("black"),
    RED("red"),
    WHITE("white"),
    ORANGE("orange");

    CarColor(String color) {
        this.color = color;
    }

    private final String color;

    public String getColor() {
        return color;
    }
}
