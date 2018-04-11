package com.el.betting.sdk.v2.betoption.bettype.winner;

import com.el.betting.sdk.v2.Event;
import com.el.betting.sdk.v2.OddsFormat;
import com.el.betting.sdk.v2.Period;
import com.el.betting.sdk.v2.Stake;
import com.el.betting.sdk.v2.pages.BettingPage;
import com.el.betting.sdk.v4.Participant;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class RaceWinnerBetOptionBuilder {
    private Event<? extends com.el.betting.sdk.v4.Participant> event;
    private long eventID;
    private String selectionID;
    private String lineID;
    private Period period;
    private Participant participant;
    private String distance;
    private BigDecimal price;
    private BettingPage bettingPage;
    private Map<String, Object> additionalProperties;
    private OddsFormat oddsFormat;
    private Stake minStake;
    private Stake maxStake;

    public RaceWinnerBetOptionBuilder setEvent(Event<? extends Participant> event) {
        this.event = event;
        return this;
    }

    public RaceWinnerBetOptionBuilder setEventID(long eventID) {
        this.eventID = eventID;
        return this;
    }

    public RaceWinnerBetOptionBuilder setSelectionID(String selectionID) {
        this.selectionID = selectionID;
        return this;
    }

    public RaceWinnerBetOptionBuilder setLineID(String lineID) {
        this.lineID = lineID;
        return this;
    }

    public RaceWinnerBetOptionBuilder setPeriod(Period period) {
        this.period = period;
        return this;
    }

    public RaceWinnerBetOptionBuilder setParticipant(Participant participant) {
        this.participant = participant;
        return this;
    }

    public RaceWinnerBetOptionBuilder setDistance(String distance) {
        this.distance = distance;
        return this;
    }

    public RaceWinnerBetOptionBuilder setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public RaceWinnerBetOptionBuilder setBettingPage(BettingPage bettingPage) {
        this.bettingPage = bettingPage;
        return this;
    }

    public RaceWinnerBetOptionBuilder setAdditionalProperties(Map<String, Object> additionalProperties) {
        this.additionalProperties = additionalProperties;
        return this;
    }

    public RaceWinnerBetOptionBuilder setOddsFormat(OddsFormat oddsFormat) {
        this.oddsFormat = oddsFormat;
        return this;
    }

    public RaceWinnerBetOptionBuilder setMinStake(Stake minStake) {
        this.minStake = minStake;
        return this;
    }

    public RaceWinnerBetOptionBuilder setMaxStake(Stake maxStake) {
        this.maxStake = maxStake;
        return this;
    }

    public RaceWinnerBetOptionBuilder addAttribute(String name, Object value) {
        if(additionalProperties == null) {
            additionalProperties = new HashMap<>();
        }
        this.additionalProperties.put(name, value);
        return this;
    }

    public RaceWinnerBetOption createRaceWinnerBetOption() {
        return new RaceWinnerBetOption(event, eventID, selectionID, lineID, period,
                participant, distance,
                price, oddsFormat, bettingPage, minStake,
                maxStake, additionalProperties);
    }
}