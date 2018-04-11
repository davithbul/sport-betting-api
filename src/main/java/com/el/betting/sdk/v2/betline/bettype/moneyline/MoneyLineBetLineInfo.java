package com.el.betting.sdk.v2.betline.bettype.moneyline;

import com.el.betting.sdk.v2.*;import com.el.betting.sdk.v4.*;
import com.el.betting.sdk.v2.betline.api.BetLineInfo;
import com.el.betting.sdk.v2.betoption.bettype.moneyline.MoneyLineBetOptionInfoBuilder;
import com.el.betting.sdk.v2.betoption.bettype.moneyline.MoneyLineBetOptionInfo;
import com.el.betting.sdk.v2.common.PropertyHelper;
import com.el.betting.sdk.v2.pages.BettingPage;
import com.el.betting.sdk.v4.Participant;
import org.springframework.data.annotation.PersistenceConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.el.betting.sdk.v2.PropertyType.*;

public class MoneyLineBetLineInfo extends BetLineInfo<MoneyLineBetOptionInfo> {
    private final Team homeTeam;
    private final String homeTeamSelectionId;
    private final String drawSelectionId;
    private final Team awayTeam;
    private final String awayTeamSelectionId;

    @PersistenceConstructor
    protected MoneyLineBetLineInfo(long eventID, String lineID, Period period, BetType betType, Stake maxStake, OddsFormat oddsFormat, LocalDateTime startTime, Map<String, Object> additionalProperties, Team homeTeam, String homeTeamSelectionId, String drawSelectionId, Team awayTeam, String awayTeamSelectionId) {
        super(eventID, lineID, period, betType, maxStake, oddsFormat, startTime, additionalProperties);
        this.homeTeam = homeTeam;
        this.homeTeamSelectionId = homeTeamSelectionId;
        this.drawSelectionId = drawSelectionId;
        this.awayTeam = awayTeam;
        this.awayTeamSelectionId = awayTeamSelectionId;
    }

    public MoneyLineBetLineInfo(MoneyLineBetLineInfo betLineInfo) {
        super(betLineInfo);
        this.homeTeam = betLineInfo.getHomeTeam();
        this.homeTeamSelectionId = betLineInfo.getHomeTeamSelectionId();
        this.drawSelectionId = betLineInfo.getDrawSelectionId();
        this.awayTeam = betLineInfo.getAwayTeam();
        this.awayTeamSelectionId = betLineInfo.getAwayTeamSelectionId();
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public String getHomeTeamSelectionId() {
        return homeTeamSelectionId;
    }

    public String getDrawSelectionId() {
        return drawSelectionId;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public String getAwayTeamSelectionId() {
        return awayTeamSelectionId;
    }

    @Override
    public List<MoneyLineBetOptionInfo> getBetOptionsInfoList() {
        PropertyHelper propertyHelper = new PropertyHelper(getAdditionalProperties());

        List<MoneyLineBetOptionInfo> betOptionsMeta = new ArrayList<>();
        Event<? extends Participant> event = (Event) propertyHelper.getProperty("event", GLOBAL).orElse(null);
        MoneyLineBetOptionInfo homeBetOptionInfo = new MoneyLineBetOptionInfoBuilder().setEvent(event).setEventID(getEventID()).setSelectionID(getHomeTeamSelectionId()).setLineID(getLineID()).setPeriod(getPeriod()).setTeam(homeTeam).setBettingPage((BettingPage) propertyHelper.getProperty("bettingPage", HOME).orElse(null)).setMinStake(null).setMaxStake(null).setAdditionalProperties(propertyHelper.getProperties(HOME, GLOBAL)).createMoneyLineBetOptionInfo();
        betOptionsMeta.add(homeBetOptionInfo);

        if (drawSelectionId != null) {
            MoneyLineBetOptionInfo drawBetOptionInfo = new MoneyLineBetOptionInfoBuilder().setEvent(event).setEventID(getEventID()).setSelectionID(getDrawSelectionId()).setLineID(getLineID()).setPeriod(getPeriod()).setTeam(Team.DRAW).setBettingPage((BettingPage) propertyHelper.getProperty("bettingPage", DRAW).orElse(null)).setMinStake(null).setMaxStake(null).setAdditionalProperties(propertyHelper.getProperties(DRAW, GLOBAL)).createMoneyLineBetOptionInfo();
            betOptionsMeta.add(drawBetOptionInfo);
        }

        MoneyLineBetOptionInfo awayBetLine = new MoneyLineBetOptionInfoBuilder().setEvent(event).setEventID(getEventID()).setSelectionID(getAwayTeamSelectionId()).setLineID(getLineID()).setPeriod(getPeriod()).setTeam(awayTeam).setBettingPage((BettingPage) propertyHelper.getProperty("bettingPage", AWAY).orElse(null)).setMinStake(null).setMaxStake(null).setAdditionalProperties(propertyHelper.getProperties(AWAY, GLOBAL)).createMoneyLineBetOptionInfo();
        betOptionsMeta.add(awayBetLine);

        return betOptionsMeta;
    }

    @Override
    public String toString() {
        return "MoneyLineBetLineInfo{" +
                "homeTeam=" + homeTeam +
                ", homeTeamSelectionId='" + homeTeamSelectionId + '\'' +
                ", drawSelectionId='" + drawSelectionId + '\'' +
                ", awayTeam=" + awayTeam +
                ", awayTeamSelectionId='" + awayTeamSelectionId + '\'' +
                "} " + super.toString();
    }
}
