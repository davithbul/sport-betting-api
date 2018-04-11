package com.el.betting.sdk.v2.betline.bettype.moneyline;

import com.el.betting.sdk.v2.betline.api.BetLine;
import com.el.betting.sdk.v2.betoption.bettype.moneyline.MoneyLineBetOptionInfo;
import com.el.betting.sdk.v2.common.PropertyHelper;
import com.el.betting.sdk.v2.*;import com.el.betting.sdk.v4.*;
import com.el.betting.sdk.v2.betoption.bettype.moneyline.DefaultMoneyLineBetOption; import com.el.betting.sdk.v2.betoption.bettype.moneyline.MoneyLineBetOption;
import org.springframework.data.annotation.PersistenceConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

import static com.el.betting.sdk.v2.BetType.MONEY_LINE;
import static com.el.betting.sdk.v2.PropertyType.*;

public class MoneyLineBetLine extends BetLine<MoneyLineBetOption, MoneyLineBetOptionInfo> {
    private final Team homeTeam;
    private final BigDecimal homePrice;
    private final String homeTeamSelectionId;
    private Optional<BigDecimal> drawPrice;
    private final String drawSelectionId;
    private final Team awayTeam;                                       
    private final BigDecimal awayPrice;
    private final String awayTeamSelectionId;

    @PersistenceConstructor
    protected MoneyLineBetLine(long eventID, String lineID, Period period, MarketStatus marketStatus, Stake maxStake, LocalDateTime startTime, Team homeTeam, BigDecimal homePrice, String homeTeamSelectionId, Optional<BigDecimal> drawPrice, String drawSelectionId, Team awayTeam, BigDecimal awayPrice, String awayTeamSelectionId, Map<String, Object> additionalProperties) {
        super(eventID, lineID, period, MONEY_LINE, marketStatus, maxStake, OddsFormat.DECIMAL, startTime, additionalProperties);
        this.homeTeam = homeTeam;
        this.homePrice = homePrice;
        this.homeTeamSelectionId = homeTeamSelectionId;
        this.drawPrice = drawPrice;
        this.drawSelectionId = drawSelectionId;
        this.awayTeam = awayTeam;
        this.awayPrice = awayPrice;
        this.awayTeamSelectionId = awayTeamSelectionId;
    }

    public BigDecimal getHomePrice() {
        return homePrice;
    }

    public Optional<BigDecimal> getDrawPrice() {
        return drawPrice;
    }

    public BigDecimal getAwayPrice() {
        return awayPrice;
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
    public List<MoneyLineBetOption> getBetOptions() {
        PropertyHelper propertyHelper = new PropertyHelper(getAdditionalProperties());

        List<MoneyLineBetOption> betOptions = new ArrayList<>();
        MoneyLineBetOption homeBetLine = new DefaultMoneyLineBetOption(null, getEventID(), getHomeTeamSelectionId(), getLineID(), getPeriod(), homeTeam, homePrice, getOddsFormat(), null, null, null, propertyHelper.getProperties(HOME, GLOBAL));
        betOptions.add(homeBetLine);

        if (drawPrice.isPresent()) {
            MoneyLineBetOption drawBetLine = new DefaultMoneyLineBetOption(null, getEventID(), getDrawSelectionId(), getLineID(), getPeriod(), Team.DRAW, drawPrice.get(), getOddsFormat(), null, null, null, propertyHelper.getProperties(DRAW, GLOBAL));
            betOptions.add(drawBetLine);
        }

        MoneyLineBetOption awayBetLine = new DefaultMoneyLineBetOption(null, getEventID(), getAwayTeamSelectionId(), getLineID(), getPeriod(), awayTeam, awayPrice, getOddsFormat(), null, null, null, propertyHelper.getProperties(AWAY, GLOBAL));
        betOptions.add(awayBetLine);

        return betOptions;
    }

    @Override
    public String toString() {
        return "MoneyLineBetOption{" +
                ", homeTeam=" + homeTeam +
                ", homePrice=" + homePrice +
                ", homeTeamSelectionId=" + homeTeamSelectionId +
                ", drawPrice=" + drawPrice +
                ", drawSelectionId=" + drawSelectionId +
                ", awayTeam=" + awayTeam +
                ", awayPrice=" + awayPrice +
                ", awayTeamSelectionId=" + awayTeamSelectionId +
                "} " + super.toString();
    }
}
