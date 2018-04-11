package com.el.betting.sdk.v2.betline.bettype.moneyline;

import com.el.betting.sdk.v2.MarketStatus;

import java.math.BigDecimal;
import java.util.Optional;

public class MoneyLineBetLineBuilder extends MoneyLineBetLineInfoBuilder<MoneyLineBetLineBuilder> {
    private MarketStatus marketStatus;
    private BigDecimal homePrice;
    private Optional<BigDecimal> drawPrice = Optional.empty();
    private BigDecimal awayPrice;

    public MoneyLineBetLineBuilder setMarketStatus(MarketStatus marketStatus) {
        this.marketStatus = marketStatus;
        return this;
    }

    public MoneyLineBetLineBuilder setHomePrice(BigDecimal homePrice) {
        this.homePrice = homePrice;
        return this;
    }

    public MoneyLineBetLineBuilder setDrawPrice(Optional<BigDecimal> drawPrice) {
        this.drawPrice = drawPrice;
        return this;
    }

    public MoneyLineBetLineBuilder setAwayPrice(BigDecimal awayPrice) {
        this.awayPrice = awayPrice;
        return this;
    }

   public boolean isValid() {
        return homeTeam != null &&
                awayTeam != null;
    }

    public MoneyLineBetLine createMoneyLineBetLine() {
        return new MoneyLineBetLine(eventID, lineID, period, marketStatus, maxStake, startTime, homeTeam, homePrice, homeTeamSelectionId, drawPrice, drawSelectionId, awayTeam, awayPrice, awayTeamSelectionId, additionalProperties);
    }
}