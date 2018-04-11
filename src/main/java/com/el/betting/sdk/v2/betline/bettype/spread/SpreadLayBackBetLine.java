package com.el.betting.sdk.v2.betline.bettype.spread;

import com.el.betting.sdk.v2.*;import com.el.betting.sdk.v4.*;
import com.el.betting.sdk.v2.betline.api.LayBackBetLine;
import com.el.betting.sdk.v2.betoption.bettype.layback.LayBackBetOption;
import com.el.betting.sdk.v2.betoption.bettype.spread.SpreadBetOption;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class SpreadLayBackBetLine extends LayBackBetLine<LayBackBetOption<SpreadBetOption>, SpreadBetLineInfo> {
    private List<BetPrice> homeBackOptions;
    private List<BetPrice> homeLayOptions;
    private List<BetPrice> awayBackOptions;
    private List<BetPrice> awayLayOptions;

    protected SpreadLayBackBetLine(SpreadBetLineInfo betLineInfo, MarketStatus marketStatus, List<BetPrice> homeBackOptions, List<BetPrice> homeLayOptions, List<BetPrice> awayBackOptions, List<BetPrice> awayLayOptions) {
        super(betLineInfo, marketStatus);
        this.homeBackOptions = homeBackOptions;
        this.homeLayOptions = homeLayOptions;
        this.awayBackOptions = awayBackOptions;
        this.awayLayOptions = awayLayOptions;
    }

    @Override
    public List<LayBackBetOption> getBetOptions() {
        List<LayBackBetOption> layBackBetOptions = new ArrayList<>();
        List<LayBackBetOption<SpreadBetOption>> homeLayBetOptions = convertBetOptions(homeLayOptions, BetExchangeType.LAY, getBetLineInfo().getHomeTeam(), getBetLineInfo().getHomeSpread(), getBetLineInfo().getHomeTeamSelectionId());
        List<LayBackBetOption<SpreadBetOption>> homeBackBetOptions = convertBetOptions(homeBackOptions, BetExchangeType.BACK, getBetLineInfo().getHomeTeam(), getBetLineInfo().getHomeSpread(), getBetLineInfo().getHomeTeamSelectionId());
        layBackBetOptions.addAll(homeLayBetOptions);
        layBackBetOptions.addAll(homeBackBetOptions);

        List<LayBackBetOption<SpreadBetOption>> awayLayBetOptions = convertBetOptions(awayLayOptions, BetExchangeType.LAY, getBetLineInfo().getAwayTeam(), getBetLineInfo().getAwaySpread(), getBetLineInfo().getAwayTeamSelectionId());
        List<LayBackBetOption<SpreadBetOption>> awayBackBetOptions = convertBetOptions(awayBackOptions, BetExchangeType.BACK, getBetLineInfo().getAwayTeam(), getBetLineInfo().getAwaySpread(), getBetLineInfo().getAwayTeamSelectionId());
        layBackBetOptions.addAll(awayLayBetOptions);
        layBackBetOptions.addAll(awayBackBetOptions);
        return layBackBetOptions;
    }

    private List<LayBackBetOption<SpreadBetOption>> convertBetOptions(List<BetPrice> betPrices, BetExchangeType exchangeType, Team team, BigDecimal spread, String selectionID) {
        if (betPrices == null) {
            return new ArrayList<>();
        }

        return betPrices.stream()
                .map(betPrice -> {
                    SpreadBetOption spreadBetOption = new SpreadBetOption(null, getEventID(), selectionID, getLineID(), getPeriod(), team, spread, betPrice.getPrice(), getOddsFormat(), null, null, null, new HashMap<>(getAdditionalProperties()));
                    return new LayBackBetOption<SpreadBetOption>(spreadBetOption, exchangeType, betPrice);
                })
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "SpreadLayBackBetLine{" +
                "homeBackOptions=" + homeBackOptions +
                ", homeLayOptions=" + homeLayOptions +
                ", awayBackOptions=" + awayBackOptions +
                ", awayLayOptions=" + awayLayOptions +
                "} " + super.toString();
    }
}
