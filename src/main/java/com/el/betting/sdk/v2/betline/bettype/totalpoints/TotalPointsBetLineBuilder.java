package com.el.betting.sdk.v2.betline.bettype.totalpoints;

import com.el.betting.sdk.v2.MarketStatus;

import java.math.BigDecimal;

public class TotalPointsBetLineBuilder extends TotalPointsBetLineInfoBuilder<TotalPointsBetLineBuilder> {
    private MarketStatus marketStatus;
    private BigDecimal overPrice;
    private BigDecimal underPrice;

    public TotalPointsBetLineBuilder setMarketStatus(MarketStatus marketStatus) {
        this.marketStatus = marketStatus;
        return this;
    }

    public TotalPointsBetLineBuilder setOverPrice(BigDecimal overPrice) {
        this.overPrice = overPrice;
        return this;
    }

    public TotalPointsBetLineBuilder setUnderPrice(BigDecimal underPrice) {
        this.underPrice = underPrice;
        return this;
    }
   public TotalPointsBetLine createTotalPointsBetLine() {
        return new TotalPointsBetLine(eventID, lineID, period, marketStatus, maxStake, startTime, points, overPrice, overSelectionID, underPrice, underSelectionID, additionalProperties);
    }
}