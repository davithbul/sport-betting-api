package com.el.betting.sdk.v2.betoption.bettype.spread;

import com.el.betting.sdk.v2.*;import com.el.betting.sdk.v4.*;
import com.el.betting.sdk.v2.betoption.api.AbstractBetOptionInfo;
import com.el.betting.sdk.v2.pages.BettingPage;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class SpreadBetOptionBuilder {
    private Event<? extends Participant> event;
    private long eventID;
    private String selectionID;
    private String lineID;
    private Period period;
    private Team team;
    private BigDecimal spread;
    private BigDecimal price;
    private OddsFormat oddsFormat;
    private BettingPage bettingPage;
    private Map<String, Object> additionalProperties;
    private Stake minStake;
    private Stake maxStake;

    public SpreadBetOptionBuilder setEvent(Event<? extends Participant> event) {
        this.event = event;
        return this;
    }

    public SpreadBetOptionBuilder setEventID(long eventID) {
        this.eventID = eventID;
        return this;
    }

    public SpreadBetOptionBuilder setSelectionID(String selectionID) {
        this.selectionID = selectionID;
        return this;
    }

    public SpreadBetOptionBuilder setLineID(String lineID) {
        this.lineID = lineID;
        return this;
    }

    public SpreadBetOptionBuilder setPeriod(Period period) {
        this.period = period;
        return this;
    }

    public SpreadBetOptionBuilder setTeam(Team team) {
        this.team = team;
        return this;
    }

    public SpreadBetOptionBuilder setSpread(BigDecimal spread) {
        this.spread = spread;
        return this;
    }

    public SpreadBetOptionBuilder setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public SpreadBetOptionBuilder setOddsFormat(OddsFormat oddsFormat) {
        this.oddsFormat = oddsFormat;
        return this;
    }

    public SpreadBetOptionBuilder setBettingPage(BettingPage bettingPage) {
        this.bettingPage = bettingPage;
        return this;
    }

    public SpreadBetOptionBuilder setAdditionalProperties(Map<String, Object> additionalProperties) {
        this.additionalProperties = additionalProperties;
        return this;
    }

    public SpreadBetOptionBuilder setMinStake(Stake minStake) {
        this.minStake = minStake;
        return this;
    }

    public SpreadBetOptionBuilder setMaxStake(Stake maxStake) {
        this.maxStake = maxStake;
        return this;
    }

    public SpreadBetOptionBuilder addAttribute(String name, Object value) {
        if(additionalProperties == null) {
            additionalProperties = new HashMap<>();
        }
        this.additionalProperties.put(name, value);
        return this;
    }

    public SpreadBetOption createDefaultSpreadBetOptionInfo() {
        return new SpreadBetOption(event, eventID, selectionID, lineID, period, team, spread, price, oddsFormat,
                bettingPage, minStake, maxStake, additionalProperties);
    }
}