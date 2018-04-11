package com.el.betting.sdk.v2.betline.bettype.moneyline;

import com.el.betting.sdk.v2.*;import com.el.betting.sdk.v4.*;
import com.el.betting.sdk.v2.betline.api.LayBackBetLine;
import com.el.betting.sdk.v2.betoption.bettype.layback.LayBackBetOption;
import com.el.betting.sdk.v2.betoption.bettype.moneyline.DefaultMoneyLineBetOption;
import com.el.betting.sdk.v2.betoption.bettype.moneyline.MoneyLineBetOption;
import com.el.betting.sdk.v2.common.PropertyHelper;
import com.el.betting.sdk.v2.pages.BettingPage;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static com.el.betting.sdk.v2.PropertyType.GLOBAL;

public class MoneyLineLayBackBetLine extends LayBackBetLine<LayBackBetOption<MoneyLineBetOption>, MoneyLineBetLineInfo> {

    private List<BetPrice> homeBackOptions;
    private List<BetPrice> homeLayOptions;
    private List<BetPrice> drawBackOptions;
    private List<BetPrice> drawLayOptions;
    private List<BetPrice> awayBackOptions;
    private List<BetPrice> awayLayOptions;

    protected MoneyLineLayBackBetLine(MoneyLineBetLineInfo betLineInfo, MarketStatus marketStatus, List<BetPrice> homeBackOptions, List<BetPrice> homeLayOptions, List<BetPrice> drawBackOptions, List<BetPrice> drawLayOptions, List<BetPrice> awayBackOptions, List<BetPrice> awayLayOptions) {
        super(betLineInfo, marketStatus);
        this.homeBackOptions = homeBackOptions;
        this.homeLayOptions = homeLayOptions;
        this.drawBackOptions = drawBackOptions;
        this.drawLayOptions = drawLayOptions;
        this.awayBackOptions = awayBackOptions;
        this.awayLayOptions = awayLayOptions;
    }

    public List<BetPrice> getHomeBackOptions() {
        return homeBackOptions;
    }

    public List<BetPrice> getHomeLayOptions() {
        return homeLayOptions;
    }

    public List<BetPrice> getDrawBackOptions() {
        return drawBackOptions;
    }

    public List<BetPrice> getDrawLayOptions() {
        return drawLayOptions;
    }

    public List<BetPrice> getAwayBackOptions() {
        return awayBackOptions;
    }

    public List<BetPrice> getAwayLayOptions() {
        return awayLayOptions;
    }

    @Override
    public List<LayBackBetOption> getBetOptions() {
        List<LayBackBetOption> betOptions = new ArrayList<>();

        List<LayBackBetOption<MoneyLineBetOption>> homeLayBetOptions = convertBetOptions(homeLayOptions, BetExchangeType.LAY, getBetLineInfo().getHomeTeamSelectionId(), getBetLineInfo().getHomeTeam());
        List<LayBackBetOption<MoneyLineBetOption>> homeBackBetOptions = convertBetOptions(homeBackOptions, BetExchangeType.BACK, getBetLineInfo().getHomeTeamSelectionId(), getBetLineInfo().getHomeTeam());
        betOptions.addAll(homeLayBetOptions);
        betOptions.addAll(homeBackBetOptions);

        if (!CollectionUtils.isEmpty(drawBackOptions)
                || !CollectionUtils.isEmpty(drawLayOptions)
                || getBetLineInfo().getDrawSelectionId() != null
                ) {
            List<LayBackBetOption<MoneyLineBetOption>> drawLayBetOptions = convertBetOptions(drawLayOptions, BetExchangeType.LAY, getBetLineInfo().getDrawSelectionId(), Team.DRAW);
            List<LayBackBetOption<MoneyLineBetOption>> drawBackBetOptions = convertBetOptions(drawBackOptions, BetExchangeType.BACK, getBetLineInfo().getDrawSelectionId(), Team.DRAW);
            betOptions.addAll(drawLayBetOptions);
            betOptions.addAll(drawBackBetOptions);
        }

        List<LayBackBetOption<MoneyLineBetOption>> awayLayBetOptions = convertBetOptions(awayLayOptions, BetExchangeType.LAY, getBetLineInfo().getAwayTeamSelectionId(), getBetLineInfo().getAwayTeam());
        List<LayBackBetOption<MoneyLineBetOption>> awayBackBetOptions = convertBetOptions(awayBackOptions, BetExchangeType.BACK, getBetLineInfo().getAwayTeamSelectionId(), getBetLineInfo().getAwayTeam());
        betOptions.addAll(awayLayBetOptions);
        betOptions.addAll(awayBackBetOptions);

        return betOptions;
    }

    private List<LayBackBetOption<MoneyLineBetOption>> convertBetOptions(List<BetPrice> betPrices, BetExchangeType exchangeType, String selectionID, Team team) {
        if (betPrices == null) {
            return new ArrayList<>();
        }

        PropertyHelper propertyHelper = new PropertyHelper(getAdditionalProperties());
        BettingPage bettingPage = (BettingPage) propertyHelper.getProperty("bettingPage", PropertyType.valueOf(exchangeType.name()), GLOBAL).orElse(null);

        return betPrices.stream()
                .map(betPrice -> {
                    DefaultMoneyLineBetOption moneyLineBetOption = new DefaultMoneyLineBetOption(null, getEventID(), selectionID, getLineID(), getPeriod(), team, betPrice.getPrice(), getOddsFormat(), bettingPage, null, getMaxStake(), new HashMap<>(getAdditionalProperties()));
                    return new LayBackBetOption<MoneyLineBetOption>(moneyLineBetOption, exchangeType, betPrice);
                })
                .collect(Collectors.toList());
    }
}
