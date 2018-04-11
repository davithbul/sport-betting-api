package com.el.betting.sdk.v2;

public enum  PropertyType {
    HOME("HOME_"),
    AWAY("AWAY_"),
    DRAW("DRAW_"),
    LAY("LAY_"),
    BACK("BACK_"),
    GLOBAL("GLOBAL_");

    private String value;

    PropertyType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
