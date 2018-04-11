package com.el.betting.sdk.v2.betline.bettype.totalpoints;

import com.el.betting.sdk.v2.*;import com.el.betting.sdk.v4.*;
import com.el.betting.sdk.v2.betline.api.BetLineInfo;
import com.el.betting.sdk.v2.betoption.bettype.totalpoints.DefaultTotalPointsBetOptionInfo;
import com.el.betting.sdk.v2.betoption.bettype.totalpoints.TotalPointsBetOptionInfo;
import org.springframework.data.annotation.PersistenceConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

public class TotalPointsBetLineInfo extends BetLineInfo<TotalPointsBetOptionInfo> {
    /** Total expected points (e.g. all goals) */
    private final double points;
    private final String overSelectionID;
    private final String underSelectionID;

    @PersistenceConstructor
    protected TotalPointsBetLineInfo(long eventID, String lineID, Period period, Stake maxStake, LocalDateTime startTime, double points, String overSelectionID, String underSelectionID, OddsFormat oddsFormat, Map<String, Object> additionalProperties) {
        super(eventID, lineID, period, BetType.TOTAL_POINTS, maxStake, oddsFormat, startTime, additionalProperties);
        this.points = points;
        this.overSelectionID = overSelectionID;
        this.underSelectionID = underSelectionID;
    }

    public double getPoints() {
        return points;
    }

    public String getOverSelectionID() {
        return overSelectionID;
    }

    public String getUnderSelectionID() {
        return underSelectionID;
    }

    @Override
    public List<TotalPointsBetOptionInfo> getBetOptionsInfoList() {
        List<TotalPointsBetOptionInfo> betOptionsMeta = new ArrayList<>();

        TotalPointsBetOptionInfo overPriceBetOptionInfo = new DefaultTotalPointsBetOptionInfo(null, getEventID(), getOverSelectionID(), getLineID(), getPeriod(), TotalType.OVER, points, null, null, null, new HashMap<>());
        betOptionsMeta.add(overPriceBetOptionInfo);

        TotalPointsBetOptionInfo underPriceBetOptionInfo = new DefaultTotalPointsBetOptionInfo(null, getEventID(), getUnderSelectionID(), getLineID(), getPeriod(), TotalType.UNDER, points, null, null, null, new HashMap<>());
        betOptionsMeta.add(underPriceBetOptionInfo);

        return betOptionsMeta;
    }

    @Override
    public String toString() {
        return "TotalPointsBetLineInfo{" +
                "points=" + points +
                ", overSelectionID='" + overSelectionID + '\'' +
                ", underSelectionID='" + underSelectionID + '\'' +
                "} " + super.toString();
    }
}
