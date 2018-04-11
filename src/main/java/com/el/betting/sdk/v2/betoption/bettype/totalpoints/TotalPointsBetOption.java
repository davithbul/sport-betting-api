package com.el.betting.sdk.v2.betoption.bettype.totalpoints;

import com.el.betting.sdk.v2.*;import com.el.betting.sdk.v4.*;
import com.el.betting.sdk.v2.betoption.api.AbstractBetOptionInfo;
import com.el.betting.sdk.v2.betoption.api.BetOption;
import com.el.betting.sdk.v2.pages.BettingPage;
import com.el.betting.sdk.v4.Participant;

import java.math.BigDecimal;
import java.util.Map;

public class TotalPointsBetOption extends DefaultTotalPointsBetOptionInfo implements BetOption, TotalPointsBetOptionInfo {

    private OddsFormat oddsFormat = OddsFormat.DECIMAL;
    private BigDecimal price;

    public TotalPointsBetOption(Event<? extends Participant> event, long eventID, String selectionID, String lineID, Period period, TotalType totalType, double points, BigDecimal price, BettingPage bettingPage, Map<String, Object> additionalProperties) {
        super(event, eventID, selectionID, lineID, period, totalType, points, bettingPage, additionalProperties);
        this.price = price;
    }

    public TotalPointsBetOption(Event<? extends Participant> event, long eventID, String selectionID, String lineID, Period period, TotalType totalType, double points, BigDecimal price, OddsFormat oddsFormat, BettingPage bettingPage, Stake minStake, Stake maxStake, Map<String, Object> additionalProperties) {
        super(event, eventID, selectionID, lineID, period, totalType, points, bettingPage, minStake, maxStake, additionalProperties);
        this.price = price;
        this.oddsFormat = oddsFormat;
    }

    public TotalPointsBetOption(AbstractBetOptionInfo betOptionInfo, TotalType totalType, double points, BigDecimal price) {
        super(betOptionInfo, totalType, points);
        this.price = price;
    }

    public TotalPointsBetOption(TotalPointsBetOption totalPointsBetOption) {
        super(totalPointsBetOption, totalPointsBetOption.getTotalType(), totalPointsBetOption.getPoints());
        this.price = totalPointsBetOption.getPrice();
    }

    @Override
    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public void setPrice(BigDecimal price) {
        this.price = price;
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
        return "TotalPointsBetOption{" +
                "price=" + price +
                "} " + super.toString();
    }
}
