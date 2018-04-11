package com.el.betting.sdk.v2.criteria;

import com.el.betting.common.SimilarityUtils;
import com.el.betting.sdk.v2.Event;
import com.el.betting.sdk.v2.League;
import com.el.betting.sdk.common.SportType;
import com.el.betting.sdk.v4.Participant;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

public class FeedsFilter {
    private SportType sportType;
    private Set<Predicate<League>> leaguePredicates = new HashSet<>();
    private Set<Predicate<Event>> eventPredicates = new HashSet<>();
    private Set<Predicate<Participant>> participantPredicates = new HashSet<>();

    public SportType getSportType() {
        return sportType;
    }

    public void setSportType(SportType sportType) {
        this.sportType = sportType;
    }

    public Set<Predicate<League>> getLeaguePredicates() {
        return leaguePredicates;
    }

    public void setLeaguePredicates(Set<Predicate<League>> leaguePredicates) {
        this.leaguePredicates = leaguePredicates;
    }

    public void addLeaguePredicate(Predicate<League> leaguePredicate) {
        leaguePredicates.add(leaguePredicate);
    }

    public Set<Predicate<Participant>> getParticipantPredicates() {
        return participantPredicates;
    }

    public void setParticipantPredicates(Set<Predicate<Participant>> participantPredicates) {
        this.participantPredicates = participantPredicates;
    }

    public void addParticipantPredicate(Predicate<Participant> participantPredicate) {
        participantPredicates.add(participantPredicate);
    }

    public void setLeagueNames(Set<String> leagueNames) {
        leagueNames.forEach(leagueName -> leaguePredicates.add((league) -> SimilarityUtils.isSimilar(league.getName(), leagueName)));
    }

    public void addLeagueName(String leagueName) {
        leaguePredicates.add((league) -> SimilarityUtils.isSimilar(league.getName(), leagueName));
    }

    public Set<Predicate<Event>> getEventPredicates() {
        return eventPredicates;
    }

    public void addEventPredicate(Predicate<Event> eventPredicate) {
        eventPredicates.add(eventPredicate);
    }

    public void setEventPredicates(Set<Predicate<Event>> eventPredicates) {
        this.eventPredicates = eventPredicates;
    }

    public void setParticipants(Set<? extends Participant> participants) {
        participants.forEach(participantItem -> participantPredicates.add((participant) -> participant.equals(participantItem)));
    }

    public void addParticipant(Participant participant) {
        participantPredicates.add((participantItem) -> participantItem.equals(participant));
    }

    public void setParticipantNames(Set<String> participantNames) {
        participantNames.forEach(participantName -> participantPredicates.add((participant) -> SimilarityUtils.isSimilar(participant.getName(), participantName)));
    }

    public void addParticipant(String participantName) {
        participantPredicates.add((participant) -> SimilarityUtils.isSimilar(participant.getName(), participantName));
    }
}
