package com.el.betting.sdk.v2;

public class FootballScore extends Score<Integer> {

    public FootballScore(String score) {
        super(score);
    }

    public FootballScore(String score, Integer homeSideScore, Integer awaySideScore) {
        super(score, homeSideScore, awaySideScore);
    }

    public FootballScore(Integer homeSideScore, Integer awaySideScore) {
        super(homeSideScore, awaySideScore);
    }
}
