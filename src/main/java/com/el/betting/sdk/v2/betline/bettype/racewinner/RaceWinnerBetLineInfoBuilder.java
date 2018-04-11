package com.el.betting.sdk.v2.betline.bettype.racewinner;

import com.el.betting.common.CollectionUtil;
import com.el.betting.sdk.v2.*;
import com.el.betting.sdk.v4.Participant;
import com.el.betting.sdk.v4.race.WinnerCandidate;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class RaceWinnerBetLineInfoBuilder<T> {
    protected Event<? extends com.el.betting.sdk.v4.Participant> event;
    protected long eventID;
    protected String lineID;
    protected Period period;
    protected Stake maxStake;
    protected LocalDateTime startTime;
    protected List<WinnerCandidate> winnerCandidates = new LinkedList<>();
    protected String distance;
    protected OddsFormat oddsFormat;
    protected Map<String, Object> additionalProperties;

    public T setEventID(long eventID) {
        this.eventID = eventID;
        return (T) this;
    }

    public T setEvent(Event<? extends com.el.betting.sdk.v4.Participant> event) {
        this.event = event;
        return (T) this;
    }

    public T setLineID(String lineID) {
        this.lineID = lineID;
        return (T) this;
    }

    public T setPeriod(Period period) {
        this.period = period;
        return (T) this;
    }

    public T setMaxStake(Stake maxStake) {
        this.maxStake = maxStake;
        return (T) this;
    }

    public T setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
        return (T) this;
    }

    public T addWinnerCandidate(Participant participant, String selectionId) {
        WinnerCandidate winnerCandidate = new WinnerCandidate(participant, selectionId);
        winnerCandidates.add(winnerCandidate);
        return (T) this;
    }

    public T setOddsFormat(OddsFormat oddsFormat) {
        this.oddsFormat = oddsFormat;
        return (T) this;
    }

    public T setAdditionalProperties(Map<String, Object> additionalProperties) {
        this.additionalProperties = additionalProperties;
        return (T) this;
    }

    public T setDistance(String distance) {
        this.distance = distance;
        return (T) this;
    }

    public T addProperty(String name, Object value) {
        if (additionalProperties == null) {
            additionalProperties = new HashMap<>();
        }
        additionalProperties.put(name, value);
        return (T) this;
    }

    public RaceWinnerBetLineInfo createWinnerBetLineInfo() {
        RaceWinnerBetLineInfo winnerBetLineInfo = new RaceWinnerBetLineInfo(eventID, lineID, period, maxStake, startTime, winnerCandidates,
                distance, oddsFormat,
                CollectionUtil.safeMap(additionalProperties));
        winnerBetLineInfo.setEvent(event.cloneWithoutBetLines());
        return winnerBetLineInfo;
    }
}