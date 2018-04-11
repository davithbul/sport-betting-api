package com.el.betting.sdk.v3.common;

import java.util.Arrays;

public enum TickType {
    Steady(0),
    Smooth(1),
    Quick(2),
    Sharp(200);

    private int maxTickSize;

    TickType(int maxTickSize) {
        this.maxTickSize = maxTickSize;
    }

    public static TickType getTickType(double tick) {
        return Arrays.stream(TickType.values())
                .filter(tickType -> tickType.maxTickSize >= tick)
                .findFirst().get();
    }
}
