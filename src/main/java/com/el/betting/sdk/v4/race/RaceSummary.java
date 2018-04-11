package com.el.betting.sdk.v4.race;

import com.el.betting.sdk.v4.Participant;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * {@link RaceSummary} is a model object which has summary
 * level information about race.
 * Race might mean - horse race, Running race, etc.
 */
@Document
public class RaceSummary<T extends Participant> {
    @Id
    private String id;
    private List<T> participants;
    private T winner;
    private T secondPlace;
    private T thirdPlace;
    private T fourthPlace;

    public List<T> getParticipants() {
        return participants;
    }

    public void setParticipants(List<T> participants) {
        this.participants = participants;
    }

    public T getWinner() {
        return winner;
    }

    public void setWinner(T winner) {
        this.winner = winner;
    }

    public T getSecondPlace() {
        return secondPlace;
    }

    public void setSecondPlace(T secondPlace) {
        this.secondPlace = secondPlace;
    }

    public T getThirdPlace() {
        return thirdPlace;
    }

    public void setThirdPlace(T thirdPlace) {
        this.thirdPlace = thirdPlace;
    }

    public T getFourthPlace() {
        return fourthPlace;
    }

    public void setFourthPlace(T fourthPlace) {
        this.fourthPlace = fourthPlace;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
