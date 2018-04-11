package com.el.betting.sdk.v2.betline.bettype.correctscore;

import com.el.betting.sdk.v2.*;import com.el.betting.sdk.v4.*;
import com.el.betting.sdk.v2.betline.api.LayBackBetLine;
import com.el.betting.sdk.v2.betoption.bettype.correctscore.CorrectScoreBetOption;
import com.el.betting.sdk.v2.betoption.bettype.layback.LayBackBetOption;
import com.el.betting.sdk.v2.betoption.bettype.totalpoints.TotalPointsBetOption;
import com.el.betting.sdk.v2.common.PropertyHelper;
import com.el.betting.sdk.v2.pages.BettingPage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static com.el.betting.sdk.v2.PropertyType.GLOBAL;

public class CorrectScoreLayBackBetLine extends LayBackBetLine<LayBackBetOption<CorrectScoreBetOption>, CorrectScoreBetLineInfo> {
    private List<ScoreLayBackPrice> scoreLayBackPriceList;

    protected CorrectScoreLayBackBetLine(CorrectScoreBetLineInfo betLineInfo, MarketStatus marketStatus, List<ScoreLayBackPrice> scoreLayBackPriceList) {
        super(betLineInfo, marketStatus);
        this.scoreLayBackPriceList = scoreLayBackPriceList;
    }

    public List<ScoreLayBackPrice> getScoreLayBackPriceList() {
        return scoreLayBackPriceList;
    }

    @Override
    public List<LayBackBetOption> getBetOptions() {
        List<LayBackBetOption> layBackBetOptions = new ArrayList<>();
        final CorrectScoreBetLineInfo betLineInfo = getBetLineInfo();
        for (ScoreLayBackPrice scoreLayBackPrice : scoreLayBackPriceList) {
            final List<BetPrice> backPrices = scoreLayBackPrice.getBackPrices();
            final List<BetPrice> layPrices = scoreLayBackPrice.getLayPrices();
            final List<LayBackBetOption<CorrectScoreBetOption>> layBetOptions = convertBetOptions(backPrices, scoreLayBackPrice.getScoreSelection(), BetExchangeType.BACK);
            final List<LayBackBetOption<CorrectScoreBetOption>> backBetOptions = convertBetOptions(layPrices, scoreLayBackPrice.getScoreSelection(), BetExchangeType.LAY);
            layBackBetOptions.addAll(layBetOptions);
            layBackBetOptions.addAll(backBetOptions);
        }
        return layBackBetOptions;
    }

    private List<LayBackBetOption<CorrectScoreBetOption>> convertBetOptions(List<BetPrice> betPrices, ScoreSelection scoreSelection, BetExchangeType exchangeType) {
        if (betPrices == null) {
            return new ArrayList<>();
        }

        PropertyHelper propertyHelper = new PropertyHelper(getAdditionalProperties());
        BettingPage bettingPage = (BettingPage) propertyHelper.getProperty("bettingPage", PropertyType.valueOf(exchangeType.name()), GLOBAL).orElse(null);
        final Score<Integer> score = scoreSelection.getScore();

        return betPrices.stream()
                .map(betPrice -> {

                    CorrectScoreBetOption correctScoreBetOption = new CorrectScoreBetOption(getEvent(), getEventID()
                            , scoreSelection.getSelectionId(), getLineID(), getPeriod(),
                           score.getHomeSideScore(), score.getAwaySideScore(), betPrice.getPrice(), getOddsFormat(), bettingPage, null, getMaxStake(), new HashMap<>(getAdditionalProperties()));
                    return new LayBackBetOption<CorrectScoreBetOption>(correctScoreBetOption, exchangeType, betPrice);
                })
                .collect(Collectors.toList());
    }
}
