package com.el.betting.sdk.v2.betline.bettype.teampoints;

import com.el.betting.sdk.v2.*;import com.el.betting.sdk.v4.*;
import com.el.betting.sdk.v2.betline.api.LayBackBetLine;
import com.el.betting.sdk.v2.betoption.bettype.layback.LayBackBetOption;
import com.el.betting.sdk.v2.betoption.bettype.teampoints.TeamPointsBetOption;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class TeamPointsLayBackBetLine extends LayBackBetLine<LayBackBetOption<TeamPointsBetOption>, TeamPointsBetLineInfo> {
    private MarketStatus marketStatus;
    private List<BetPrice> overBackOptions;
    private List<BetPrice> overLayOptions;
    private List<BetPrice> underBackOptions;
    private List<BetPrice> underLayOptions;

    protected TeamPointsLayBackBetLine(TeamPointsBetLineInfo betLineInfo, MarketStatus marketStatus, List<BetPrice> overBackOptions, List<BetPrice> overLayOptions, List<BetPrice> underBackOptions, List<BetPrice> underLayOptions) {
        super(betLineInfo, marketStatus);
        this.overBackOptions = overBackOptions;
        this.overLayOptions = overLayOptions;
        this.underBackOptions = underBackOptions;
        this.underLayOptions = underLayOptions;
    }

    @Override
    public List<LayBackBetOption> getBetOptions() {
        List<LayBackBetOption> layBackBetOptions = new ArrayList<>();
        List<LayBackBetOption<TeamPointsBetOption>> overLayBetOptions = convertBetOptions(overLayOptions, TotalType.OVER, BetExchangeType.LAY, getBetLineInfo().getOverSelectionID(), getBetLineInfo().getTeam());
        List<LayBackBetOption<TeamPointsBetOption>> overBackBetOptions = convertBetOptions(overBackOptions, TotalType.OVER, BetExchangeType.BACK, getBetLineInfo().getOverSelectionID(), getBetLineInfo().getTeam());
        layBackBetOptions.addAll(overLayBetOptions);
        layBackBetOptions.addAll(overBackBetOptions);

        List<LayBackBetOption<TeamPointsBetOption>> underLayBetOptions = convertBetOptions(underLayOptions, TotalType.UNDER, BetExchangeType.LAY, getBetLineInfo().getUnderSelectionID(), getBetLineInfo().getTeam());
        List<LayBackBetOption<TeamPointsBetOption>> underBackBetOptions = convertBetOptions(underBackOptions, TotalType.UNDER, BetExchangeType.BACK, getBetLineInfo().getUnderSelectionID(), getBetLineInfo().getTeam());
        layBackBetOptions.addAll(underLayBetOptions);
        layBackBetOptions.addAll(underBackBetOptions);
        return layBackBetOptions;
    }

    private List<LayBackBetOption<TeamPointsBetOption>> convertBetOptions(List<BetPrice> betPrices, TotalType totalType, BetExchangeType exchangeType, String selectionID, Team team) {
        if (betPrices == null) {
            return new ArrayList<>();
        }

        return betPrices.stream()
                .map(betPrice -> {
                    TeamPointsBetOption teamPointsBetOption = new TeamPointsBetOption(null, getEventID(), selectionID, getLineID(), getPeriod(), team, totalType, getBetLineInfo().getPoints(), betPrice.getPrice(), getOddsFormat(), null, null, null, new HashMap<String, Object>(getAdditionalProperties()));
                    return new LayBackBetOption<TeamPointsBetOption>(teamPointsBetOption, exchangeType, betPrice);
                })
                .collect(Collectors.toList());
    }
}
