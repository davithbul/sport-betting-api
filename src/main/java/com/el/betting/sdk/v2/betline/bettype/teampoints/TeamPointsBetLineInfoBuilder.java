package com.el.betting.sdk.v2.betline.bettype.teampoints;

import com.el.betting.common.CollectionUtil;
import com.el.betting.sdk.v2.Event;import com.el.betting.sdk.v4.*;
import com.el.betting.sdk.v2.Period;
import com.el.betting.sdk.v2.Stake;
import com.el.betting.sdk.v2.Team;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class TeamPointsBetLineInfoBuilder<T extends TeamPointsBetLineInfoBuilder> {
    protected Event<? extends Participant> event;
    protected long eventID;
    protected String lineID;
    protected Period period;
    protected Stake maxStake;
    protected LocalDateTime startTime;
    protected Team team;
    protected double points;
    protected String overSelectionID;
    protected String underSelectionID;
    protected Map<String, Object> additionalProperties;


    public T setEvent(Event<? extends Participant> event) {
        this.event = event;
        return (T) this;
    }

    public T setEventID(long eventID) {
        this.eventID = eventID;
        return (T) this;
    }

    public T setLineID(String lineID) {
        this.lineID = lineID;
        return (T) this;
    }

    public T setPeriod(Period period) {
        this.period = period;
        return (T) this;
    }

    public T setMaxStake(Stake maxStake) {
        this.maxStake = maxStake;
        return (T) this;
    }

    public T setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
        return (T) this;
    }

    public T setTeam(Team team) {
        this.team = team;
        return (T) this;
    }

    public T setPoints(double points) {
        this.points = points;
        return (T) this;
    }

    public T setOverSelectionID(String overSelectionID) {
        this.overSelectionID = overSelectionID;
        return (T) this;
    }

    public T setUnderSelectionID(String underSelectionID) {
        this.underSelectionID = underSelectionID;
        return (T) this;
    }

    public T setAdditionalProperties(Map<String, Object> additionalProperties) {
        this.additionalProperties = additionalProperties;
        return (T) this;
    }

    public T addProperty(String name, Object value) {
        if(additionalProperties == null) {
            additionalProperties = new HashMap<>();
        }
        additionalProperties.put(name, value);
        return (T) this;
    }

    public TeamPointsBetLineInfo createTeamPointsBetLineInfo() {
        TeamPointsBetLineInfo teamPointsBetLineInfo = new TeamPointsBetLineInfo(eventID, lineID, period, maxStake, startTime, team, points, overSelectionID, underSelectionID,
                CollectionUtil.safeMap(additionalProperties));
        teamPointsBetLineInfo.setEvent(event.cloneWithoutBetLines());
        return teamPointsBetLineInfo;
    }
}