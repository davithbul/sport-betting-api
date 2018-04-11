package com.el.betting.sdk.v4.race;

import com.el.betting.sdk.v4.Participant;

import javax.annotation.Nullable;

public class HorseRaceParticipant extends Participant {

    @Nullable private int number;
    private final String jockey;
    private final String trainer;

    public HorseRaceParticipant(String name, int number, String jockey, String trainer) {
        super(name);
        this.number = number;
        this.jockey = jockey;
        this.trainer = trainer;
    }

    public HorseRaceParticipant(String name, String jockey, String trainer) {
        super(name);
        this.jockey = jockey;
        this.trainer = trainer;
    }

    public String getJockey() {
        return jockey;
    }

    public String getTrainer() {
        return trainer;
    }

    public int getNumber() {
        return number;
    }
}
