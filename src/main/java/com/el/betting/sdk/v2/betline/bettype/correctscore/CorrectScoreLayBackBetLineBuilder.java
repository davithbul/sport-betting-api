package com.el.betting.sdk.v2.betline.bettype.correctscore;

import com.el.betting.sdk.v2.*;import com.el.betting.sdk.v4.*;

import java.util.LinkedList;
import java.util.List;

public class CorrectScoreLayBackBetLineBuilder extends CorrectScoreBetLineInfoBuilder<CorrectScoreLayBackBetLineBuilder> {
    private MarketStatus marketStatus;
    private List<ScoreLayBackPrice> scoreLayBackPriceList = new LinkedList<>();

    public CorrectScoreLayBackBetLineBuilder setMarketStatus(MarketStatus marketStatus) {
        this.marketStatus = marketStatus;
        return this;
    }

    public CorrectScoreLayBackBetLineBuilder addScoreLayBackPrice(Score score, String selectionId, List<BetPrice> backPrices, List<BetPrice> layPrices) {
        addScoreSelection(score, selectionId);
        final ScoreSelection scoreSelection = new ScoreSelection<>(score, selectionId);
        final ScoreLayBackPrice<Number> scoreLayBackPrice = new ScoreLayBackPrice<>(scoreSelection, backPrices, layPrices);
        scoreLayBackPriceList.add(scoreLayBackPrice);
        return this;
    }

    public CorrectScoreLayBackBetLine createCorrectScoreLayBackBetLine() {
        CorrectScoreBetLineInfo correctScoreBetLineInfo = createCorrectScoreBetLineInfo();
        CorrectScoreLayBackBetLine correctScoreLayBackBetLine = new CorrectScoreLayBackBetLine(correctScoreBetLineInfo, marketStatus, scoreLayBackPriceList);
        correctScoreLayBackBetLine.setEvent(event != null ? event.cloneWithoutBetLines() : null);
        return correctScoreLayBackBetLine;
    }
}