package com.example.hrworld.businessObject;

public enum ProductType {
    FISH("FISH"),
    DOGS("DOGS"),
    REPTILES("REPTILES"),
    CATS("CATS"),
    BIRDS("BIRDS");

    private final String type;

    ProductType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
