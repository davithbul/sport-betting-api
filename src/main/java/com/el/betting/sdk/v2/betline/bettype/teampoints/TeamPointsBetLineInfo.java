package com.el.betting.sdk.v2.betline.bettype.teampoints;

import com.el.betting.sdk.v2.*;import com.el.betting.sdk.v4.*;
import com.el.betting.sdk.v2.betline.api.BetLineInfo;
import com.el.betting.sdk.v2.betoption.bettype.teampoints.DefaultTeamPointsBetOptionInfo;
import com.el.betting.sdk.v2.betoption.bettype.teampoints.TeamPointsBetOptionInfo;
import org.springframework.data.annotation.PersistenceConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

public class TeamPointsBetLineInfo extends BetLineInfo<TeamPointsBetOptionInfo> {
    private final Team team;
    /** Total expected points (e.g. all goals by team) */
    private final double points;
    private final String overSelectionID;
    private final String underSelectionID;

    @PersistenceConstructor
    protected TeamPointsBetLineInfo(long eventID, String lineID, Period period, Stake maxStake, LocalDateTime startTime, Team team, double points, String overSelectionID, String underSelectionID, Map<String, Object> additionalProperties) {
        super(eventID, lineID, period, BetType.TEAM_POINTS, maxStake, OddsFormat.DECIMAL, startTime, additionalProperties);
        this.team = team;
        this.points = points;
        this.overSelectionID = overSelectionID;
        this.underSelectionID = underSelectionID;
    }

    public Team getTeam() {
        return team;
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
    public List<TeamPointsBetOptionInfo> getBetOptionsInfoList() {
        List<TeamPointsBetOptionInfo> betOptionsMeta = new ArrayList<>();

        TeamPointsBetOptionInfo overPriceBetOptionInfo = new DefaultTeamPointsBetOptionInfo(null, getEventID(), getOverSelectionID(), getLineID(), getPeriod(), team, TotalType.OVER, points, null, null, null, new HashMap<>());
        betOptionsMeta.add(overPriceBetOptionInfo);

        TeamPointsBetOptionInfo underPriceBetOptionInfo = new DefaultTeamPointsBetOptionInfo(null, getEventID(), getUnderSelectionID(), getLineID(), getPeriod(), team, TotalType.UNDER, points, null, null, null, new HashMap<>());
        betOptionsMeta.add(underPriceBetOptionInfo);

        return betOptionsMeta;
    }

    @Override
    public String toString() {
        return "TeamPointsBetLineInfo{" +
                "team=" + team +
                ", points=" + points +
                ", overSelectionID='" + overSelectionID + '\'' +
                ", underSelectionID='" + underSelectionID + '\'' +
                "} " + super.toString();
    }
}
