package com.el.betting.sdk.v2.betoption.bettype.teampoints;

import com.el.betting.sdk.v2.*;import com.el.betting.sdk.v4.*;
import com.el.betting.sdk.v2.betoption.api.AbstractBetOptionInfo;
import com.el.betting.sdk.v2.betoption.api.BetOption;
import com.el.betting.sdk.v2.pages.BettingPage;

import java.math.BigDecimal;
import java.util.Map;

public class TeamPointsBetOption extends DefaultTeamPointsBetOptionInfo implements BetOption {

    private OddsFormat oddsFormat = OddsFormat.DECIMAL;
    private BigDecimal price;

    public TeamPointsBetOption(Event<? extends Participant> event, long eventID, String selectionID, String lineID, Period period, Team team, TotalType totalType, double points, BigDecimal price, BettingPage bettingPage, Map<String, Object> additionalProperties) {
        super(event, eventID, selectionID, lineID, period, team, totalType, points, bettingPage, additionalProperties);
        this.price = price;
    }

    public TeamPointsBetOption(Event<? extends Participant> event, long eventID, String selectionID, String lineID, Period period, Team team, TotalType totalType, double points, BigDecimal price, OddsFormat oddsFormat, BettingPage bettingPage, Stake minStake, Stake maxStake, Map<String, Object> additionalProperties) {
        super(event, eventID, selectionID, lineID, period, team, totalType, points, bettingPage, minStake, maxStake, additionalProperties);
        this.price = price;
        this.oddsFormat = oddsFormat;
    }

    public TeamPointsBetOption(AbstractBetOptionInfo betOptionInfo, Team team, TotalType totalType, double points, BigDecimal price) {
        super(betOptionInfo, team, totalType, points);
        this.price = price;
    }

    public TeamPointsBetOption(TeamPointsBetOption teamPointsBetOption) {
        super(teamPointsBetOption, teamPointsBetOption.getTeam(), teamPointsBetOption.getTotalType(), teamPointsBetOption.getPoints());
        this.price = teamPointsBetOption.getPrice();
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
        return "TeamPointsBetOption{" +
                "price=" + price +
                "} " + super.toString();
    }
}
