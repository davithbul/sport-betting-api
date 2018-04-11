package com.el.betting.sdk.v2.common;

import com.el.betting.common.DateUtils;
import com.el.betting.sdk.v2.Event;
import com.el.betting.sdk.v2.TimeRange;
import com.el.betting.sdk.v4.Participant;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class TimeRanges {

    public static List<Predicate<Event<? extends Participant>>> getPredicatesForDefaultZone(TimeRange<LocalDateTime> timeRange, Function<Event, LocalDateTime> function) {
        List<Predicate<Event<? extends Participant>>> predicates = new ArrayList<>();
        if (timeRange.getFrom() != null) {
            predicates.add(event -> !function.apply(event).isBefore(DateUtils.convertUtcToDefaultZone(timeRange.getFrom())));
        }

        if (timeRange.getTo() != null) {
            predicates.add(event -> !function.apply(event).isAfter(DateUtils.convertUtcToDefaultZone(timeRange.getTo())));
        }

        return predicates;
    }

    public static List<Predicate<Event<? extends Participant>>> getPredicates(TimeRange<LocalDateTime> timeRange, Function<Event, LocalDateTime> function) {
        List<Predicate<Event<? extends Participant>>> predicates = new ArrayList<>();
        if (timeRange.getFrom() != null) {
            predicates.add(event -> !function.apply(event).isBefore(timeRange.getFrom()));
        }

        if (timeRange.getTo() != null) {
            predicates.add(event -> !function.apply(event).isAfter(timeRange.getTo()));
        }

        return predicates;
    }

    public static <T extends Number> boolean isWithin(T number, TimeRange<T> timeRange) {
        return timeRange.getFrom().intValue() >= number.intValue() &&
                timeRange.getTo().intValue() <= number.intValue();
    }
}
