package com.el.betting.sdk.v2.betline.bettype.teampoints;

import com.el.betting.sdk.v2.BetPrice;
import com.el.betting.sdk.v2.MarketStatus;

import java.util.List;

public class TeamPointsLayBackBetLineBuilder extends TeamPointsBetLineInfoBuilder<TeamPointsLayBackBetLineBuilder> {
    private MarketStatus marketStatus;
    private List<BetPrice> overBackOptions;
    private List<BetPrice> overLayOptions;
    private List<BetPrice> underBackOptions;
    private List<BetPrice> underLayOptions;

    public TeamPointsLayBackBetLineBuilder setMarketStatus(MarketStatus marketStatus) {
        this.marketStatus = marketStatus;
        return this;
    }

    public TeamPointsLayBackBetLineBuilder setOverBackOptions(List<BetPrice> overBackOptions) {
        this.overBackOptions = overBackOptions;
        return this;
    }

    public TeamPointsLayBackBetLineBuilder setOverLayOptions(List<BetPrice> overLayOptions) {
        this.overLayOptions = overLayOptions;
        return this;
    }

    public TeamPointsLayBackBetLineBuilder setUnderBackOptions(List<BetPrice> underBackOptions) {
        this.underBackOptions = underBackOptions;
        return this;
    }

    public TeamPointsLayBackBetLineBuilder setUnderLayOptions(List<BetPrice> underLayOptions) {
        this.underLayOptions = underLayOptions;
        return this;
    }

    public TeamPointsLayBackBetLine createTeamPointsLayBackBetLine() {
        TeamPointsBetLineInfo teamPointsBetLineInfo = createTeamPointsBetLineInfo();
        return new TeamPointsLayBackBetLine(teamPointsBetLineInfo, marketStatus, overBackOptions, overLayOptions, underBackOptions, underLayOptions);
    }
}