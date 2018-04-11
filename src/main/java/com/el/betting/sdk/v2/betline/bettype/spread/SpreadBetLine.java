package com.el.betting.sdk.v2.betline.bettype.spread;

import com.el.betting.sdk.v2.*;import com.el.betting.sdk.v4.*;
import com.el.betting.sdk.v2.betline.api.BetLine;
import com.el.betting.sdk.v2.betoption.bettype.spread.SpreadBetOption;
import com.el.betting.sdk.v2.betoption.bettype.spread.SpreadBetOptionInfo;
import org.springframework.data.annotation.PersistenceConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

public class SpreadBetLine extends BetLine<SpreadBetOption, SpreadBetOptionInfo> {
    private final Team homeTeam;
    private final BigDecimal homeSpread;
    private final BigDecimal homePrice;
    private final String homeTeamSelectionId;
    private final Team awayTeam;
    private final BigDecimal awaySpread;
    private final BigDecimal awayPrice;
    private final String awayTeamSelectionId;

    @PersistenceConstructor
    protected SpreadBetLine(long eventID, String lineID, Period period, MarketStatus marketStatus, Stake maxStake, LocalDateTime startTime, Team homeTeam, BigDecimal homeSpread, BigDecimal homePrice, String homeTeamSelectionId, Team awayTeam, BigDecimal awaySpread, BigDecimal awayPrice, String awayTeamSelectionId, Map<String, Object> additionalProperties) {
        super(eventID, lineID, period, BetType.HANDICAP, marketStatus, maxStake, OddsFormat.DECIMAL, startTime, additionalProperties);
        this.homeTeam = homeTeam;
        this.homeSpread = homeSpread;
        this.homePrice = homePrice;
        this.homeTeamSelectionId = homeTeamSelectionId;
        this.awayTeam = awayTeam;
        this.awaySpread = awaySpread;
        this.awayPrice = awayPrice;
        this.awayTeamSelectionId = awayTeamSelectionId;
    }

    public BigDecimal getHomeSpread() {
        return homeSpread;
    }

    public BigDecimal getHomePrice() {
        return homePrice;
    }

    public BigDecimal getAwaySpread() {
        return awaySpread;
    }

    public BigDecimal getAwayPrice() {
        return awayPrice;
    }

    public String getHomeTeamSelectionId() {
        return homeTeamSelectionId;
    }

    public String getAwayTeamSelectionId() {
        return awayTeamSelectionId;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    @Override
    public List<SpreadBetOption> getBetOptions() {
        List<SpreadBetOption> betOptions = new ArrayList<>();

        SpreadBetOption homeSpreadBetOption = new SpreadBetOption(null, getEventID(), getHomeTeamSelectionId(), getLineID(), getPeriod(), homeTeam, homeSpread, homePrice, getOddsFormat(), null, null, null, new HashMap<>());
        betOptions.add(homeSpreadBetOption);

        SpreadBetOption awaySpreadBetOption = new SpreadBetOption(null, getEventID(), getAwayTeamSelectionId(), getLineID(), getPeriod(), awayTeam, awaySpread, awayPrice, getOddsFormat(), null, null, null, new HashMap<>());
        betOptions.add(awaySpreadBetOption);

        return betOptions;
    }

    @Override
    public String toString() {
        return "SpreadBetOption{" +
                ", homeSpread=" + homeSpread +
                ", homePrice=" + homePrice +
                ", homeTeamSelectionId=" + homeTeamSelectionId +
                ", awaySpread=" + awaySpread +
                ", awayPrice=" + awayPrice +
                ", awayTeamSelectionId=" + awayTeamSelectionId +
                "} " + super.toString();
    }
}
