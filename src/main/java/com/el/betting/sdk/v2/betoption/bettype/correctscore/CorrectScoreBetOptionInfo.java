package com.el.betting.sdk.v2.betoption.bettype.correctscore;

import com.el.betting.sdk.v2.betoption.api.BetOptionInfo;

import java.math.BigDecimal;

public interface CorrectScoreBetOptionInfo extends BetOptionInfo {

    /**
     * The score(e.g. goal count) which was scored by home side
     */
    int getHomeSideScore();

    /**
     * Goal count scored by away side
     */
    int getAwaySideScore();
}
