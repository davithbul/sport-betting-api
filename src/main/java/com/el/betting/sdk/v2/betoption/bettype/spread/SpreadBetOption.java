package com.el.betting.sdk.v2.betoption.bettype.spread;

import com.el.betting.sdk.v2.*;import com.el.betting.sdk.v4.*;
import com.el.betting.sdk.v2.betoption.api.AbstractBetOptionInfo;
import com.el.betting.sdk.v2.betoption.api.BetOption;
import com.el.betting.sdk.v2.pages.BettingPage;

import java.math.BigDecimal;
import java.util.Map;

public class SpreadBetOption extends DefaultSpreadBetOptionInfo implements BetOption {

    private OddsFormat oddsFormat;
    private BigDecimal price;

    public SpreadBetOption(Event<? extends Participant> event, long eventID, String selectionID, String lineID, Period period, Team team, BigDecimal spread, BigDecimal price, BettingPage bettingPage, Map<String, Object> additionalProperties) {
        super(event, eventID, selectionID, lineID, period, team, spread, bettingPage, additionalProperties);
        this.price = price;
        this.oddsFormat = OddsFormat.DECIMAL;
    }

    public SpreadBetOption(Event<? extends Participant> event, long eventID, String selectionID, String lineID, Period period, Team team, BigDecimal spread, BigDecimal price, OddsFormat oddsFormat, BettingPage bettingPage, Stake minStake, Stake maxStake, Map<String, Object> additionalProperties) {
        super(event, eventID, selectionID, lineID, period, team, spread, bettingPage, minStake, maxStake, additionalProperties);
        this.price = price;
        this.oddsFormat = oddsFormat;
    }

    public SpreadBetOption(AbstractBetOptionInfo betOptionInfo, Team team, BigDecimal spread, BigDecimal price) {
        super(betOptionInfo, team, spread);
        this.price = price;
        this.oddsFormat = OddsFormat.DECIMAL;
    }

    @Override
    public BetType getBetType() {
        return BetType.HANDICAP;
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
        return "SpreadBetOption{" +
                "price=" + price +
                "} " + super.toString();
    }
}
