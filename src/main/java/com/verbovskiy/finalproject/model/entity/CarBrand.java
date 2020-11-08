package com.verbovskiy.finalproject.model.entity;

public enum CarBrand {
    AUDI("Audi"),
    BMW("BMW"),
    BUGATTI("Bugatti"),
    BENTLEY("Bentley"),
    CADILLAC("Cadillac"),
    NISSAN("Nissan"),
    FERRARI("Ferrari"),
    JAGUAR("Jaguar"),
    MASERATI("Maserati");

    private final String brand;

    CarBrand(String brand) {
        this.brand = brand;
    }

    public String getBrand() {
        return brand;
    }
}

