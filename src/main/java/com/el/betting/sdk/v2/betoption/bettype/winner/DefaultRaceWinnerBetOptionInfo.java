package com.el.betting.sdk.v2.betoption.bettype.winner;

import com.el.betting.sdk.v2.BetType;
import com.el.betting.sdk.v2.Event;
import com.el.betting.sdk.v2.Period;
import com.el.betting.sdk.v2.Stake;
import com.el.betting.sdk.v2.betoption.api.AbstractBetOptionInfo;
import com.el.betting.sdk.v2.betoption.api.BetOptionInfo;
import com.el.betting.sdk.v2.pages.BettingPage;
import com.el.betting.sdk.v4.Participant;

import java.util.Map;

public class DefaultRaceWinnerBetOptionInfo extends AbstractBetOptionInfo implements BetOptionInfo, RaceWinnerBetOptionInfo {

    private final Participant participant;
    private final String distance;


    public DefaultRaceWinnerBetOptionInfo(Event<? extends Participant> event, long eventID, String selectionID, String lineID, Period period, Participant participant, String distance, BettingPage bettingPage, Map<String, Object> additionalProperties) {
        super(event, eventID, selectionID, lineID, period, bettingPage, additionalProperties);
        this.participant = participant;
        this.distance = distance;
    }

    public DefaultRaceWinnerBetOptionInfo(Event<? extends Participant> event, long eventID, String selectionID, String lineID, Period period, Participant participant, String distance, BettingPage bettingPage, Stake minStake, Stake maxStake, Map<String, Object> additionalProperties) {
        super(event, eventID, selectionID, lineID, period, bettingPage, minStake, maxStake, additionalProperties);
        this.participant = participant;
        this.distance = distance;
    }

    public DefaultRaceWinnerBetOptionInfo(AbstractBetOptionInfo betOptionInfo, Participant participant, String distance) {
        super(betOptionInfo);
        this.participant = participant;
        this.distance = distance;
    }

    @Override
    public Participant getParticipant() {
        return participant;
    }

    @Override
    public String getDistance() {
        return distance;
    }

    @Override
    public BetType getBetType() {
        return BetType.RACE_WINNER;
    }

    @Override
    public String toString() {
        return "DefaultRaceWinnerBetOptionInfo{" +
                "participant=" + participant +
                "} " + super.toString();
    }
}
