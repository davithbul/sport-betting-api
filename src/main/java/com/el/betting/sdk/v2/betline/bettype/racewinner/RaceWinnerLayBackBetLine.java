package com.el.betting.sdk.v2.betline.bettype.racewinner;

import com.el.betting.sdk.v2.BetExchangeType;
import com.el.betting.sdk.v2.BetPrice;
import com.el.betting.sdk.v2.MarketStatus;
import com.el.betting.sdk.v2.PropertyType;
import com.el.betting.sdk.v2.betline.api.LayBackBetLine;
import com.el.betting.sdk.v2.betoption.bettype.layback.LayBackBetOption;
import com.el.betting.sdk.v2.betoption.bettype.winner.RaceWinnerBetOption;
import com.el.betting.sdk.v2.common.PropertyHelper;
import com.el.betting.sdk.v2.pages.BettingPage;
import com.el.betting.sdk.v4.race.WinnerCandidate;
import com.el.betting.sdk.v4.race.WinnerCandidateLayBackPrice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static com.el.betting.sdk.v2.PropertyType.GLOBAL;

public class RaceWinnerLayBackBetLine extends LayBackBetLine<LayBackBetOption<RaceWinnerBetOption>, RaceWinnerBetLineInfo> {
    private List<WinnerCandidateLayBackPrice> layBackPrices;

    protected RaceWinnerLayBackBetLine(RaceWinnerBetLineInfo betLineInfo, MarketStatus marketStatus, List<WinnerCandidateLayBackPrice> layBackPrices) {
        super(betLineInfo, marketStatus);
        this.layBackPrices = layBackPrices;
    }

    public List<WinnerCandidateLayBackPrice> getLayBackPrices() {
        return layBackPrices;
    }

    @Override
    public List<LayBackBetOption> getBetOptions() {
        List<LayBackBetOption> layBackBetOptions = new ArrayList<>();
        for (WinnerCandidateLayBackPrice winnerCandidateLayBackPrice : layBackPrices) {
            List<BetPrice> backPrices = winnerCandidateLayBackPrice.getBackPrices();
            List<BetPrice> layPrices = winnerCandidateLayBackPrice.getLayPrices();
            List<LayBackBetOption<RaceWinnerBetOption>> layBetOptions = convertBetOptions(backPrices, winnerCandidateLayBackPrice.getWinnerCandidate(), BetExchangeType.BACK);
            List<LayBackBetOption<RaceWinnerBetOption>> backBetOptions = convertBetOptions(layPrices, winnerCandidateLayBackPrice.getWinnerCandidate(), BetExchangeType.LAY);
            layBackBetOptions.addAll(layBetOptions);
            layBackBetOptions.addAll(backBetOptions);
        }
        return layBackBetOptions;
    }

    private List<LayBackBetOption<RaceWinnerBetOption>> convertBetOptions(List<BetPrice> betPrices, WinnerCandidate winnerCandidate, BetExchangeType exchangeType) {
        if (betPrices == null) {
            return new ArrayList<>();
        }

        PropertyHelper propertyHelper = new PropertyHelper(getAdditionalProperties());
        BettingPage bettingPage = (BettingPage) propertyHelper.getProperty("bettingPage", PropertyType.valueOf(exchangeType.name()), GLOBAL).orElse(null);
        return betPrices.stream()
                .map(betPrice -> {
                    RaceWinnerBetOption raceWinnerBetOption = new RaceWinnerBetOption(
                            getEvent(), getEventID(),
                            winnerCandidate.getSelectionId(), getLineID(), getPeriod(),
                            winnerCandidate.getParticipant(), getBetLineInfo().getDistance(),
                            betPrice.getPrice(),
                            getOddsFormat(), bettingPage, null, getMaxStake(), new HashMap<>(getAdditionalProperties()));
                    return new LayBackBetOption<RaceWinnerBetOption>(raceWinnerBetOption, exchangeType, betPrice);
                })
                .collect(Collectors.toList());
    }
}
