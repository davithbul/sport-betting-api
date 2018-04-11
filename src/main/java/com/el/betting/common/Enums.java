package com.el.betting.common;

import java.util.Optional;

public class Enums {

    public static <T extends Enum<T>> Optional<T> getIfPresent(Class<T> enumClass, String value) {
        try {
            return Optional.of(Enum.valueOf(enumClass, value));
        } catch (IllegalArgumentException iae) {
            return Optional.empty();
        }
    }
}
