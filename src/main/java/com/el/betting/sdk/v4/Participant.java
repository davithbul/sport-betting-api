package com.el.betting.sdk.v4;

import java.io.Serializable;

/**
 * Represents participant of a game, race or any other event.
 * Might be team, or politician, etc.
 */
public class Participant implements Serializable {

    /**
     * Name of the participant.
     */
    private String name;

    protected Participant() {
    }

    public Participant(String name) {
        this.name = name;
    }

    protected void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Participant{" +
                "name='" + name + '\'' +
                '}';
    }
}
