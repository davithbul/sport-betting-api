package com.el.betting.sdk.v2;

import java.io.Serializable;

public class ScoreSelection<T extends Number> implements Serializable{

    private final Score<T> score;
    private final String selectionId;

    public ScoreSelection(Score<T> score, String selectionId) {
        this.score = score;
        this.selectionId = selectionId;
    }

    public Score<T> getScore() {
        return score;
    }

    public String getSelectionId() {
        return selectionId;
    }
}
