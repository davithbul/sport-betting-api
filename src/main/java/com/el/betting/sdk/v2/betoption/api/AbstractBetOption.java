package com.el.betting.sdk.v2.betoption.api;

import com.el.betting.sdk.v2.Event;import com.el.betting.sdk.v4.*;
import com.el.betting.sdk.v2.OddsFormat;
import com.el.betting.sdk.v2.Period;
import com.el.betting.sdk.v2.Stake;
import com.el.betting.sdk.v2.pages.BettingPage;
import com.el.betting.sdk.v4.Participant;

import java.math.BigDecimal;
import java.util.Map;

public abstract class AbstractBetOption extends AbstractBetOptionInfo implements BetOptionInfo, BetOption {
    private BigDecimal price;
    private final OddsFormat oddsFormat;

    public AbstractBetOption(Event<? extends Participant> event, long eventID, String selectionID, String lineID, Period period, BigDecimal price, BettingPage bettingPage, Map<String, Object> additionalData) {
        this(event, eventID, selectionID, lineID, period, price, OddsFormat.DECIMAL, bettingPage, null, null, additionalData);
    }

    public AbstractBetOption(Event<? extends Participant> event, long eventID, String selectionID, String lineID, Period period, BigDecimal price, OddsFormat oddsFormat, BettingPage bettingPage, Stake minStake, Stake maxStake, Map<String, Object> additionalData) {
        super(event, eventID, selectionID, lineID, period, bettingPage, minStake, maxStake, additionalData);
        this.price = price;
        this.oddsFormat = oddsFormat;
    }

    public AbstractBetOption(AbstractBetOptionInfo betOptionInfo, BigDecimal price, OddsFormat oddsFormat) {
        super(betOptionInfo);
        this.price = price;
        this.oddsFormat = oddsFormat;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public OddsFormat getOddsFormat() {
        return oddsFormat;
    }

    @Override
    public String toString() {
        return "BetLine{" +
                ", price=" + price +
                ", oddsFormat=" + oddsFormat+
                '}';
    }
}
