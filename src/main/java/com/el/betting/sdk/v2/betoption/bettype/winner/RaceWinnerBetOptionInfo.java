package com.el.betting.sdk.v2.betoption.bettype.winner;

import com.el.betting.sdk.v2.betoption.api.BetOptionInfo;
import com.el.betting.sdk.v4.Participant;

public interface RaceWinnerBetOptionInfo extends BetOptionInfo {

    /**
     * The participant of a race
     */
    Participant getParticipant();

    /**
     * The distance of race.
     */
    String getDistance();

}
