package com.el.betting.sdk.v2;

import java.io.Serializable;
import java.util.List;

public class ScoreLayBackPrice<T extends Number> implements Serializable {
    private final ScoreSelection<T> scoreSelection;
    private final List<BetPrice> backPrices;
    private final List<BetPrice> layPrices;

    public ScoreLayBackPrice(ScoreSelection<T> scoreSelection, List<BetPrice> backPrices, List<BetPrice> layPrices) {
        this.scoreSelection = scoreSelection;
        this.backPrices = backPrices;
        this.layPrices = layPrices;
    }

    public ScoreSelection<T> getScoreSelection() {
        return scoreSelection;
    }

    public List<BetPrice> getBackPrices() {
        return backPrices;
    }

    public List<BetPrice> getLayPrices() {
        return layPrices;
    }
}
