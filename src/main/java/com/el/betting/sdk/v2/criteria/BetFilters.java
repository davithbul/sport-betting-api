package com.el.betting.sdk.v2.criteria;

import com.el.betting.sdk.v2.BetExchangeType;
import com.el.betting.sdk.v2.Team;
import com.el.betting.sdk.v2.TimeRange;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class BetFilters {
    private Set<String> betIDs;
    private String betStatus;
    private com.el.betting.sdk.v2.TimeRange<LocalDateTime> timeRange;
    private Team.Side side;
    private BetExchangeType exchangeType;
    private Set<Long> sportIds;
    private Set<Long> leagueIds;
    private Set<Long> eventIds;
    private Set<String> selectionIds;

    public String getBetStatus() {
        return betStatus;
    }

    public void setBetStatus(String betStatus) {
        this.betStatus = betStatus;
    }

    public Set<String> getBetIDs() {
        return betIDs;
    }

    public void addBetID(String betID) {
        if (betIDs == null) {
            betIDs = new HashSet<>();
        }
        betIDs.add(betID);
    }

    public void setBetIDs(Set<String> betIDs) {
        this.betIDs = betIDs;
    }

    public TimeRange<LocalDateTime> getTimeRange() {
        return timeRange;
    }

    public void setTimeRange(TimeRange<LocalDateTime> timeRange) {
        this.timeRange = timeRange;
    }

    public Team.Side getSide() {
        return side;
    }

    public void setSide(Team.Side side) {
        this.side = side;
    }

    public BetExchangeType getExchangeType() {
        return exchangeType;
    }

    public void setExchangeType(BetExchangeType exchangeType) {
        this.exchangeType = exchangeType;
    }

    public Set<Long> getSportIds() {
        return sportIds;
    }

    public void setSportIds(Set<Long> sportIds) {
        this.sportIds = sportIds;
    }

    public Set<Long> getLeagueIds() {
        return leagueIds;
    }

    public void setLeagueIds(Set<Long> leagueIds) {
        this.leagueIds = leagueIds;
    }

    public Set<Long> getEventIds() {
        return eventIds;
    }

    public void setEventIds(Set<Long> eventIds) {
        this.eventIds = eventIds;
    }

    public Set<String> getSelectionIds() {
        return selectionIds;
    }

    public void setSelectionIds(Set<String> selectionIds) {
        this.selectionIds = selectionIds;
    }
}
