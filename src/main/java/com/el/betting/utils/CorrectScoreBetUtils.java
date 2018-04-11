package com.el.betting.utils;

import com.el.betting.sdk.v2.*;import com.el.betting.sdk.v4.*;
import com.el.betting.sdk.v2.betline.api.LayBackBetLine;
import com.el.betting.sdk.v2.betline.bettype.correctscore.CorrectScoreLayBackBetLine;
import com.el.betting.sdk.v3.common.ScoreUtils;
import com.el.betting.sdk.v4.Participant;
import org.springframework.util.CollectionUtils;

import java.util.Optional;

public class CorrectScoreBetUtils {


    public static Optional<Double> getPrice(Event<? extends Participant> event, Period period, Score<Integer> score) {
        for (LayBackBetLine layBackBetLine : event.getLayBackBetLines()) {
            if (layBackBetLine.getBetType() != BetType.CORRECT_SCORE ||
                    layBackBetLine.getPeriod() != period) {
                continue;
            }

            CorrectScoreLayBackBetLine correctScoreBetLine = (CorrectScoreLayBackBetLine) layBackBetLine;
            for (ScoreLayBackPrice scoreLayBackPrice : correctScoreBetLine.getScoreLayBackPriceList()) {
                Score<Integer> offeredScore = scoreLayBackPrice.getScoreSelection().getScore();
                //filter if lay is not available
                if (ScoreUtils.isEqualScores(score, offeredScore)) {
                    if (!CollectionUtils.isEmpty(scoreLayBackPrice.getBackPrices())) {
                        return Optional.of(((BetPrice) scoreLayBackPrice.getBackPrices().get(0)).getPrice().doubleValue());
                    } else {
                        return Optional.empty();
                    }
                }
            }
        }

        return Optional.empty();
    }
}
