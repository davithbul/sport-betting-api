package com.el.betting.sdk.v2.betoption.bettype.teampoints;

import com.el.betting.sdk.v2.*;import com.el.betting.sdk.v4.*;
import com.el.betting.sdk.v2.betoption.api.AbstractBetOptionInfo;
import com.el.betting.sdk.v2.betoption.api.BetOptionInfo;
import com.el.betting.sdk.v2.pages.BettingPage;

import java.math.BigDecimal;
import java.util.Map;

public class DefaultTeamPointsBetOptionInfo extends AbstractBetOptionInfo implements BetOptionInfo, TeamPointsBetOptionInfo {

    private final Team team;
    private final TotalType totalType;
    private final double points;

    public DefaultTeamPointsBetOptionInfo(Event<? extends Participant> event, long eventID, String selectionID, String lineID, Period period, Team team, TotalType totalType, double points, BettingPage bettingPage, Map<String, Object> additionalProperties) {
        super(event, eventID, selectionID, lineID, period, bettingPage, additionalProperties);
        this.team = team;
        this.totalType = totalType;
        this.points = points;
    }

    public DefaultTeamPointsBetOptionInfo(Event<? extends Participant> event, long eventID, String selectionID, String lineID, Period period, Team team, TotalType totalType, double points, BettingPage bettingPage, Stake minStake, Stake maxStake, Map<String, Object> additionalProperties) {
        super(event, eventID, selectionID, lineID, period, bettingPage, minStake, maxStake, additionalProperties);
        this.team = team;
        this.totalType = totalType;
        this.points = points;
    }

    public DefaultTeamPointsBetOptionInfo(AbstractBetOptionInfo betOptionInfo, Team team, TotalType totalType, double points) {
        super(betOptionInfo);
        this.team = team;
        this.totalType = totalType;
        this.points = points;
    }

    @Override
    public TotalType getTotalType() {
        return totalType;
    }

    @Override
    public Team getTeam() {
        return team;
    }

    @Override
    public double getPoints() {
        return points;
    }

    @Override
    public BetType getBetType() {
        return BetType.TEAM_POINTS;
    }

    @Override
    public String toString() {
        return "DefaultTeamPointsBetOptionInfo{" +
                "team=" + team +
                ", totalType=" + totalType +
                ", points=" + points +
                "} " + super.toString();
    }
}
