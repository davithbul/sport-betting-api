package com.el.betting.sdk.v2.betoption.bettype.totalpoints;

import com.el.betting.sdk.v2.*;import com.el.betting.sdk.v4.*;
import com.el.betting.sdk.v2.pages.BettingPage;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class TotalPointsBetBuilder {
    private Event<? extends Participant> event;
    private long eventID;
    private String selectionID;
    private String lineID;
    private Period period;
    private TotalType totalType;
    private double points;
    private BigDecimal price;
    private BettingPage bettingPage;
    private Stake stake;
    private Map<String, Object> additionalProperties = new HashMap<>();
    private Stake minStake;
    private Stake maxStake;
    private OddsFormat oddsFormat = OddsFormat.DECIMAL;

    public TotalPointsBetBuilder setEvent(Event<? extends Participant> event) {
        this.event = event;
        return this;
    }

    public TotalPointsBetBuilder setEventID(long eventID) {
        this.eventID = eventID;
        return this;
    }

    public TotalPointsBetBuilder setSelectionID(String selectionID) {
        this.selectionID = selectionID;
        return this;
    }

    public TotalPointsBetBuilder setLineID(String lineID) {
        this.lineID = lineID;
        return this;
    }

    public TotalPointsBetBuilder setPeriod(Period period) {
        this.period = period;
        return this;
    }

    public TotalPointsBetBuilder setTotalType(TotalType totalType) {
        this.totalType = totalType;
        return this;
    }

    public TotalPointsBetBuilder setPoints(double points) {
        this.points = points;
        return this;
    }

    public TotalPointsBetBuilder setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public TotalPointsBetBuilder setBettingPage(BettingPage bettingPage) {
        this.bettingPage = bettingPage;
        return this;
    }

    public TotalPointsBetBuilder setStake(Stake stake) {
        this.stake = stake;
        return this;
    }

    public TotalPointsBetBuilder setAdditionalProperties(Map<String, Object> additionalProperties) {
        this.additionalProperties = additionalProperties;
        return this;
    }

    public TotalPointsBetBuilder addaAttribute(String name, String value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    public TotalPointsBetBuilder setMinStake(Stake minStake) {
        this.minStake = minStake;
        return this;
    }

    public TotalPointsBetBuilder setMaxStake(Stake maxStake) {
        this.maxStake = maxStake;
        return this;
    }

    public TotalPointsBetBuilder setOddsFormat(OddsFormat oddsFormat) {
        this.oddsFormat = oddsFormat;
        return this;
    }

    public TotalPointsBet createTotalPointsBet() {
        return new TotalPointsBet(event, eventID, selectionID, lineID, period, totalType, points, price, bettingPage, minStake, maxStake, oddsFormat, stake, additionalProperties);
    }
}