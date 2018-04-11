package com.el.betting.sdk.v2.betline.bettype.racewinner;

import com.el.betting.sdk.v2.*;
import com.el.betting.sdk.v2.betline.api.BetLine;
import com.el.betting.sdk.v2.betoption.bettype.winner.RaceWinnerBetOption;
import com.el.betting.sdk.v2.betoption.bettype.winner.RaceWinnerBetOptionBuilder;
import com.el.betting.sdk.v2.betoption.bettype.winner.RaceWinnerBetOptionInfo;
import com.el.betting.sdk.v4.race.WinnerCandidate;
import com.el.betting.sdk.v4.race.WinnerCandidatePrice;
import org.springframework.data.annotation.PersistenceConstructor;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RaceWinnerBetLine extends BetLine<RaceWinnerBetOption, RaceWinnerBetOptionInfo> {
    private final List<WinnerCandidatePrice> winnerCandidatePrices;
    private final String distance;

    @PersistenceConstructor
    protected RaceWinnerBetLine(long eventID, String lineID, Period period, MarketStatus marketStatus, Stake maxStake, LocalDateTime startTime, List<WinnerCandidatePrice> winnerCandidatePrices, String distance, Map<String, Object> additionalProperties) {
        super(eventID, lineID, period, BetType.TOTAL_POINTS, marketStatus, maxStake, OddsFormat.DECIMAL, startTime, additionalProperties);
        this.winnerCandidatePrices = winnerCandidatePrices;
        this.distance = distance;
    }

    public List<WinnerCandidatePrice> getWinnerCandidatePrices() {
        return winnerCandidatePrices;
    }

    public String getDistance() {
        return distance;
    }

    @Override
    public List<RaceWinnerBetOption> getBetOptions() {
        return winnerCandidatePrices.stream()
                .map(winnerCandidatePrice -> {
                    WinnerCandidate winnerCandidate = winnerCandidatePrice.getWinnerCandidate();
                    return new RaceWinnerBetOptionBuilder()
                            .setEvent(null)
                            .setEventID(getEventID())
                            .setSelectionID(winnerCandidate.getSelectionId())
                            .setLineID(getLineID())
                            .setPeriod(getPeriod())
                            .setParticipant(winnerCandidate.getParticipant())
                            .setDistance(distance)
                            .setPrice(winnerCandidatePrice.getPrice())
                            .setBettingPage(null).setMinStake(null).setMaxStake(null).setOddsFormat(getOddsFormat()).setAdditionalProperties(new HashMap<>())
                            .createRaceWinnerBetOption();
                }).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "RaceWinnerBetLine{" +
                "winnerCandidatePrices=" + winnerCandidatePrices +
                ", distance='" + distance + '\'' +
                "} " + super.toString();
    }
}
