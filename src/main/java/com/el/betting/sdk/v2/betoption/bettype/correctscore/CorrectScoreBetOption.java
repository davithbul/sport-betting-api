package com.el.betting.sdk.v2.betoption.bettype.correctscore;

import com.el.betting.sdk.v2.Event;import com.el.betting.sdk.v4.*;
import com.el.betting.sdk.v2.OddsFormat;
import com.el.betting.sdk.v2.Period;
import com.el.betting.sdk.v2.Stake;
import com.el.betting.sdk.v2.betoption.api.AbstractBetOptionInfo;
import com.el.betting.sdk.v2.betoption.api.BetOption;
import com.el.betting.sdk.v2.pages.BettingPage;

import java.math.BigDecimal;
import java.util.Map;

public class CorrectScoreBetOption extends DefaultCorrectScoreBetOptionInfo implements BetOption, CorrectScoreBetOptionInfo {

    private OddsFormat oddsFormat;
    private BigDecimal price;

    public CorrectScoreBetOption(Event<? extends Participant> event, long eventID, String selectionID, String lineID, Period period, int homeTeamGoalCount, int awayTeamGoalCount, BigDecimal price, BettingPage bettingPage, Map<String, Object> additionalProperties) {
        super(event, eventID, selectionID, lineID, period, homeTeamGoalCount, awayTeamGoalCount, bettingPage, additionalProperties);
        this.price = price;
        this.oddsFormat = OddsFormat.DECIMAL;
    }

    public CorrectScoreBetOption(Event<? extends Participant> event, long eventID, String selectionID, String lineID, Period period, int homeTeamGoalCount, int awayTeamGoalCount, BigDecimal price, OddsFormat oddsFormat, BettingPage bettingPage, Stake minStake, Stake maxStake, Map<String, Object> additionalProperties) {
        super(event, eventID, selectionID, lineID, period, homeTeamGoalCount, awayTeamGoalCount, bettingPage, minStake, maxStake, additionalProperties);
        this.price = price;
        this.oddsFormat = oddsFormat;
    }

    public CorrectScoreBetOption(AbstractBetOptionInfo betOptionInfo, int homeTeamGoalCount, int awayTeamGoalCount, BigDecimal price) {
        super(betOptionInfo, homeTeamGoalCount, awayTeamGoalCount);
        this.price = price;
        this.oddsFormat = OddsFormat.DECIMAL;
    }

    public CorrectScoreBetOption(CorrectScoreBetOption correctScoreBetOption) {
        super(correctScoreBetOption, correctScoreBetOption.getHomeSideScore(), correctScoreBetOption.getAwaySideScore());
        this.price = correctScoreBetOption.getPrice();
        this.oddsFormat = correctScoreBetOption.getOddsFormat();
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
        return "CorrectScoreBetOption{" +
                "price=" + price +
                "} " + super.toString();
    }
}
