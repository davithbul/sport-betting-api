package com.el.betting.sdk.v2.betline.bettype.correctscore;

import com.el.betting.sdk.v2.*;import com.el.betting.sdk.v4.*;
import com.el.betting.sdk.v2.betline.api.BetLine;
import com.el.betting.sdk.v2.betoption.bettype.correctscore.CorrectScoreBetOption;
import com.el.betting.sdk.v2.betoption.bettype.correctscore.CorrectScoreBetOptionBuilder;
import com.el.betting.sdk.v2.betoption.bettype.correctscore.CorrectScoreBetOptionInfo;
import org.springframework.data.annotation.PersistenceConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CorrectScoreBetLine extends BetLine<CorrectScoreBetOption, CorrectScoreBetOptionInfo> {
    private final List<ScorePrice> scorePrices;

    @PersistenceConstructor
    protected CorrectScoreBetLine(long eventID, String lineID, Period period, MarketStatus marketStatus, Stake maxStake, LocalDateTime startTime, List<ScorePrice> scorePrices, Map<String, Object> additionalProperties) {
        super(eventID, lineID, period, BetType.TOTAL_POINTS, marketStatus, maxStake, OddsFormat.DECIMAL, startTime, additionalProperties);
        this.scorePrices = scorePrices;
    }

    public List<ScorePrice> getScorePrices() {
        return scorePrices;
    }

    @Override
    public List<CorrectScoreBetOption> getBetOptions() {
        List<CorrectScoreBetOption> betOptions = new ArrayList<>();

        for (int i = 0; i < scorePrices.size(); i++) {
            final ScorePrice scorePrice = scorePrices.get(i);
            final ScoreSelection scoreSelection = scorePrice.getScoreSelection();
            final Score<Integer> score = scoreSelection.getScore();
            final CorrectScoreBetOption correctScoreBetOption = new CorrectScoreBetOptionBuilder().setEvent(null).setEventID(getEventID())
                    .setSelectionID(scoreSelection.getSelectionId())
                    .setLineID(getLineID())
                    .setPeriod(getPeriod())
                    .setHomeSideScore(score.getHomeSideScore())
                    .setAwaySidePoints(score.getHomeSideScore())
                    .setPrice(scorePrice.getPrice())
                    .setBettingPage(null).setMinStake(null).setMaxStake(null).setOddsFormat(getOddsFormat()).setAdditionalProperties(new HashMap<>()).createCorrectScoreBetOption();
            betOptions.add(correctScoreBetOption);
        }

        return betOptions;
    }

    @Override
    public String toString() {
        return "CorrectScoreBetLine{" +
                "scorePrices=" + scorePrices +
                "} " + super.toString();
    }
}
