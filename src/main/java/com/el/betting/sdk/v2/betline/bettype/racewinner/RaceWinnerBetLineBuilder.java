package com.el.betting.sdk.v2.betline.bettype.racewinner;

import com.el.betting.sdk.v2.MarketStatus;
import com.el.betting.sdk.v4.Participant;
import com.el.betting.sdk.v4.race.WinnerCandidate;
import com.el.betting.sdk.v4.race.WinnerCandidatePrice;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

public class RaceWinnerBetLineBuilder extends RaceWinnerBetLineInfoBuilder<RaceWinnerBetLineBuilder> {
    private MarketStatus marketStatus;
    private List<WinnerCandidatePrice> winnerCandidatePrices = new LinkedList<>();

    public RaceWinnerBetLineBuilder setMarketStatus(MarketStatus marketStatus) {
        this.marketStatus = marketStatus;
        return this;
    }

    public RaceWinnerBetLineBuilder addWinnerCandidatePrice(Participant participant, String selectionId, BigDecimal price) {
        addWinnerCandidate(participant, selectionId);
        WinnerCandidate winnerCandidate = new WinnerCandidate(participant, selectionId);
        WinnerCandidatePrice winnerCandidatePrice = new WinnerCandidatePrice(winnerCandidate, price);
        winnerCandidatePrices.add(winnerCandidatePrice);
        return this;
    }

   public RaceWinnerBetLine createRaceWinnerBetLine() {
        return new RaceWinnerBetLine(eventID, lineID, period, marketStatus, maxStake, startTime, winnerCandidatePrices, distance, additionalProperties);
    }
}