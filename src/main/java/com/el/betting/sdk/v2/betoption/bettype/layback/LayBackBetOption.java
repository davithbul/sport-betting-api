package com.el.betting.sdk.v2.betoption.bettype.layback;

import com.el.betting.sdk.v2.*;import com.el.betting.sdk.v4.*;
import com.el.betting.sdk.v2.betoption.api.*;
import org.springframework.data.annotation.PersistenceConstructor;

import java.math.BigDecimal;


/**
 * LayBackBetOption represents wrapper of real BetOption with possibility to back and lay.
 * @param <T> the real betOption type
 */
public class LayBackBetOption<T extends BetOptionInfo> extends LayBackBetOptionInfo<T> implements BetOption {

    private BigDecimal price;
    private OddsFormat oddsFormat = OddsFormat.DECIMAL;

    @PersistenceConstructor
    public LayBackBetOption(T betOptionInfo, BetExchangeType betExchangeType, BetPrice betPrice) {
        super(betOptionInfo, betExchangeType);
        this.price = betPrice.getPrice();
        Stake maxStake = new Stake(betPrice.getSize(), betPrice.getCurrency());
        this.setMaxStake(maxStake);
    }

    public LayBackBetOption(T betOptionInfo, BetExchangeType betExchangeType, BigDecimal price) {
        super(betOptionInfo, betExchangeType);
        this.price = price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public OddsFormat getOddsFormat() {
        return oddsFormat;
    }

    public void setOddsFormat(OddsFormat oddsFormat) {
        this.oddsFormat = oddsFormat;
    }

    @Override
    public String toString() {
        return "LayBackBetOption{" +
                "price=" + price +
                "} " + super.toString();
    }
}
