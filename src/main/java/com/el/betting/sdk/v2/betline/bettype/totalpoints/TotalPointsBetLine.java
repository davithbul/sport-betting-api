package com.el.betting.sdk.v2.betline.bettype.totalpoints;

import com.el.betting.sdk.v2.*;import com.el.betting.sdk.v4.*;
import com.el.betting.sdk.v2.betline.api.BetLine;
import com.el.betting.sdk.v2.betoption.bettype.totalpoints.TotalPointsBetOption;
import com.el.betting.sdk.v2.betoption.bettype.totalpoints.TotalPointsBetOptionBuilder;
import com.el.betting.sdk.v2.betoption.bettype.totalpoints.TotalPointsBetOptionInfo;
import org.springframework.data.annotation.PersistenceConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

public class TotalPointsBetLine extends BetLine<TotalPointsBetOption, TotalPointsBetOptionInfo> {
    /** Total expected points (e.g. all goals) */
    private final double points;
    /** The price if there is more points then set */
    private final BigDecimal overPrice;
    private final String overSelectionID;
    /** The price if there is less points then set */
    private final BigDecimal underPrice;
    private final String underSelectionID;

    @PersistenceConstructor
    protected TotalPointsBetLine(long eventID, String lineID, Period period, MarketStatus marketStatus, Stake maxStake, LocalDateTime startTime, double points, BigDecimal overPrice, String overSelectionID, BigDecimal underPrice, String underSelectionID, Map<String, Object> additionalProperties) {
        super(eventID, lineID, period, BetType.TOTAL_POINTS, marketStatus, maxStake, OddsFormat.DECIMAL, startTime, additionalProperties);
        this.points = points;
        this.overPrice = overPrice;
        this.overSelectionID = overSelectionID;
        this.underPrice = underPrice;
        this.underSelectionID = underSelectionID;
    }

    public double getPoints() {
        return points;
    }

    public BigDecimal getOverPrice() {
        return overPrice;
    }

    public BigDecimal getUnderPrice() {
        return underPrice;
    }

    public String getOverSelectionID() {
        return overSelectionID;
    }

    public String getUnderSelectionID() {
        return underSelectionID;
    }

    @Override
    public List<TotalPointsBetOption> getBetOptions() {
        List<TotalPointsBetOption> betOptions = new ArrayList<>();

        TotalPointsBetOption overPriceBetOption = new TotalPointsBetOptionBuilder().setEvent(null).setEventID(getEventID()).setSelectionID(getOverSelectionID()).setLineID(getLineID()).setPeriod(getPeriod()).setTotalType(TotalType.OVER).setPoints(points).setPrice(overPrice).setBettingPage(null).setMinStake(null).setMaxStake(null).setOddsFormat(getOddsFormat()).setAdditionalProperties(new HashMap<>()).createTotalPointsBetOption();
        betOptions.add(overPriceBetOption);

        TotalPointsBetOption underPriceBetOption = new TotalPointsBetOptionBuilder().setEvent(null).setEventID(getEventID()).setSelectionID(getUnderSelectionID()).setLineID(getLineID()).setPeriod(getPeriod()).setTotalType(TotalType.UNDER).setPoints(points).setPrice(underPrice).setBettingPage(null).setMinStake(null).setMaxStake(null).setOddsFormat(getOddsFormat()).setAdditionalProperties(new HashMap<>()).createTotalPointsBetOption();
        betOptions.add(underPriceBetOption);

        return betOptions;
    }

    @Override
    public String toString() {
        return "TotalPointsBetLine{" +
                "points=" + points +
                ", overPrice=" + overPrice +
                ", overSelectionID='" + overSelectionID + '\'' +
                ", underPrice=" + underPrice +
                ", underSelectionID='" + underSelectionID + '\'' +
                "} " + super.toString();
    }
}
