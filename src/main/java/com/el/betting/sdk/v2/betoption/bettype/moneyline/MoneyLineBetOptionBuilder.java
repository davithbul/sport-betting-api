package com.el.betting.sdk.v2.betoption.bettype.moneyline;

import com.el.betting.sdk.v2.OddsFormat;

import java.math.BigDecimal;

public class MoneyLineBetOptionBuilder extends MoneyLineBetOptionInfoBuilder<MoneyLineBetOptionBuilder> {
    private BigDecimal price;
    private OddsFormat oddsFormat = OddsFormat.DECIMAL;

    public MoneyLineBetOptionBuilder setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public MoneyLineBetOptionBuilder setOddsFormat(OddsFormat oddsFormat) {
        this.oddsFormat = oddsFormat;
        return this;
    }

    public MoneyLineBetOption createMoneyLineBetOption() {
        MoneyLineBetOptionInfo moneyLineBetOptionInfo = createMoneyLineBetOptionInfo();
        return new DefaultMoneyLineBetOption(moneyLineBetOptionInfo, price, oddsFormat);
    }
}