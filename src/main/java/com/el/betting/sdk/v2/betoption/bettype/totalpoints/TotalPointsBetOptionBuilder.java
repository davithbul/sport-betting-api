package com.el.betting.sdk.v2.betoption.bettype.totalpoints;

import com.el.betting.sdk.v2.*;import com.el.betting.sdk.v4.*;
import com.el.betting.sdk.v2.pages.BettingPage;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class TotalPointsBetOptionBuilder {
    private Event<? extends Participant> event;
    private long eventID;
    private String selectionID;
    private String lineID;
    private Period period;
    private TotalType totalType;
    private double points;
    private BigDecimal price;
    private BettingPage bettingPage;
    private Map<String, Object> additionalProperties;
    private Stake minStake;
    private Stake maxStake;
    private OddsFormat oddsFormat = OddsFormat.DECIMAL;

    public TotalPointsBetOptionBuilder setEvent(Event<? extends Participant> event) {
        this.event = event;
        return this;
    }

    public TotalPointsBetOptionBuilder setEventID(long eventID) {
        this.eventID = eventID;
        return this;
    }

    public TotalPointsBetOptionBuilder setSelectionID(String selectionID) {
        this.selectionID = selectionID;
        return this;
    }

    public TotalPointsBetOptionBuilder setLineID(String lineID) {
        this.lineID = lineID;
        return this;
    }

    public TotalPointsBetOptionBuilder setPeriod(Period period) {
        this.period = period;
        return this;
    }

    public TotalPointsBetOptionBuilder setTotalType(TotalType totalType) {
        this.totalType = totalType;
        return this;
    }

    public TotalPointsBetOptionBuilder setPoints(double points) {
        this.points = points;
        return this;
    }

    public TotalPointsBetOptionBuilder setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public TotalPointsBetOptionBuilder setBettingPage(BettingPage bettingPage) {
        this.bettingPage = bettingPage;
        return this;
    }

    public TotalPointsBetOptionBuilder setAdditionalProperties(Map<String, Object> additionalProperties) {
        this.additionalProperties = additionalProperties;
        return this;
    }

    public TotalPointsBetOptionBuilder addAttribute(String name, Object value) {
        if(additionalProperties == null) {
            additionalProperties = new HashMap<>();
        }
        this.additionalProperties.put(name, value);
        return this;
    }

    public TotalPointsBetOptionBuilder setMinStake(Stake minStake) {
        this.minStake = minStake;
        return this;
    }

    public TotalPointsBetOptionBuilder setMaxStake(Stake maxStake) {
        this.maxStake = maxStake;
        return this;
    }

    public TotalPointsBetOptionBuilder setOddsFormat(OddsFormat oddsFormat) {
        this.oddsFormat = oddsFormat;
        return this;
    }

    public TotalPointsBetOption createTotalPointsBetOption() {
        return new TotalPointsBetOption(event, eventID, selectionID, lineID, period, totalType, points, price, oddsFormat, bettingPage, minStake, maxStake, additionalProperties);
    }
}