package com.el.betting.sdk.v2.betoption.bettype.spread;

import com.el.betting.sdk.v2.*;import com.el.betting.sdk.v4.*;
import com.el.betting.sdk.v2.betoption.api.AbstractBetOptionInfo;
import com.el.betting.sdk.v2.betoption.api.BetOptionInfo;
import com.el.betting.sdk.v2.pages.BettingPage;
import com.el.betting.sdk.v4.Participant;

import java.math.BigDecimal;
import java.util.Map;

public class DefaultSpreadBetOptionInfo extends AbstractBetOptionInfo implements BetOptionInfo, SpreadBetOptionInfo {

    private final Team team;
    private final BigDecimal spread;

    public DefaultSpreadBetOptionInfo(Event<? extends Participant> event, long eventID, String selectionID, String lineID, Period period, Team team, BigDecimal spread, BettingPage bettingPage, Map<String, Object> additionalProperties) {
        super(event, eventID, selectionID, lineID, period, bettingPage, additionalProperties);
        this.team = team;
        this.spread = spread;
    }

    public DefaultSpreadBetOptionInfo(Event<? extends Participant> event, long eventID, String selectionID, String lineID, Period period, Team team, BigDecimal spread, BettingPage bettingPage, Stake minStake, Stake maxStake, Map<String, Object> additionalProperties) {
        super(event, eventID, selectionID, lineID, period, bettingPage, minStake, maxStake, additionalProperties);
        this.team = team;
        this.spread = spread;
    }

    public DefaultSpreadBetOptionInfo(AbstractBetOptionInfo betOptionInfo, Team team, BigDecimal spread) {
        super(betOptionInfo);
        this.team = team;
        this.spread = spread;
    }

    public BigDecimal getSpread() {
        return spread;
    }

    @Override
    public BetType getBetType() {
        return BetType.HANDICAP;
    }

    public Team getTeam() {
        return team;
    }

    @Override
    public String toString() {
        return "DefaultSpreadBetOptionInfo{" +
                "team=" + team +
                ", spread=" + spread +
                "} " + super.toString();
    }
}
