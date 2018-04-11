package com.el.betting.sdk.v2.betoption.bettype.totalpoints;

import com.el.betting.sdk.v2.*;import com.el.betting.sdk.v4.*;
import com.el.betting.sdk.v2.betoption.api.AbstractBetOptionInfo;
import com.el.betting.sdk.v2.betoption.api.BetOptionInfo;
import com.el.betting.sdk.v2.pages.BettingPage;
import com.el.betting.sdk.v4.Participant;

import java.util.Map;

public class DefaultTotalPointsBetOptionInfo extends AbstractBetOptionInfo implements BetOptionInfo, TotalPointsBetOptionInfo {

    private final TotalType totalType;
    private final double points;

    public DefaultTotalPointsBetOptionInfo(Event<? extends Participant> event, long eventID, String selectionID, String lineID, Period period, TotalType totalType, double points, BettingPage bettingPage, Map<String, Object> additionalProperties) {
        super(event, eventID, selectionID, lineID, period, bettingPage, additionalProperties);
        this.totalType = totalType;
        this.points = points;
    }

    public DefaultTotalPointsBetOptionInfo(Event<? extends Participant> event, long eventID, String selectionID, String lineID, Period period, TotalType totalType, double points, BettingPage bettingPage, Stake minStake, Stake maxStake, Map<String, Object> additionalProperties) {
        super(event, eventID, selectionID, lineID, period, bettingPage, minStake, maxStake, additionalProperties);
        this.totalType = totalType;
        this.points = points;
    }

    public DefaultTotalPointsBetOptionInfo(AbstractBetOptionInfo betOptionInfo, TotalType totalType, double points) {
        super(betOptionInfo);
        this.totalType = totalType;
        this.points = points;
    }

    @Override
    public TotalType getTotalType() {
        return totalType;
    }

    @Override
    public double getPoints() {
        return points;
    }

    @Override
    public BetType getBetType() {
        return BetType.TOTAL_POINTS;
    }

    @Override
    public String toString() {
        return "DefaultTotalPointsBetOptionInfo{" +
                "totalType=" + totalType +
                ", points=" + points +
                "} " + super.toString();
    }
}
