package com.el.betting.sdk.v2.betline.bettype.racewinner;

import com.el.betting.sdk.v2.*;
import com.el.betting.sdk.v4.Participant;
import com.el.betting.sdk.v4.race.WinnerCandidate;
import com.el.betting.sdk.v4.race.WinnerCandidateLayBackPrice;

import java.util.LinkedList;
import java.util.List;

public class RaceWinnerLayBackBetLineBuilder extends RaceWinnerBetLineInfoBuilder<RaceWinnerLayBackBetLineBuilder> {
    private MarketStatus marketStatus;
    private List<WinnerCandidateLayBackPrice> winnerCandidateLayBackPrices = new LinkedList<>();

    public RaceWinnerLayBackBetLineBuilder setMarketStatus(MarketStatus marketStatus) {
        this.marketStatus = marketStatus;
        return this;
    }

    public RaceWinnerLayBackBetLineBuilder addWinnerCandidateLayBackPrice(Participant participant, String selectionId, List<BetPrice> backPrices, List<BetPrice> layPrices) {
        addWinnerCandidate(participant, selectionId);
        WinnerCandidate winnerCandidate = new WinnerCandidate(participant, selectionId);
        WinnerCandidateLayBackPrice winnerCandidateLayBackPrice = new WinnerCandidateLayBackPrice(winnerCandidate, backPrices, layPrices);
        winnerCandidateLayBackPrices.add(winnerCandidateLayBackPrice);
        return this;
    }

    public RaceWinnerLayBackBetLine createRaceWinnerLayBackBetLine() {
        RaceWinnerBetLineInfo winnerBetLineInfo = createWinnerBetLineInfo();
        RaceWinnerLayBackBetLine raceWinnerLayBackBetLine = new RaceWinnerLayBackBetLine(winnerBetLineInfo, marketStatus, winnerCandidateLayBackPrices);
        raceWinnerLayBackBetLine.setEvent(event != null ? event.cloneWithoutBetLines() : null);
        return raceWinnerLayBackBetLine;
    }
}