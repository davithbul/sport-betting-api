package com.el.betting.sdk.v2.betline.bettype.totalpoints;

import com.el.betting.sdk.v2.*;import com.el.betting.sdk.v4.*;
import com.el.betting.sdk.v2.betline.api.LayBackBetLine;
import com.el.betting.sdk.v2.betoption.bettype.layback.LayBackBetOption;
import com.el.betting.sdk.v2.betoption.bettype.totalpoints.TotalPointsBetOption;
import com.el.betting.sdk.v2.common.PropertyHelper;
import com.el.betting.sdk.v2.pages.BettingPage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static com.el.betting.sdk.v2.PropertyType.GLOBAL;

public class TotalPointsLayBackBetLine extends LayBackBetLine<LayBackBetOption<TotalPointsBetOption>, TotalPointsBetLineInfo> {
    private List<BetPrice> underBackPrices;
    private List<BetPrice> underLayPrices;
    private List<BetPrice> overBackPrices;
    private List<BetPrice> overLayPrices;

    protected TotalPointsLayBackBetLine(TotalPointsBetLineInfo betLineInfo, MarketStatus marketStatus, List<BetPrice> underBackPrices, List<BetPrice> underLayPrices, List<BetPrice> overBackPrices, List<BetPrice> overLayPrices) {
        super(betLineInfo, marketStatus);
        this.underBackPrices = underBackPrices;
        this.underLayPrices = underLayPrices;
        this.overBackPrices = overBackPrices;
        this.overLayPrices = overLayPrices;
    }


    public List<BetPrice> getUnderBackPrices() {
        return underBackPrices;
    }

    public List<BetPrice> getUnderLayPrices() {
        return underLayPrices;
    }

    public List<BetPrice> getOverBackPrices() {
        return overBackPrices;
    }

    public List<BetPrice> getOverLayPrices() {
        return overLayPrices;
    }

    @Override
    public List<LayBackBetOption> getBetOptions() {
        List<LayBackBetOption> layBackBetOptions = new ArrayList<>();
        List<LayBackBetOption<TotalPointsBetOption>> overLayBetOptions = convertBetOptions(overLayPrices, TotalType.OVER, BetExchangeType.LAY, getBetLineInfo().getOverSelectionID());
        List<LayBackBetOption<TotalPointsBetOption>> overBackBetOptions = convertBetOptions(overBackPrices, TotalType.OVER, BetExchangeType.BACK, getBetLineInfo().getOverSelectionID());
        layBackBetOptions.addAll(overLayBetOptions);
        layBackBetOptions.addAll(overBackBetOptions);

        List<LayBackBetOption<TotalPointsBetOption>> underLayBetOptions = convertBetOptions(underLayPrices, TotalType.UNDER, BetExchangeType.LAY, getBetLineInfo().getUnderSelectionID());
        List<LayBackBetOption<TotalPointsBetOption>> underBackBetOptions = convertBetOptions(underBackPrices, TotalType.UNDER, BetExchangeType.BACK, getBetLineInfo().getUnderSelectionID());
        layBackBetOptions.addAll(underLayBetOptions);
        layBackBetOptions.addAll(underBackBetOptions);
        return layBackBetOptions;
    }

    private List<LayBackBetOption<TotalPointsBetOption>> convertBetOptions(List<BetPrice> betPrices, TotalType totalType, BetExchangeType exchangeType, String selectionID) {
        if (betPrices == null) {
            return new ArrayList<>();
        }

        PropertyHelper propertyHelper = new PropertyHelper(getAdditionalProperties());
        BettingPage bettingPage = (BettingPage) propertyHelper.getProperty("bettingPage", PropertyType.valueOf(exchangeType.name()), GLOBAL).orElse(null);

        return betPrices.stream()
                .map(betPrice -> {
                    TotalPointsBetOption teamPointsBetOption = new TotalPointsBetOption(getEvent(), getEventID(), selectionID, getLineID(), getPeriod(), totalType, getBetLineInfo().getPoints(), betPrice.getPrice(), getOddsFormat(), bettingPage, null, getMaxStake(), new HashMap<>(getAdditionalProperties()));
                    return new LayBackBetOption<TotalPointsBetOption>(teamPointsBetOption, exchangeType, betPrice);
                })
                .collect(Collectors.toList());
    }
}
