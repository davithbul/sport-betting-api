package com.el.betting.sdk.v2.betline.bettype.correctscore;

import com.el.betting.sdk.v2.*;import com.el.betting.sdk.v4.*;
import com.el.betting.sdk.v2.betline.api.BetLineInfo;
import com.el.betting.sdk.v2.betoption.bettype.correctscore.CorrectScoreBetOptionInfo;
import com.el.betting.sdk.v2.betoption.bettype.correctscore.DefaultCorrectScoreBetOptionInfo;
import org.springframework.data.annotation.PersistenceConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Keeps in line all given score list and corresponding selection ids.
 */
public class CorrectScoreBetLineInfo extends BetLineInfo<CorrectScoreBetOptionInfo> {
    private final List<ScoreSelection> scoreSelections;

    @PersistenceConstructor
    protected CorrectScoreBetLineInfo(long eventID, String lineID, Period period, Stake maxStake, LocalDateTime startTime, List<ScoreSelection> scoreSelections, OddsFormat oddsFormat, Map<String, Object> additionalProperties) {
        super(eventID, lineID, period, BetType.CORRECT_SCORE, maxStake, oddsFormat, startTime, additionalProperties);
        this.scoreSelections = scoreSelections;
    }

    public List<ScoreSelection> getScoreSelections() {
        return scoreSelections;
    }

    @Override
    public List<CorrectScoreBetOptionInfo> getBetOptionsInfoList() {
        List<CorrectScoreBetOptionInfo> correctScoreBetOptionInfoList = new ArrayList<>();

        for (int i = 0; i < scoreSelections.size(); i++) {
            final ScoreSelection<Integer> scoreSelection = scoreSelections.get(i);
            final String selectionId = scoreSelection.getSelectionId();
            final Score<Integer> score = scoreSelection.getScore();
            final DefaultCorrectScoreBetOptionInfo correctScoreBetOptionInfo = new DefaultCorrectScoreBetOptionInfo(null, getEventID(), selectionId, getLineID(), getPeriod(), score.getHomeSideScore(), score.getAwaySideScore(), null, null, null, new HashMap<>());
            correctScoreBetOptionInfoList.add(correctScoreBetOptionInfo);
        }

        return correctScoreBetOptionInfoList;
    }

    @Override
    public String toString() {
        return "CorrectScoreBetLineInfo{" +
                "scoreSelections=" + scoreSelections +
                "} " + super.toString();
    }
}
