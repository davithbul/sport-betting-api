package com.el.betting.common;


import java.util.function.Predicate;

public class Predicates {

    /**
     * Returns a predicate that always evaluates to {@code true}.
     */
    public static <T> Predicate<T> alwaysTrue() {
        return (element -> true);
    }

    /**
     * Returns a predicate that always evaluates to {@code false}.
     */
    public static <T> Predicate<T> alwaysFalse() {
        return (element -> false);
    }

    /**
     * Returns a predicate that evaluates to {@code true} if the object reference
     * being tested is null.
     */
    public static <T> Predicate<T> isNull() {
        return (element -> element == null);
    }

    /**
     * Returns a predicate that evaluates to {@code true} if the object reference
     * being tested is not null.
     */
    public static <T> Predicate<T> notNull() {
        return (element -> element != null);
    }

}
