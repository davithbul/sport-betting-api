package com.el.betting.sdk.v2.betline.bettype.correctscore;

import com.el.betting.sdk.v2.MarketStatus;
import com.el.betting.sdk.v2.Score;
import com.el.betting.sdk.v2.ScorePrice;
import com.el.betting.sdk.v2.ScoreSelection;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

public class CorrectScoreBetLineBuilder extends CorrectScoreBetLineInfoBuilder<CorrectScoreBetLineBuilder> {
    private MarketStatus marketStatus;
    private List<ScorePrice> scorePriceList = new LinkedList<>();

    public CorrectScoreBetLineBuilder setMarketStatus(MarketStatus marketStatus) {
        this.marketStatus = marketStatus;
        return this;
    }

    public CorrectScoreBetLineBuilder addScorePrice(Score score, String selectionId, BigDecimal price) {
        addScoreSelection(score, selectionId);
        ScoreSelection scoreSelection = new ScoreSelection<>(score, selectionId);
        ScorePrice scorePrice = new ScorePrice<>(scoreSelection, price);
        scorePriceList.add(scorePrice);
        return this;
    }

   public CorrectScoreBetLine createCorrectScoreBetLine() {
        return new CorrectScoreBetLine(eventID, lineID, period, marketStatus, maxStake, startTime, scorePriceList, additionalProperties);
    }
}