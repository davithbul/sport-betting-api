package com.el.betting.sdk.v2.betoption.bettype.moneyline;

import com.el.betting.common.CollectionUtil;
import com.el.betting.sdk.v2.*;import com.el.betting.sdk.v4.*;
import com.el.betting.sdk.v2.betoption.api.AbstractBetOptionInfo;
import com.el.betting.sdk.v2.pages.BettingPage;
import com.el.betting.sdk.v4.Participant;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MoneyLineBetOptionInfoBuilder<T extends MoneyLineBetOptionInfoBuilder> {
    protected Event<? extends Participant> event;
    protected long eventID;
    protected String selectionID;
    protected String lineID;
    protected Period period;
    protected Team team;
    protected BettingPage bettingPage;
    protected Stake minStake;
    protected Stake maxStake;
    protected Map<String, Object> additionalProperties;

    public T setEvent(Event<? extends Participant> event) {
        this.event = event;
        return (T) this;
    }

    public T setEventID(long eventID) {
        this.eventID = eventID;
        return (T) this;
    }

    public T setSelectionID(String selectionID) {
        this.selectionID = selectionID;
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

    public T setTeam(Team team) {
        this.team = team;
        return (T) this;
    }

    public T setBettingPage(BettingPage bettingPage) {
        this.bettingPage = bettingPage;
        return (T) this;
    }

    public T setMinStake(Stake minStake) {
        this.minStake = minStake;
        return (T) this;
    }

    public T setMaxStake(Stake maxStake) {
        this.maxStake = maxStake;
        return (T) this;
    }

    public T setAdditionalProperties(Map<String, Object> additionalProperties) {
        this.additionalProperties = additionalProperties;
        return (T) this;
    }

    public T addAttribute(String name, Object value) {
        if(additionalProperties == null) {
            additionalProperties = new HashMap<>();
        }
        this.additionalProperties.put(name, value);
        return (T) this;
    }

    public MoneyLineBetOptionInfo createMoneyLineBetOptionInfo() {
        return new DefaultMoneyLineBetOptionInfo(event, eventID, selectionID, lineID, period, team, bettingPage,
                minStake, maxStake,
                CollectionUtil.safeMap(additionalProperties)
        );
    }
}