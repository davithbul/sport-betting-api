package com.el.betting.sdk.v2.betline.bettype.teampoints;

import com.el.betting.sdk.v2.*;import com.el.betting.sdk.v4.*;
import com.el.betting.sdk.v2.betline.api.BetLine;
import com.el.betting.sdk.v2.betoption.bettype.teampoints.TeamPointsBetOption;
import com.el.betting.sdk.v2.betoption.bettype.teampoints.TeamPointsBetOptionInfo;
import org.springframework.data.annotation.PersistenceConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

public class TeamPointsBetLine extends BetLine<TeamPointsBetOption, TeamPointsBetOptionInfo> {

    private final Team team;
    /** Total expected points (e.g. all goals by team) */
    private final double points;
    /** The price if there is more points then set */
    private final BigDecimal overPrice;
    private final String overSelectionID;
    /** The price if there is less points then set */
    private final BigDecimal underPrice;
    private final String underSelectionID;

    @PersistenceConstructor
    protected TeamPointsBetLine(long eventID, String lineID, Period period, MarketStatus marketStatus, Stake maxStake, LocalDateTime startTime, Team team, double points, BigDecimal overPrice, String overSelectionID, BigDecimal underPrice, String underSelectionID, Map<String, Object> additionalProperties) {
        super(eventID, lineID, period, BetType.TEAM_POINTS, marketStatus, maxStake, OddsFormat.DECIMAL, startTime, additionalProperties);
        this.team = team;
        this.points = points;
        this.overPrice = overPrice;
        this.overSelectionID = overSelectionID;
        this.underPrice = underPrice;
        this.underSelectionID = underSelectionID;
    }

    public Team getTeam() {
        return team;
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
    public List<TeamPointsBetOption> getBetOptions() {
        List<TeamPointsBetOption> betOptions = new ArrayList<>();

        TeamPointsBetOption overPriceBetOption = new TeamPointsBetOption(null, getEventID(), getOverSelectionID(), getLineID(), getPeriod(), team, TotalType.OVER, points, overPrice, getOddsFormat(), null, null, null, new HashMap<>());
        betOptions.add(overPriceBetOption);

        TeamPointsBetOption underPriceBetOption = new TeamPointsBetOption(null, getEventID(), getUnderSelectionID(), getLineID(), getPeriod(), team, TotalType.UNDER, points, underPrice, getOddsFormat(), null, null, null, new HashMap<>());
        betOptions.add(underPriceBetOption);

        return betOptions;
    }

}
