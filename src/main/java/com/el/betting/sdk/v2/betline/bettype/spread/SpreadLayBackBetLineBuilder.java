package com.el.betting.sdk.v2.betline.bettype.spread;

import com.el.betting.sdk.v2.BetPrice;
import com.el.betting.sdk.v2.MarketStatus;

import java.util.List;

public class SpreadLayBackBetLineBuilder extends SpreadBetLineInfoBuilder<SpreadLayBackBetLineBuilder> {
    private MarketStatus marketStatus;
    private List<BetPrice> homeBackOptions;
    private List<BetPrice> homeLayOptions;
    private List<BetPrice> awayBackOptions;
    private List<BetPrice> awayLayOptions;

    public SpreadLayBackBetLineBuilder setHomeBackOptions(List<BetPrice> homeBackOptions) {
        this.homeBackOptions = homeBackOptions;
        return this;
    }

    public SpreadLayBackBetLineBuilder setHomeLayOptions(List<BetPrice> homeLayOptions) {
        this.homeLayOptions = homeLayOptions;
        return this;
    }

    public SpreadLayBackBetLineBuilder setAwayBackOptions(List<BetPrice> awayBackOptions) {
        this.awayBackOptions = awayBackOptions;
        return this;
    }

    public SpreadLayBackBetLineBuilder setAwayLayOptions(List<BetPrice> awayLayOptions) {
        this.awayLayOptions = awayLayOptions;
        return this;
    }

    public SpreadLayBackBetLineBuilder setMarketStatus(MarketStatus marketStatus) {
        this.marketStatus = marketStatus;
        return this;
    }

    public SpreadLayBackBetLine createSpreadLayBackBetLine() {
        SpreadBetLineInfo spreadBetLineInfo = createSpreadBetLineInfo();
        return new SpreadLayBackBetLine(spreadBetLineInfo, marketStatus, homeBackOptions, homeLayOptions, awayBackOptions, awayLayOptions);
    }
}