package com.el.betting.sdk.v2.betoption.bettype.winner;

import com.el.betting.sdk.v2.Event;
import com.el.betting.sdk.v2.OddsFormat;
import com.el.betting.sdk.v2.Period;
import com.el.betting.sdk.v2.Stake;
import com.el.betting.sdk.v2.betoption.api.AbstractBetOptionInfo;
import com.el.betting.sdk.v2.betoption.api.BetOption;
import com.el.betting.sdk.v2.pages.BettingPage;
import com.el.betting.sdk.v4.Participant;

import java.math.BigDecimal;
import java.util.Map;

public class RaceWinnerBetOption extends DefaultRaceWinnerBetOptionInfo implements BetOption, RaceWinnerBetOptionInfo {

    private OddsFormat oddsFormat;
    private BigDecimal price;

    public RaceWinnerBetOption(Event<? extends Participant> event, long eventID, String selectionID, String lineID, Period period, Participant participant, String distance,BigDecimal price, BettingPage bettingPage, Map<String, Object> additionalProperties) {
        super(event, eventID, selectionID, lineID, period, participant, distance, bettingPage, additionalProperties);
        this.price = price;
        this.oddsFormat = OddsFormat.DECIMAL;
    }

    public RaceWinnerBetOption(Event<? extends Participant> event, long eventID, String selectionID, String lineID, Period period, Participant participant, String distance, BigDecimal price, OddsFormat oddsFormat, BettingPage bettingPage, Stake minStake, Stake maxStake, Map<String, Object> additionalProperties) {
        super(event, eventID, selectionID, lineID, period, participant, distance, bettingPage, minStake, maxStake, additionalProperties);
        this.price = price;
        this.oddsFormat = oddsFormat;
    }

    public RaceWinnerBetOption(AbstractBetOptionInfo betOptionInfo, Participant participant, String distance, BigDecimal price) {
        super(betOptionInfo, participant, distance);
        this.price = price;
        this.oddsFormat = OddsFormat.DECIMAL;
    }

    public RaceWinnerBetOption(RaceWinnerBetOption raceWinnerBetOption) {
        super(raceWinnerBetOption, raceWinnerBetOption.getParticipant(), raceWinnerBetOption.getDistance());
        this.price = raceWinnerBetOption.getPrice();
        this.oddsFormat = raceWinnerBetOption.getOddsFormat();
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
        return "RaceWinnerBetOption{" +
                "price=" + price +
                "} " + super.toString();
    }
}
