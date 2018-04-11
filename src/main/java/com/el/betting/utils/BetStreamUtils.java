package com.el.betting.utils;

import com.el.betting.common.CollectionUtil;
import com.el.betting.common.StreamUtils;
import com.el.betting.sdk.v2.*;
import com.el.betting.sdk.common.SportType;
import com.el.betting.sdk.v4.Participant;
import com.el.betting.sdk.v4.race.HorseRaceParticipant;
import org.apache.commons.collections4.CollectionUtils;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

public class BetStreamUtils {

    public static List<Sport> mergeSports(BiFunction<List<League>, List<League>, List<League>> leaguesMergingStrategy, Sport... sports) {
        return mergeSports(leaguesMergingStrategy, Arrays.asList(sports));
    }

    public static List<Sport> mergeSports(BiFunction<List<League>, List<League>, List<League>> leaguesMergingStrategy, List<Sport> sports) {
        Map<SportType, Optional<Sport>> sportsMap =
                sports.stream()
                        .collect(Collectors.groupingBy(Sport::getSportType,
                                Collectors.reducing((Sport sport1, Sport sport2) -> {
                                    List<League> leagues = leaguesMergingStrategy.apply(sport1.getLeagues(), sport2.getLeagues());
                                    sport1.setLeagues(leagues);
                                    return sport1;
                                })));

        return StreamUtils.getValueList(sportsMap);
    }

    public static List<League> mergeLeagueLists(List<League> leagues1, List<League> leagues2) {
        List<League> leagues = CollectionUtil.addAll(leagues1, leagues2);
        Map<String, Optional<League>> leaguesMap = leagues.stream()
                .filter(League::isHasContent)
                .filter(league -> league.getName() != null)
                .collect(Collectors.groupingBy(League::getName,
                        Collectors.reducing((league1, league2) -> {
                            List<Event<? extends Participant>> events = mergeEvents(league1.getEvents(), league2.getEvents());
                            league1.setEvents(events);
                            return league1;
                        })));
        return StreamUtils.getValueList(leaguesMap);
    }

    public static List<League> mergeLeagueListsByID(List<League> leagues1, List<League> leagues2) {
        ArrayList<League> leagues = new ArrayList<>(leagues1);
        leagues.addAll(leagues2);
        return BetStreamUtils.mergeLeagues(leagues, League::getId, BetStreamUtils::simpleMergeEvents);
    }


    public static List<League> mergeLeagues(List<League> leagues, Function<League, ? extends Object> groupBy, BiFunction<List<Event<? extends Participant>>, List<Event<? extends Participant>>, List<Event<? extends Participant>>> eventMergingStrategy) {
        Map<Object, Optional<League>> leaguesMap = leagues.stream()
                .collect(Collectors.groupingBy(groupBy,
                        Collectors.reducing((league1, league2) -> {
                            List<Event<? extends Participant>> events = eventMergingStrategy.apply(league1.getEvents(), league2.getEvents());
                            league1.setEvents(events);
                            return league1;
                        })));
        return StreamUtils.getValueList(leaguesMap);
    }

    public static List<Event<? extends Participant>> simpleMergeEvents(List<Event<? extends Participant>> events1, List<Event<? extends Participant>> events2) {
        List<Event<? extends Participant>> events = CollectionUtil.addAll(events1, events2);
        return events;
    }

    public static List<Event<? extends Participant>> mergeEvents(List<Event<? extends Participant>> events1, List<Event<? extends Participant>> events2) {
        List<Event<? extends Participant>> events = CollectionUtil.addAll(events1, events2);
        return mergeEvents(events);
    }

    public static List<Event<? extends Participant>> mergeEventsById(List<Event<? extends Participant>> events1, List<Event<? extends Participant>> events2) {
        List<Event<? extends Participant>> events = CollectionUtil.addAll(events1, events2);
        return mergeEventsById(events);
    }

    public static List<Event<? extends Participant>> mergeEvents(List<Event<? extends Participant>> events) {
        Map<String, Optional<Event<? extends Participant>>> eventsMap = events.stream()
                .collect(Collectors.groupingBy(BetStreamUtils::getEventIdentifier,
                        Collectors.reducing((event1, event2) -> {
                            mergeBetOptions(event1, event2);
                            return event1;
                        })));
        return StreamUtils.getValueList(eventsMap);
    }

    public static List<Event<? extends Participant>> mergeEventsById(List<Event<? extends Participant>> events) {
        return events.stream()
                .collect(Collectors.groupingBy(Event::getId,
                        Collectors.reducing((event1, event2) -> {
                            mergeBetOptions(event1, event2);
                            return event1;
                        })))
                .values()
                .stream()
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    public static List<Event<HorseRaceParticipant>> mergeRacesById(List<Event<HorseRaceParticipant>> events) {
        return events.stream()
                .collect(Collectors.groupingBy(Event::getId,
                        Collectors.reducing((event1, event2) -> {
                            mergeBetOptions(event1, event2);
                            if(event1.getStartTime().isAfter(event2.getStartTime())) {
                                event1.setStartTime(event2.getStartTime());
                            }
                            TreeSet<HorseRaceParticipant> participants = new TreeSet<>((o1, o2) -> o1.getName().compareTo(o2.getName()));
                            participants.addAll(event1.getParticipants());
                            participants.addAll(event2.getParticipants());
                            List<HorseRaceParticipant> mergedParticipants = new ArrayList<>(participants);
                            event1.setParticipants(mergedParticipants);
                            return event1;
                        })))
                .values()
                .stream()
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    public static void mergeBetOptions(Event<? extends Participant> event1, Event<? extends Participant> event2) {
        event1.getBetLines().addAll(event2.getBetLines());

        if (CollectionUtils.isNotEmpty(event2.getLayBackBetLines())) {
            if (CollectionUtils.isNotEmpty(event1.getLayBackBetLines())) {
                event1.getLayBackBetLines().addAll(event2.getLayBackBetLines());
            } else {
                event1.setLayBackBetLines(event2.getLayBackBetLines());
            }
        }
    }

    public static String getEventIdentifier(Event<? extends Participant> event) {
        StringBuilder eventIdentifier = new StringBuilder();
        event.getParticipants()
                .stream()
                .filter(participant -> !(participant instanceof Team) || ((Team) participant).getSide() != Team.Side.DRAW)
                .sorted((team1, team2) -> team1.getName().compareTo(team2.getName()))
                .forEach(team -> eventIdentifier.append("|").append(team.getName()));

        eventIdentifier.append(event.getStartTime().getMonthValue());
        eventIdentifier.append(event.getStartTime().getDayOfMonth());
        return eventIdentifier.toString();
    }

    public static String getEventTeamsIdentifier(Event<? extends Participant> event) {
        StringBuilder eventIdentifier = new StringBuilder();
        event.getParticipants()
                .stream()
                .filter(participant -> !(participant instanceof Team) || ((Team) participant).getSide() != Team.Side.DRAW)
                .sorted((team1, team2) -> team1.getName().compareTo(team2.getName()))
                .forEach(team -> eventIdentifier.append("|").append(team.getName()));

        eventIdentifier.append(event.getStartTime().getMonthValue());
        return eventIdentifier.toString();
    }
}
