package com.el.betting.sdk.v2.betoption.bettype.correctscore;

import com.el.betting.sdk.v2.BetType;
import com.el.betting.sdk.v2.Event;import com.el.betting.sdk.v4.*;
import com.el.betting.sdk.v2.Period;
import com.el.betting.sdk.v2.Stake;
import com.el.betting.sdk.v2.betoption.api.AbstractBetOptionInfo;
import com.el.betting.sdk.v2.betoption.api.BetOptionInfo;
import com.el.betting.sdk.v2.pages.BettingPage;
import com.el.betting.sdk.v4.Participant;

import java.util.Map;

public class DefaultCorrectScoreBetOptionInfo extends AbstractBetOptionInfo implements BetOptionInfo, CorrectScoreBetOptionInfo {

    private final int homeSideScore;
    private final int awaySideScore;

    public DefaultCorrectScoreBetOptionInfo(Event<? extends Participant> event, long eventID, String selectionID, String lineID, Period period, int homeSideScore, int awaySideScore, BettingPage bettingPage, Map<String, Object> additionalProperties) {
        super(event, eventID, selectionID, lineID, period, bettingPage, additionalProperties);
        this.homeSideScore = homeSideScore;
        this.awaySideScore = awaySideScore;
    }

    public DefaultCorrectScoreBetOptionInfo(Event<? extends Participant> event, long eventID, String selectionID, String lineID, Period period, int homeSideScore, int awaySideScore, BettingPage bettingPage, Stake minStake, Stake maxStake, Map<String, Object> additionalProperties) {
        super(event, eventID, selectionID, lineID, period, bettingPage, minStake, maxStake, additionalProperties);
        this.homeSideScore = homeSideScore;
        this.awaySideScore = awaySideScore;
    }

    public DefaultCorrectScoreBetOptionInfo(AbstractBetOptionInfo betOptionInfo, int homeSideScore, int awaySideScore) {
        super(betOptionInfo);
        this.homeSideScore = homeSideScore;
        this.awaySideScore = awaySideScore;
    }

    @Override
    public int getHomeSideScore() {
        return homeSideScore;
    }

    @Override
    public int getAwaySideScore() {
        return awaySideScore;
    }

    @Override
    public BetType getBetType() {
        return BetType.CORRECT_SCORE;
    }

    @Override
    public String toString() {
        return "DefaultCorrectScoreBetOptionInfo{" +
                "homeTeamGoalCount=" + homeSideScore +
                ", awayTeamGoalCount=" + awaySideScore +
                "} " + super.toString();
    }
}
