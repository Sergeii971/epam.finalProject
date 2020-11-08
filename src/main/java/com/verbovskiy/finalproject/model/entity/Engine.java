package com.verbovskiy.finalproject.model.entity;

public enum Engine {
    DIESEL("diesel"),
    PETROL("petrol");

    private final String engine;

    Engine(String engine) {
        this.engine = engine;
    }

    public String getEngine() {
        return engine;
    }
}
