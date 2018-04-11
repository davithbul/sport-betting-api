package com.el.betting.sdk.v2.betoption.bettype.moneyline;

import com.el.betting.sdk.v2.*;import com.el.betting.sdk.v4.*;
import com.el.betting.sdk.v2.betoption.api.AbstractBetOptionInfo;
import com.el.betting.sdk.v2.betoption.api.BetOptionInfo;
import com.el.betting.sdk.v2.pages.BettingPage;
import com.el.betting.sdk.v4.Participant;

import java.util.Map;


public class DefaultMoneyLineBetOptionInfo extends AbstractBetOptionInfo implements MoneyLineBetOptionInfo {

    private final Team team;

    protected DefaultMoneyLineBetOptionInfo(Event<? extends Participant> event, long eventID, String selectionID, String lineID, Period period, Team team, BettingPage bettingPage, Stake minStake, Stake maxStake, Map<String, Object> additionalProperties) {
        super(event, eventID, selectionID, lineID, period, bettingPage, minStake, maxStake, additionalProperties);
        this.team = team;
    }

    public DefaultMoneyLineBetOptionInfo(MoneyLineBetOptionInfo betOptionInfo) {
        super(betOptionInfo.getEvent(), betOptionInfo.getEventID(), betOptionInfo.getSelectionID(), betOptionInfo.getLineID(),
                betOptionInfo.getPeriod(), betOptionInfo.getBettingPage(), betOptionInfo.getMinStake(), betOptionInfo.getMaxStake(),
                betOptionInfo.getAdditionalProperties());
        this.team = betOptionInfo.getTeam();
    }

    public DefaultMoneyLineBetOptionInfo(BetOptionInfo betOptionInfo, Team team) {
        super(betOptionInfo);
        this.team = team;
    }

    public Team getTeam() {
        return team;
    }

    @Override
    public BetType getBetType() {
        return BetType.MONEY_LINE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DefaultMoneyLineBetOptionInfo)) return false;
        if (!super.equals(o)) return false;

        DefaultMoneyLineBetOptionInfo that = (DefaultMoneyLineBetOptionInfo) o;

        return !(team != null ? !team.equals(that.team) : that.team != null);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (team != null ? team.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "MoneyLineBetLine{" +
                "team=" + team +
                "} " + super.toString();
    }
}
