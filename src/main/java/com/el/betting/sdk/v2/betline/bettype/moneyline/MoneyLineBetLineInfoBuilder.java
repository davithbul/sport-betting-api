package com.el.betting.sdk.v2.betline.bettype.moneyline;

import com.el.betting.common.CollectionUtil;
import com.el.betting.sdk.v2.*;import com.el.betting.sdk.v4.*;
import com.el.betting.sdk.v2.common.PropertyHelper;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class MoneyLineBetLineInfoBuilder<T extends MoneyLineBetLineInfoBuilder> {
    protected Event<? extends Participant> event;
    protected long eventID;
    protected String lineID;
    protected Period period;
    protected BetType betType;
    protected Stake maxStake;
    protected OddsFormat oddsFormat;
    protected LocalDateTime startTime;
    protected Map<String, Object> additionalProperties;
    protected Team homeTeam;
    protected String homeTeamSelectionId;
    protected String drawSelectionId;
    protected Team awayTeam;
    protected String awayTeamSelectionId;

    public T setEvent(Event<? extends Participant> event) {
        this.event = event;
        return (T)this;
    }

    public T setEventID(long eventID) {
        this.eventID = eventID;
        return (T)this;
    }

    public T setLineID(String lineID) {
        this.lineID = lineID;
        return (T)this;
    }

    public T setPeriod(Period period) {
        this.period = period;
        return (T)this;
    }

    public T setBetType(BetType betType) {
        this.betType = betType;
        return (T)this;
    }

    public T setMaxStake(Stake maxStake) {
        this.maxStake = maxStake;
        return (T)this;
    }

    public T setOddsFormat(OddsFormat oddsFormat) {
        this.oddsFormat = oddsFormat;
        return (T)this;
    }

    public T setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
        return (T)this;
    }

    public T addProperty(PropertyType propertyType, String name, Object value) {
        String propertyName = PropertyHelper.propertyName(propertyType, name);
        addProperty(propertyName, value);
        return (T)this;
    }

    public T addProperty(String name, Object value) {
        if(additionalProperties == null) {
            additionalProperties = new HashMap<>();
        }
        additionalProperties.put(name, value);
        return (T)this;
    }

    public T addAdditionalProperties(Map<String, Object> properties) {
        if(additionalProperties == null) {
            additionalProperties = new HashMap<>();
        }
        additionalProperties.putAll(properties);
        return (T) this;
    }

    public T setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
        return (T)this;
    }

    public T setHomeTeamSelectionId(String homeTeamSelectionId) {
        this.homeTeamSelectionId = homeTeamSelectionId;
        return (T)this;
    }

    public T setDrawSelectionId(String drawSelectionId) {
        this.drawSelectionId = drawSelectionId;
        return (T)this;
    }

    public T setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
        return (T)this;
    }

    public T setAwayTeamSelectionId(String awayTeamSelectionId) {
        this.awayTeamSelectionId = awayTeamSelectionId;
        return (T)this;
    }

    public MoneyLineBetLineInfo createMoneyLineBetLineInfo() {
        MoneyLineBetLineInfo moneyLineBetLineInfo = new MoneyLineBetLineInfo(eventID, lineID, period, betType, maxStake, oddsFormat, startTime,
                CollectionUtil.safeMap(additionalProperties),
                homeTeam, homeTeamSelectionId, drawSelectionId, awayTeam, awayTeamSelectionId);

        moneyLineBetLineInfo.setEvent(event.cloneWithoutBetLines());
        return moneyLineBetLineInfo;
    }
}