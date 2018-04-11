package com.el.betting.sdk.v2.common;

import com.el.betting.sdk.v2.Event;
import com.el.betting.sdk.v2.criteria.EventFilters;
import com.el.betting.sdk.v4.Participant;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class EventsFilterUtil {

    private final static Logger log = LoggerFactory.getLogger(EventsFilterUtil.class);

    public static List<Event<? extends Participant>> filterEvents(List<Event<? extends Participant>> events, EventFilters eventFilters) {
        Set<Predicate<Event<? extends Participant>>> predicates = new HashSet<>();
        if (!CollectionUtils.isEmpty(eventFilters.getSportIDs())) {
            predicates.add((event -> eventFilters.getSportIDs().contains(event.getSportId())));
        }

        if (!CollectionUtils.isEmpty(eventFilters.getLeagueIds())) {
            predicates.add((event -> eventFilters.getLeagueIds().contains(event.getLeagueId())));
        }

        if (!CollectionUtils.isEmpty(eventFilters.getEventIDs())) {
            predicates.add((event -> eventFilters.getEventIDs().contains(event.getId())));
        }

        if (!CollectionUtils.isEmpty(eventFilters.getEventStatuses())) {
            predicates.add((event -> eventFilters.getEventStatuses().contains(event.getStatus())));
        }

        if (eventFilters.getEventStartTime() != null) {
            predicates.addAll(TimeRanges.getPredicatesForDefaultZone(eventFilters.getEventStartTime(), Event::getStartTime));
        }

        if (StringUtils.isNotBlank(eventFilters.getTextQuery())) {
            predicates.add((event ->
                    event.getParticipants().stream().anyMatch(team -> team.getName().contains(eventFilters.getTextQuery()))));
        }

        return events.stream()
                .filter(event -> predicates.stream().allMatch(predicate -> predicate.test(event)))
                .collect(Collectors.toList());
    }
}
