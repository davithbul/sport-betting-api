package com.el.betting.sdk.v4.race;

import com.el.betting.sdk.v2.BetPrice;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Represents the candidate of winning race/marathon/etc along with
 * the lay back prices of winning the race.
 */
public class WinnerCandidateLayBackPrice implements Serializable {
    private final WinnerCandidate winnerCandidate;
    private final List<BetPrice> backPrices;
    private final List<BetPrice> layPrices;

    public WinnerCandidateLayBackPrice(WinnerCandidate winnerCandidate, List<BetPrice> backPrices, List<BetPrice> layPrices) {
        this.winnerCandidate = winnerCandidate;
        this.backPrices = backPrices;
        this.layPrices = layPrices;
    }

    public WinnerCandidate getWinnerCandidate() {
        return winnerCandidate;
    }

    public List<BetPrice> getBackPrices() {
        return backPrices;
    }

    public List<BetPrice> getLayPrices() {
        return layPrices;
    }

    @Override
    public String toString() {
        return "WinnerCandidateLayBackPrice{" +
                "winnerCandidate=" + winnerCandidate +
                ", backPrices=" + backPrices +
                ", layPrices=" + layPrices +
                '}';
    }
}
