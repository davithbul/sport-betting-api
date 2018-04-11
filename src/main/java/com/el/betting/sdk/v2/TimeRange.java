package com.el.betting.sdk.v2;

import javax.annotation.concurrent.Immutable;

@Immutable
public class TimeRange<T> {

    private final T from;
    private final T to;

    public static <T> TimeRange<T> after(T from) {
        return new TimeRange<>(from, null);
    }

    public static <T> TimeRange<T> before(T to) {
        return new TimeRange<>(null, to);
    }

    public static <T> TimeRange of(T from, T to) {
        return new TimeRange<>(from, to);
    }

    private TimeRange(T from, T to) {
        this.from = from;
        this.to = to;
    }

    public T getFrom() {
        return from;
    }

    public T getTo() {
        return to;
    }
}
