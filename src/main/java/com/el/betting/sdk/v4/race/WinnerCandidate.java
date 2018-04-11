package com.el.betting.sdk.v4.race;

import com.el.betting.sdk.v4.Participant;

import java.io.Serializable;

public class WinnerCandidate implements Serializable {
    private final Participant participant;
    private final String selectionId;

    public WinnerCandidate(Participant participant, String selectionId) {
        this.participant = participant;
        this.selectionId = selectionId;
    }

    public Participant getParticipant() {
        return participant;
    }

    public String getSelectionId() {
        return selectionId;
    }

    @Override
    public String toString() {
        return "WinnerCandidate{" +
                "raceParticipant=" + participant +
                ", selectionId='" + selectionId + '\'' +
                '}';
    }
}
