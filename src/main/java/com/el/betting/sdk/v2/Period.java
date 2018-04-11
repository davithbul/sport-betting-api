package com.el.betting.sdk.v2;


public enum Period {
    RACE,
    MATCH,
    FIRST_HALF,
    SECOND_HALF,
    FIRST_QUARTER("1st Quarter"),
    SECOND_QUARTER("2nd Quarter"),
    THIRD_QUARTER("3th Quarter"),
    FOURTH_QUARTER("4th Quarter"),
    FIFTH_QUARTER("5th Quarter"),
    SIXTH_QUARTER("6th Quarter");

    private String description;

    Period() {
    }

    Period(String description) {
        this.description = description;
    }
}
