package com.car.carAdverts.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum FuelType {
    DIESEL("diesel"),
    GASOLINE("gasoline");

    private final String name;

    FuelType(String name) {
        this.name = name;
    }

    @JsonValue
    public String getName() {
        return name;
    }
}
