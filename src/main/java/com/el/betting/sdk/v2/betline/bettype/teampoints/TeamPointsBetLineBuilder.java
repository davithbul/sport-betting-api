package com.el.betting.sdk.v2.betline.bettype.teampoints;

import com.el.betting.sdk.v2.MarketStatus;

import java.math.BigDecimal;

public class TeamPointsBetLineBuilder extends TeamPointsBetLineInfoBuilder<TeamPointsBetLineBuilder> {
    private MarketStatus marketStatus;
    private BigDecimal overPrice;
    private BigDecimal underPrice;

    public TeamPointsBetLineBuilder setMarketStatus(MarketStatus marketStatus) {
        this.marketStatus = marketStatus;
        return this;
    }

    public TeamPointsBetLineBuilder setOverPrice(BigDecimal overPrice) {
        this.overPrice = overPrice;
        return this;
    }

    public TeamPointsBetLineBuilder setUnderPrice(BigDecimal underPrice) {
        this.underPrice = underPrice;
        return this;
    }

    public TeamPointsBetLine createTeamPointsBetLine() {
        return new TeamPointsBetLine(eventID, lineID, period, marketStatus, maxStake, startTime, team, points, overPrice, overSelectionID, underPrice, underSelectionID, additionalProperties);
    }
}