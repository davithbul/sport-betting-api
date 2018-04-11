package com.el.betting.common;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@SuppressWarnings("unchecked")
public class OptionalElements {

    public static boolean allPresent(Optional<?>... elements) {
        return Arrays.stream(elements)
                .allMatch(Optional::isPresent);
    }

    @SafeVarargs
    public static <T> boolean test(Predicate<T> predicate, Optional<T>... elements) {
        return Arrays.stream(elements)
                .map(Optional::get)
                .allMatch(predicate);
    }

    public static <T> boolean testIfPresent(Predicate<T> predicate, Optional<T>... elements) {
        return Arrays.stream(elements)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .allMatch(predicate);
    }

    @SafeVarargs
    public static <T, R> List<R> applyIfPresent(Function<T, R> function, Optional<T>... elements) {
        return Arrays.stream(elements)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(function)
                .collect(Collectors.toList());
    }

    @SafeVarargs
    public static <T> T getFirstPresent(Optional<T>... optionals) {
        final Optional<T> first = Arrays.stream(optionals)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst();
        return first.orElse(null);
    }

    /**
     * We need this because it supports boxed versions of primitives
     */
    public static Double orElse(OptionalDouble optionalDouble, Double defaultValue) {
        if(optionalDouble.isPresent()) {
            return optionalDouble.getAsDouble();
        } else {
            return defaultValue;
        }
    }

    public static Integer orElse(OptionalInt optionalInt, Integer defaultValue) {
        if(optionalInt.isPresent()) {
            return optionalInt.getAsInt();
        } else {
            return defaultValue;
        }
    }

    public static <T> void elseDo(Optional<T> optional, Runnable action) {
        if(!optional.isPresent()) {
            action.run();
        }
    }
}
