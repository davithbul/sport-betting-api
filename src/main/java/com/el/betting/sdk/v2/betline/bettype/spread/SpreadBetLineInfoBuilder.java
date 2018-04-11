package com.el.betting.sdk.v2.betline.bettype.spread;

import com.el.betting.common.CollectionUtil;
import com.el.betting.sdk.v2.OddsFormat;
import com.el.betting.sdk.v2.Period;
import com.el.betting.sdk.v2.Stake;
import com.el.betting.sdk.v2.Team;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class SpreadBetLineInfoBuilder<T> {
    protected long eventID;
    protected String lineID;
    protected Period period;
    protected Stake maxStake;
    protected LocalDateTime startTime;
    protected OddsFormat oddsFormat;
    protected Team homeTeam;
    protected BigDecimal homeSpread;
    protected String homeTeamSelectionId;
    protected Team awayTeam;
    protected BigDecimal awaySpread;
    protected String awayTeamSelectionId;
    protected Map<String, Object> additionalProperties;

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

    public T setOddsFormat(OddsFormat oddsFormat) {
        this.oddsFormat = oddsFormat;
        return (T) this;
    }

    public T setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
        return (T) this;
    }

    public T setHomeSpread(BigDecimal homeSpread) {
        this.homeSpread = homeSpread;
        return (T) this;
    }

    public T setHomeTeamSelectionId(String homeTeamSelectionId) {
        this.homeTeamSelectionId = homeTeamSelectionId;
        return (T) this;
    }

    public T setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
        return (T) this;
    }

    public T setAwaySpread(BigDecimal awaySpread) {
        this.awaySpread = awaySpread;
        return (T) this;
    }

    public T setAwayTeamSelectionId(String awayTeamSelectionId) {
        this.awayTeamSelectionId = awayTeamSelectionId;
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

    public SpreadBetLineInfo createSpreadBetLineInfo() {
        return new SpreadBetLineInfo(eventID, lineID, period, maxStake, startTime,
                homeTeam, homeSpread, homeTeamSelectionId,
                awayTeam, awaySpread, awayTeamSelectionId,
                oddsFormat,
                CollectionUtil.safeMap(additionalProperties)
                );
    }
}