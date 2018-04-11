package com.el.betting.sdk.v2;

import java.math.BigDecimal;

public class ScorePrice<T extends Number> {
    private final ScoreSelection<T> scoreSelection;
    private final BigDecimal price;

    public ScorePrice(ScoreSelection<T> scoreSelection, BigDecimal price) {
        this.scoreSelection = scoreSelection;
        this.price = price;
    }

    public ScoreSelection<T> getScoreSelection() {
        return scoreSelection;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
