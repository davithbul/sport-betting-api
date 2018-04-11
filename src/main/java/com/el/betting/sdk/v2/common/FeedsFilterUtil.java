package com.el.betting.sdk.v2.common;

import com.el.betting.sdk.v2.BookmakerFeeds;
import com.el.betting.sdk.v2.Event;
import com.el.betting.sdk.v2.League;
import com.el.betting.sdk.v2.Sport;
import com.el.betting.sdk.v2.criteria.FeedsFilter;
import com.el.betting.sdk.v4.Participant;
import org.slf4j.Logger;import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FeedsFilterUtil {

    private final static Logger log = LoggerFactory.getLogger(FeedsFilterUtil.class);

    public static List<Event<? extends Participant>> searchEvents(BookmakerFeeds bookmakerFeeds, FeedsFilter feedsFilter) {
        if (bookmakerFeeds.getSports() == null) {
            return Collections.emptyList();
        }

        Stream<League> leagueStream = applySportsFilters(bookmakerFeeds, feedsFilter)
                .filter(sport -> !CollectionUtils.isEmpty(sport.getLeagues()))
                .map(Sport::getLeagues)
                .flatMap(Collection::stream);

        Stream<Event<? extends Participant>> eventStream = applyLeagueFilters(leagueStream, feedsFilter)
                .filter(league -> !CollectionUtils.isEmpty(league.getEvents()))
                .map(League::getEvents)
                .flatMap(Collection::stream);

        return applyEventsFilter(eventStream, feedsFilter).collect(Collectors.toList());
    }

    public static List<League> searchLeagues(BookmakerFeeds bookmakerFeeds, FeedsFilter feedsFilter) {
        if (bookmakerFeeds.getSports() == null) {
            return Collections.emptyList();
        }

        //filter sports
        Stream<League> leagueStream = applySportsFilters(bookmakerFeeds, feedsFilter)
                .filter(sport -> !CollectionUtils.isEmpty(sport.getLeagues()))
                .map(Sport::getLeagues)
                .flatMap(Collection::stream);

        //filter leagues
        leagueStream = applyLeagueFilters(leagueStream, feedsFilter)
                .map(league -> {
                    League clonedLeague = league.clone();
                    if (clonedLeague.getEvents() == null) {
                        clonedLeague.setEvents(new ArrayList<>());
                    }

                    List<Event<? extends Participant>> events = applyEventsFilter(clonedLeague.getEvents().stream(), feedsFilter)
                            .collect(Collectors.toList());
                    clonedLeague.setEvents(events);

                    return clonedLeague;
                });

        if (containsEventFilters(feedsFilter)) {
            leagueStream = leagueStream.filter(league -> !CollectionUtils.isEmpty(league.getEvents()));
        }

        return leagueStream.collect(Collectors.toList());
    }

    public static List<Sport> searchSports(BookmakerFeeds bookmakerFeeds, FeedsFilter feedsFilter) {
        if (bookmakerFeeds.getSports() == null) {
            return Collections.emptyList();
        }

        Stream<Sport> sportStream = applySportsFilters(bookmakerFeeds, feedsFilter)
                .map(sport -> {
                    Sport sportClone = sport.clone();
                    if (sportClone.getLeagues() == null) {
                        sportClone.setLeagues(new ArrayList<>());
                    }

                    //filter leagues
                    Stream<League> leagueStream = applyLeagueFilters(sportClone.getLeagues().stream(), feedsFilter)
                            .filter(league -> league.getEvents() != null)
                            .map(league -> {
                                Stream<Event<? extends Participant>> eventStream = applyEventsFilter(league.getEvents().stream(), feedsFilter);
                                league.setEvents(eventStream.collect(Collectors.toList()));
                                return league;
                            });

                    if (containsEventFilters(feedsFilter)) {
                        leagueStream = leagueStream.filter(league -> !CollectionUtils.isEmpty(league.getEvents()));
                    }

                    sportClone.setLeagues(leagueStream.collect(Collectors.toList()));
                    return sportClone;
                });

        //if there is league filters then check that leagues are not empty after applying filter
        if (containsLeagueFilters(feedsFilter)) {
            sportStream = sportStream.filter(sport -> !CollectionUtils.isEmpty(sport.getLeagues()));
        }

        return sportStream.collect(Collectors.toList());
    }

    private static Stream<Sport> applySportsFilters(BookmakerFeeds bookmakerFeeds, FeedsFilter feedsFilter) {
        return bookmakerFeeds.getSports()
                .stream()
                .filter(sport -> feedsFilter.getSportType() == null || sport.getSportType() == feedsFilter.getSportType());
    }

    private static Stream<League> applyLeagueFilters(Stream<League> leagues, FeedsFilter feedsFilter) {
        return leagues
                .filter(league -> {
                    if (CollectionUtils.isEmpty(feedsFilter.getLeaguePredicates())) {
                        return true;
                    }

                    return feedsFilter.getLeaguePredicates().stream().anyMatch(leaguePredicate -> leaguePredicate.test(league));
                });
    }

    public static Stream<Event<? extends Participant>> applyEventsFilter(Stream<Event<? extends Participant>> eventStream, FeedsFilter feedsFilter) {
        return eventStream.filter(event -> {
            if (CollectionUtils.isEmpty(feedsFilter.getEventPredicates())) {
                return true;
            }

            return feedsFilter.getEventPredicates().stream().anyMatch(eventPredicate -> eventPredicate.test(event));
        }).filter(event -> {
            if (CollectionUtils.isEmpty(feedsFilter.getParticipantPredicates())) {
                return true;
            }

            if (event.getParticipants() == null) {
                log.error("Teams for event are null: " + event);
                return false;
            }

            if (event.getParticipants().size() < 2) {
                return false;
            }

            return feedsFilter.getParticipantPredicates().stream()
                    .allMatch(teamPredicate -> event.getParticipants().stream().anyMatch(teamPredicate::test));
        });
    }

    public static boolean containsLeagueFilters(FeedsFilter feedsFilter) {
        return !CollectionUtils.isEmpty(feedsFilter.getLeaguePredicates()) || containsEventFilters(feedsFilter);
    }

    public static boolean containsEventFilters(FeedsFilter feedsFilter) {
        return !CollectionUtils.isEmpty(feedsFilter.getEventPredicates())
                || !CollectionUtils.isEmpty(feedsFilter.getParticipantPredicates());
    }
}
