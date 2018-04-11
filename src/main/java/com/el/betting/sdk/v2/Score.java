package com.el.betting.sdk.v2;

import java.io.Serializable;

public class Score<T extends Number> implements Serializable {
    private String score;
    private T homeSideScore;
    private T awaySideScore;


    public Score() {} //NOOP

    public Score(String score) {
        this.score = score;
    }

    public Score(String score, T homeSideScore, T awaySideScore) {
        this.score = score;
        this.homeSideScore = homeSideScore;
        this.awaySideScore = awaySideScore;
    }

    public Score(T homeSideScore, T awaySideScore) {
        this.homeSideScore = homeSideScore;
        this.awaySideScore = awaySideScore;
    }

    public String getScore() {
        return score;
    }

    public T getHomeSideScore() {
        return homeSideScore;
    }

    public T getAwaySideScore() {
        return awaySideScore;
    }

    @Override
    public String toString() {
        return "Score{" +
                "score='" + score + '\'' +
                ", homeSideScore=" + homeSideScore +
                ", awaySideScore=" + awaySideScore +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Score)) return false;

        Score<?> score1 = (Score<?>) o;

        if (score != null ? !score.equals(score1.score) : score1.score != null) return false;
        if (homeSideScore != null ? !homeSideScore.equals(score1.homeSideScore) : score1.homeSideScore != null)
            return false;
        return awaySideScore != null ? awaySideScore.equals(score1.awaySideScore) : score1.awaySideScore == null;

    }

    @Override
    public int hashCode() {
        int result = score != null ? score.hashCode() : 0;
        result = 31 * result + (homeSideScore != null ? homeSideScore.hashCode() : 0);
        result = 31 * result + (awaySideScore != null ? awaySideScore.hashCode() : 0);
        return result;
    }
}
