package com.el.betting.common;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamUtils {

    public static <T> List<T> getValueList(Map<? extends Object, Optional<T>> map) {
        return map.values()
                .stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    public static <T> List<T> getFlatten(Stream<List<T>> objectList) {
        return objectList
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    public static <T, T1> List<T1> parseCollection(Collection<T> collection, Function<T, T1> mapper) {
        return collection.stream()
                .map(mapper)
                .collect(Collectors.toList());
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T,Object> keyExtractor) {
        Map<Object,Boolean> seen = new HashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
}
