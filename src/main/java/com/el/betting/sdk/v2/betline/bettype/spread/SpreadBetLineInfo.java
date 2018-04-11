package com.el.betting.sdk.v2.betline.bettype.spread;

import com.el.betting.sdk.v2.*;import com.el.betting.sdk.v4.*;
import com.el.betting.sdk.v2.betline.api.BetLineInfo;
import com.el.betting.sdk.v2.betoption.bettype.spread.DefaultSpreadBetOptionInfo;
import com.el.betting.sdk.v2.betoption.bettype.spread.SpreadBetOptionBuilder;
import com.el.betting.sdk.v2.betoption.bettype.spread.SpreadBetOptionInfo;
import org.springframework.data.annotation.PersistenceConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

public class SpreadBetLineInfo extends BetLineInfo<SpreadBetOptionInfo> {
    private final Team homeTeam;
    private final BigDecimal homeSpread;
    private final String homeTeamSelectionId;
    private final Team awayTeam;
    private final BigDecimal awaySpread;
    private final String awayTeamSelectionId;

    @PersistenceConstructor
    protected SpreadBetLineInfo(long eventID, String lineID, Period period, Stake maxStake, LocalDateTime startTime, Team homeTeam, BigDecimal homeSpread, String homeTeamSelectionId, Team awayTeam, BigDecimal awaySpread, String awayTeamSelectionId, OddsFormat oddsFormat, Map<String, Object> additionalProperties) {
        super(eventID, lineID, period, BetType.HANDICAP, maxStake, oddsFormat, startTime, additionalProperties);
        this.homeTeam = homeTeam;
        this.homeSpread = homeSpread;
        this.homeTeamSelectionId = homeTeamSelectionId;
        this.awayTeam = awayTeam;
        this.awaySpread = awaySpread;
        this.awayTeamSelectionId = awayTeamSelectionId;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public BigDecimal getHomeSpread() {
        return homeSpread;
    }

    public BigDecimal getAwaySpread() {
        return awaySpread;
    }

    public String getHomeTeamSelectionId() {
        return homeTeamSelectionId;
    }

    public String getAwayTeamSelectionId() {
        return awayTeamSelectionId;
    }

    @Override
    public List<SpreadBetOptionInfo> getBetOptionsInfoList() {
        List<SpreadBetOptionInfo> betOptionsMeta = new ArrayList<>();

        DefaultSpreadBetOptionInfo homeSpreadBetOptionInfo = new SpreadBetOptionBuilder().setEvent(null).setEventID(getEventID()).setSelectionID(getHomeTeamSelectionId()).setLineID(getLineID()).setPeriod(getPeriod()).setTeam(homeTeam).setSpread(homeSpread).setBettingPage(null).setMinStake(null).setMaxStake(null).setAdditionalProperties(new HashMap<>()).createDefaultSpreadBetOptionInfo();
        betOptionsMeta.add(homeSpreadBetOptionInfo);

        DefaultSpreadBetOptionInfo awaySpreadBetOptionInfo = new SpreadBetOptionBuilder().setEvent(null).setEventID(getEventID()).setSelectionID(getAwayTeamSelectionId()).setLineID(getLineID()).setPeriod(getPeriod()).setTeam(awayTeam).setSpread(awaySpread).setBettingPage(null).setMinStake(null).setMaxStake(null).setAdditionalProperties(new HashMap<>()).createDefaultSpreadBetOptionInfo();
        betOptionsMeta.add(awaySpreadBetOptionInfo);

        return betOptionsMeta;
    }

    @Override
    public String toString() {
        return "SpreadBetLineInfo{" +
                "homeTeam=" + homeTeam +
                ", homeSpread=" + homeSpread +
                ", homeTeamSelectionId='" + homeTeamSelectionId + '\'' +
                ", awayTeam=" + awayTeam +
                ", awaySpread=" + awaySpread +
                ", awayTeamSelectionId='" + awayTeamSelectionId + '\'' +
                "} " + super.toString();
    }
}
