package com.el.betting.sdk.v2.betline.api;


import com.el.betting.sdk.v2.*;import com.el.betting.sdk.v4.*;
import com.el.betting.sdk.v2.betoption.api.BetOptionInfo;
import com.el.betting.sdk.v4.Participant;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * BetLineMeta represents the line of game options meta for the event for the given period.
 * This is like a group of bet options / possible bets for the same event
 * in the same time with opposite or partially overlapping (e.g. double chance) outcomes.
 * It's possible to get each possible bet option metadata by calling BetLine#getBetOptionsInfoList.
 * In other words, each BetLineMeta contains metadata about betOptions which are part of the same line (i.e. lineID)
 * <p>
 * E.G.
 * In Real vs Barca game, the money line game lines might be considered
 * Match Winner - Real - odd1 vs Draw - odd2 vs Barca - odd3 (bet line1)
 * First half Winner - Real - odd1 vs Draw - odd2 vs Barca - odd3 (bet line2)
 *
 * For different type of game lines the representation might be different
 */
public abstract class BetLineInfo<T extends BetOptionInfo> implements Serializable {
    private final String lineID;
    private final long eventID;
    private final Period period;
    private final BetType betType;
    private final Stake maxStake;
    private final OddsFormat oddsFormat;
    private final LocalDateTime startTime;
    private Event<? extends Participant> event;
    private final Map<String, Object> additionalProperties;

    protected BetLineInfo(BetLineInfo betLine) {
        this.eventID = betLine.getEventID();
        this.lineID = betLine.getLineID();
        this.period = betLine.getPeriod();
        this.betType = betLine.getBetType();
        this.maxStake = betLine.getMaxStake();
        this.oddsFormat = betLine.getOddsFormat();
        this.startTime = betLine.getStartTime();
        this.additionalProperties = betLine.getAdditionalProperties();
    }

    protected BetLineInfo(long eventID, String lineID, Period period, BetType betType, Stake maxStake, OddsFormat oddsFormat, LocalDateTime startTime, Map<String, Object> additionalProperties) {
        this.eventID = eventID;
        this.lineID = lineID;
        this.period = period;
        this.betType = betType;
        this.maxStake = maxStake;
        this.oddsFormat = oddsFormat;
        this.startTime = startTime;
        this.additionalProperties = additionalProperties;
    }

    public long getEventID() {
        return eventID;
    }

    public Event<? extends Participant> getEvent() {
        return event;
    }

    public void setEvent(Event<? extends Participant> event) {
        this.event = event;
    }

    public String getLineID() {
        return lineID;
    }

    public Period getPeriod() {
        return period;
    }

    public BetType getBetType() {
        return betType;
    }

    public Stake getMaxStake() {
        return maxStake;
    }

    public OddsFormat getOddsFormat() {
        return oddsFormat;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public Map<String, Object> getAdditionalProperties() {
        return additionalProperties;
    }

    @JsonIgnore
    public abstract List<T> getBetOptionsInfoList();

    @Override
    public String toString() {
        return "BetOption{" +
                "eventID=" + eventID +
                ", lineID=" + lineID +
                ", periodType=" + period +
                ", betType=" + betType +
                ", maxStake=" + maxStake +
                ", oddsFormat=" + oddsFormat +
                ", startTime=" + startTime +
                '}';
    }
}
