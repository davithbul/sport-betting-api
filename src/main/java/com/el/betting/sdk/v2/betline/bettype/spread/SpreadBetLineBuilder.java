package com.el.betting.sdk.v2.betline.bettype.spread;

import com.el.betting.sdk.v2.MarketStatus;

import java.math.BigDecimal;

public class SpreadBetLineBuilder extends SpreadBetLineInfoBuilder<SpreadBetLineBuilder> {
    private BigDecimal homePrice;
    private BigDecimal awayPrice;
    private MarketStatus marketStatus;


    public SpreadBetLineBuilder setHomePrice(BigDecimal homePrice) {
        this.homePrice = homePrice;
        return this;
    }

    public SpreadBetLineBuilder setAwayPrice(BigDecimal awayPrice) {
        this.awayPrice = awayPrice;
        return this;
    }

    public SpreadBetLineBuilder setMarketStatus(MarketStatus marketStatus) {
        this.marketStatus = marketStatus;
        return this;
    }

    public SpreadBetLine createSpreadBetLine() {
        return new SpreadBetLine(eventID, lineID, period, marketStatus, maxStake, startTime, homeTeam, homeSpread, homePrice, homeTeamSelectionId, awayTeam, awaySpread, awayPrice, awayTeamSelectionId, additionalProperties);
    }
}