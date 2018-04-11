package com.el.betting.sdk.v2.betline.bettype.totalpoints;

import com.el.betting.sdk.v2.BetPrice;
import com.el.betting.sdk.v2.MarketStatus;

import java.util.List;

public class TotalPointsLayBackBetLineBuilder extends TotalPointsBetLineInfoBuilder<TotalPointsLayBackBetLineBuilder> {
    private MarketStatus marketStatus;
    private List<BetPrice> underBackOptions;
    private List<BetPrice> underLayOptions;
    private List<BetPrice> overBackOptions;
    private List<BetPrice> overLayOptions;

    public TotalPointsLayBackBetLineBuilder setMarketStatus(MarketStatus marketStatus) {
        this.marketStatus = marketStatus;
        return this;
    }


    public TotalPointsLayBackBetLineBuilder setUnderBackOptions(List<BetPrice> underBackOptions) {
        this.underBackOptions = underBackOptions;
        return this;
    }

    public TotalPointsLayBackBetLineBuilder setUnderLayOptions(List<BetPrice> underLayOptions) {
        this.underLayOptions = underLayOptions;
        return this;
    }

    public TotalPointsLayBackBetLineBuilder setOverBackOptions(List<BetPrice> overBackOptions) {
        this.overBackOptions = overBackOptions;
        return this;
    }

    public TotalPointsLayBackBetLineBuilder setOverLayOptions(List<BetPrice> overLayOptions) {
        this.overLayOptions = overLayOptions;
        return this;
    }

    public TotalPointsLayBackBetLine createTotalPointsLayBackBetLine() {
        TotalPointsBetLineInfo totalPointsBetLineInfo = createTotalPointsBetLineInfo();
        TotalPointsLayBackBetLine totalPointsLayBackBetLine = new TotalPointsLayBackBetLine(totalPointsBetLineInfo, marketStatus, underBackOptions, underLayOptions, overBackOptions, overLayOptions);
        totalPointsLayBackBetLine.setEvent(event != null ? event.cloneWithoutBetLines() : null);
        return totalPointsLayBackBetLine;
    }
}