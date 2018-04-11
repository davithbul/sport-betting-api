package com.el.betting.sdk.v2.betline.bettype.racewinner;

import com.el.betting.sdk.v2.*;
import com.el.betting.sdk.v2.betline.api.BetLineInfo;
import com.el.betting.sdk.v2.betoption.bettype.winner.DefaultRaceWinnerBetOptionInfo;
import com.el.betting.sdk.v2.betoption.bettype.winner.RaceWinnerBetOptionInfo;
import com.el.betting.sdk.v4.Participant;
import com.el.betting.sdk.v4.race.WinnerCandidate;
import org.springframework.data.annotation.PersistenceConstructor;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Keeps in line all given winner candidate's list and corresponding selection ids.
 */
public class RaceWinnerBetLineInfo extends BetLineInfo<RaceWinnerBetOptionInfo> {
    private final List<WinnerCandidate> winnerCandidates;
    private final String distance;

    @PersistenceConstructor
    protected RaceWinnerBetLineInfo(long eventID, String lineID, Period period, Stake maxStake, LocalDateTime startTime, List<WinnerCandidate> winnerCandidates, String distance, OddsFormat oddsFormat, Map<String, Object> additionalProperties) {
        super(eventID, lineID, period, BetType.RACE_WINNER, maxStake, oddsFormat, startTime, additionalProperties);
        this.winnerCandidates = winnerCandidates;
        this.distance = distance;
    }

    public String getDistance() {
        return distance;
    }

    public List<WinnerCandidate> getWinnerCandidates() {
        return winnerCandidates;
    }

    @Override
    public List<RaceWinnerBetOptionInfo> getBetOptionsInfoList() {
        return winnerCandidates.stream()
        .map(winnerCandidate -> {
            Participant participant = winnerCandidate.getParticipant();
            String selectionId = winnerCandidate.getSelectionId();
            return new DefaultRaceWinnerBetOptionInfo(null, getEventID(), selectionId, getLineID(), getPeriod(), participant, distance, null, null, null, new HashMap<>());
        }).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "RaceWinnerBetLineInfo{" +
                "winnerCandidates=" + winnerCandidates +
                ", distance='" + distance + '\'' +
                "} " + super.toString();
    }
}
