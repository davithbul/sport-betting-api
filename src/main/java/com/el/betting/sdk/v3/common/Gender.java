package com.el.betting.sdk.v3.common;

public enum Gender {
    MALE("MEN"),
    FEMALE("WOMEN"),
    MIXED("MIXED");
    String name;

    Gender(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
