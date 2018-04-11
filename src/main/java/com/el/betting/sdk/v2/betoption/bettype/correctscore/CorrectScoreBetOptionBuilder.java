package com.el.betting.sdk.v2.betoption.bettype.correctscore;

import com.el.betting.sdk.v2.Event;import com.el.betting.sdk.v4.*;
import com.el.betting.sdk.v2.OddsFormat;
import com.el.betting.sdk.v2.Period;
import com.el.betting.sdk.v2.Stake;
import com.el.betting.sdk.v2.pages.BettingPage;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class CorrectScoreBetOptionBuilder {
    private Event<? extends Participant> event;
    private long eventID;
    private String selectionID;
    private String lineID;
    private Period period;
    private int homeSideScore;
    private int awaySidePoints;
    private BigDecimal price;
    private BettingPage bettingPage;
    private Map<String, Object> additionalProperties;
    private OddsFormat oddsFormat;
    private Stake minStake;
    private Stake maxStake;

    public CorrectScoreBetOptionBuilder setEvent(Event<? extends Participant> event) {
        this.event = event;
        return this;
    }

    public CorrectScoreBetOptionBuilder setEventID(long eventID) {
        this.eventID = eventID;
        return this;
    }

    public CorrectScoreBetOptionBuilder setSelectionID(String selectionID) {
        this.selectionID = selectionID;
        return this;
    }

    public CorrectScoreBetOptionBuilder setLineID(String lineID) {
        this.lineID = lineID;
        return this;
    }

    public CorrectScoreBetOptionBuilder setPeriod(Period period) {
        this.period = period;
        return this;
    }

    public CorrectScoreBetOptionBuilder setHomeSideScore(int homeSideScore) {
        this.homeSideScore = homeSideScore;
        return this;
    }

    public CorrectScoreBetOptionBuilder setAwaySidePoints(int awaySidePoints) {
        this.awaySidePoints = awaySidePoints;
        return this;
    }

    public CorrectScoreBetOptionBuilder setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public CorrectScoreBetOptionBuilder setBettingPage(BettingPage bettingPage) {
        this.bettingPage = bettingPage;
        return this;
    }

    public CorrectScoreBetOptionBuilder setAdditionalProperties(Map<String, Object> additionalProperties) {
        this.additionalProperties = additionalProperties;
        return this;
    }

    public CorrectScoreBetOptionBuilder setOddsFormat(OddsFormat oddsFormat) {
        this.oddsFormat = oddsFormat;
        return this;
    }

    public CorrectScoreBetOptionBuilder setMinStake(Stake minStake) {
        this.minStake = minStake;
        return this;
    }

    public CorrectScoreBetOptionBuilder setMaxStake(Stake maxStake) {
        this.maxStake = maxStake;
        return this;
    }

    public CorrectScoreBetOptionBuilder addAttribute(String name, Object value) {
        if(additionalProperties == null) {
            additionalProperties = new HashMap<>();
        }
        this.additionalProperties.put(name, value);
        return this;
    }

    public CorrectScoreBetOption createCorrectScoreBetOption() {
        return new CorrectScoreBetOption(event, eventID, selectionID, lineID, period, homeSideScore, awaySidePoints, price, oddsFormat, bettingPage, minStake,
                maxStake, additionalProperties);
    }
}