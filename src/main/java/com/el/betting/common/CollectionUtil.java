package com.el.betting.common;

import com.google.common.base.Preconditions;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static javafx.scene.input.KeyCode.T;

public class CollectionUtil {

    @SafeVarargs
    public static <T> List<T> addAll(List<T>... collectionList) {
        List<T> finalCollection = null;
        for (List<T> collection : collectionList) {
            if (collection != null) {
                if (finalCollection == null) {
                    finalCollection = collection;
                } else {
                    finalCollection.addAll(collection);
                }
            }
        }
        return finalCollection != null ? finalCollection : new ArrayList<>();
    }

    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map, boolean ascending) {
        Comparator<Map.Entry<K, V>> comparator = Comparator.comparing(Map.Entry::getValue);
        if (!ascending) {
            comparator = comparator.reversed();
        }

        return sortByValue(map, comparator);
    }

    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map, Comparator<Map.Entry<K, V>> comparator) {
        Map<K, V> result = new LinkedHashMap<>();
        map.entrySet().stream()
                .sorted(comparator)
                .forEach(e -> result.put(e.getKey(), e.getValue()));
        return result;
    }

    public static boolean containsIgnoreCase(String[] array, String value) {
        return Arrays.stream(array).anyMatch(element -> element.toUpperCase().contains(value.toUpperCase()));
    }

    public static <E> E getElement(Collection<E> collection, int index) {
        return getElement(collection.iterator(), index);
    }

    public static <E> E getElement(Iterator<E> iterator, int index) {
        Preconditions.checkArgument(index >= 0);
        E element = null;
        while (index-- > -1) {
            Preconditions.checkArgument(iterator.hasNext(), "IndexOutOfBound Exception");
            element = iterator.next();
        }

        return element;
    }

    @SafeVarargs
    public static <T, R> List<R> applyIfValid(Predicate<T> validate, Function<T, R> function, T... elements) {
        return Arrays.stream(elements)
                .filter(validate)
                .map(function)
                .collect(Collectors.toList());
    }

    @SafeVarargs
    public static <E> void applyToCollection(Set<E> collection, Function<? super E, ? extends E>... mappers) {
        if (mappers.length == 0) {
            return;
        }

        HashSet<E> newElements = new HashSet<>();
        for (E element : collection) {
            E newElement = element;
            for (Function<? super E, ? extends E> mapper : mappers) {
                newElement = mapper.apply(newElement);
            }
            newElements.add(newElement);
        }

        collection.clear();
        collection.addAll(newElements);
    }

    public static <T> List<T> map(List<?> list, Class<T> type) {
        return list.stream()
                .map(element -> (T) element)
                .collect(Collectors.toList());
    }

    /**
     * Returns list if not null, otherwise new empty list.
     */
    public static <T> List<T> safeList(List<T> list) {
        if (list != null) {
            return list;
        }
        return new ArrayList<T>();
    }

    public static <K, V> Map<K, V> safeMap(Map<K, V> map) {
        if (map != null) {
            return map;
        }
        return new HashMap<>();
    }

    public static <K, V> String printMap(Map<K, V> map) {
        StringBuilder niceMap = new StringBuilder();
        for (Map.Entry<K, V> entry : map.entrySet()) {
            niceMap.append(entry.getKey())
                    .append(" = ")
                    .append(entry.getValue())
                    .append(", ");
        }
        return niceMap.toString();
    }

    public static <T> T getFirstNotNull(T... elements) {
        for (T element : elements) {
            if (element != null) {
                return element;
            }
        }
        throw new RuntimeException("All elements are null");
    }

    public static <T> List<T> collectNotNulls(T... elements) {
        return Arrays.stream(elements)
                .filter(element -> element != null)
                .collect(Collectors.toList());
    }

    public static <E, T extends List<E>> T asList(T list, E... elements) {
        Arrays.stream(elements).forEach(list::add);
        return list;
    }

    /**
     * Returns existing collection if it's not null, otherwise returns
     * new collection.
     */
    public static <T> List<T> getOrCreate(List<T> existing) {
        if (existing == null) {
            return new ArrayList<>();
        } else {
            return existing;
        }
    }
}
