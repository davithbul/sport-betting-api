package com.el.betting.sdk.v2.betline.bettype.moneyline;

import com.el.betting.common.CollectionUtil;
import com.el.betting.sdk.v2.*;import com.el.betting.sdk.v4.*;

import java.util.List;

public class MoneyLineLayBackBetLineBuilder extends MoneyLineBetLineInfoBuilder<MoneyLineLayBackBetLineBuilder> {
    private MarketStatus marketStatus;
    private List<BetPrice> homeBackPrices;
    private List<BetPrice> homeLayPrices;
    private List<BetPrice> drawBackPrices;
    private List<BetPrice> drawLayPrices;
    private List<BetPrice> awayBackPrices;
    private List<BetPrice> awayLayPrices;

    public MoneyLineLayBackBetLineBuilder setMarketStatus(MarketStatus marketStatus) {
        this.marketStatus = marketStatus;
        return this;
    }

    public MoneyLineLayBackBetLineBuilder setHomeBackPrices(List<BetPrice> homeBackPrices) {
        this.homeBackPrices = homeBackPrices;
        return this;
    }

    public MoneyLineLayBackBetLineBuilder setHomeLayPrices(List<BetPrice> homeLayPrices) {
        this.homeLayPrices = homeLayPrices;
        return this;
    }

    public MoneyLineLayBackBetLineBuilder setDrawBackPrices(List<BetPrice> drawBackPrices) {
        this.drawBackPrices = drawBackPrices;
        return this;
    }

    public MoneyLineLayBackBetLineBuilder setDrawLayPrices(List<BetPrice> drawLayPrices) {
        this.drawLayPrices = drawLayPrices;
        return this;
    }

    public MoneyLineLayBackBetLineBuilder setAwayBackPrices(List<BetPrice> awayBackPrices) {
        this.awayBackPrices = awayBackPrices;
        return this;
    }

    public MoneyLineLayBackBetLineBuilder setAwayLayPrices(List<BetPrice> awayLayPrices) {
        this.awayLayPrices = awayLayPrices;
        return this;
    }

    public MoneyLineLayBackBetLine createMoneyLineLayBackBetLine() {
        MoneyLineBetLineInfo moneyLineBetLineInfo = createMoneyLineBetLineInfo();
        return new MoneyLineLayBackBetLine(moneyLineBetLineInfo, marketStatus,
                CollectionUtil.safeList(homeBackPrices),
                CollectionUtil.safeList(homeLayPrices),
                CollectionUtil.safeList(drawBackPrices),
                CollectionUtil.safeList(drawLayPrices),
                CollectionUtil.safeList(awayBackPrices), CollectionUtil.safeList(awayLayPrices));
    }
}