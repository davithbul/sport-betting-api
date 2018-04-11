package com.el.betting.sdk.v4.race;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Represents the candidate of winning race/marathon/etc along with
 * the probability (i.e. price) of winning the race.
 */
public class WinnerCandidatePrice implements Serializable {
    private final WinnerCandidate winnerCandidate;
    private final BigDecimal price;

    public WinnerCandidatePrice(WinnerCandidate winnerCandidate, BigDecimal price) {
        this.winnerCandidate = winnerCandidate;
        this.price = price;
    }

    public WinnerCandidate getWinnerCandidate() {
        return winnerCandidate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "WinnerCandidatePrice{" +
                "winnerCandidate=" + winnerCandidate +
                ", price=" + price +
                '}';
    }
}
