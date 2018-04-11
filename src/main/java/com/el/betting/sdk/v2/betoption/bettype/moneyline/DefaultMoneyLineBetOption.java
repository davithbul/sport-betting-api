package com.el.betting.sdk.v2.betoption.bettype.moneyline;

import com.el.betting.sdk.v2.*;import com.el.betting.sdk.v4.*;
import com.el.betting.sdk.v2.betoption.api.*;
import com.el.betting.sdk.v2.pages.BettingPage;
import com.el.betting.sdk.v4.Participant;

import java.math.BigDecimal;
import java.util.Map;


public class DefaultMoneyLineBetOption extends DefaultMoneyLineBetOptionInfo implements MoneyLineBetOption {

    private BigDecimal price;
    private OddsFormat oddsFormat;

    public DefaultMoneyLineBetOption(MoneyLineBetOptionInfo betOptionInfo, BigDecimal price) {
        this(betOptionInfo, price, OddsFormat.DECIMAL);
    }

    public DefaultMoneyLineBetOption(MoneyLineBetOptionInfo betOptionInfo, BigDecimal price, OddsFormat oddsFormat) {
        this(betOptionInfo.getEvent(), betOptionInfo.getEventID(), betOptionInfo.getSelectionID(), betOptionInfo.getLineID(),
                betOptionInfo.getPeriod(), betOptionInfo.getTeam(), price, oddsFormat, betOptionInfo.getBettingPage(), betOptionInfo.getMinStake(),
                betOptionInfo.getMaxStake(), betOptionInfo.getAdditionalProperties());
    }

    public DefaultMoneyLineBetOption(Event<? extends Participant> event, long eventID, String selectionID, String lineID, Period period, Team team, BigDecimal price, OddsFormat oddsFormat, BettingPage bettingPage, Stake minStake, Stake maxStake, Map<String, Object> additionalProperties) {
        super(event, eventID, selectionID, lineID, period, team, bettingPage, minStake, maxStake, additionalProperties);
        this.price = price;
        this.oddsFormat = oddsFormat;
    }

    public DefaultMoneyLineBetOption(AbstractBetOptionInfo betOptionInfo, BigDecimal price, Team team) {
        super(betOptionInfo, team);
        this.price = price;
    }

    public DefaultMoneyLineBetOption(MoneyLineBetOptionInfo betOptionInfo, BigDecimal price, Team team) {
        super(betOptionInfo, team);
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

    @Override
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public BetType getBetType() {
        return BetType.MONEY_LINE;
    }

    @Override
    public String toString() {
        return "MoneyLineBetLine{" +
                "price=" + price +
                "} " + super.toString();
    }
}
