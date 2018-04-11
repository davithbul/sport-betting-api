package com.el.betting.sdk.v2.common;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class CacheData<T> {
    private T object;
    private int period;
    private LocalDateTime putTime;

    public CacheData(T object, int period) {
        this.object = object;
        this.period = period;
        putTime = LocalDateTime.now();
    }

    public T getObject() {
        return object;
    }

    public boolean isActive() {
        return putTime.until(LocalDateTime.now(), ChronoUnit.MINUTES) <= period;
    }
}
