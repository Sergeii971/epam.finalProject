package com.verbovskiy.finalproject.model.entity;

public enum CarEngine {
    DIESEL("diesel"),
    PETROL("petrol");

    private final String engine;

    CarEngine(String engine) {
        this.engine = engine;
    }

    public String getEngine() {
        return engine;
    }
}
