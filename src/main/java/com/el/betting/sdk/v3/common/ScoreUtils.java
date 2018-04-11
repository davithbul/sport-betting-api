package com.el.betting.sdk.v3.common;

import com.el.betting.sdk.v2.Score;

import java.util.Objects;

public class ScoreUtils {

    public static boolean isEqualScores(Score<Integer> score1, Score<Integer> score2) {
        if (score1 == null || score2 == null) {
            return score1 == score2;
        }

        return Objects.equals(score1.getHomeSideScore(), score2.getHomeSideScore()) &&
                Objects.equals(score1.getAwaySideScore(), score2.getAwaySideScore());
    }
}
