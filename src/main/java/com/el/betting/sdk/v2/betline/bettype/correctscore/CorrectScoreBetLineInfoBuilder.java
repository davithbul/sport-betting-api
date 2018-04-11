package com.el.betting.sdk.v2.betline.bettype.correctscore;

import com.el.betting.common.CollectionUtil;
import com.el.betting.sdk.v2.*;import com.el.betting.sdk.v4.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CorrectScoreBetLineInfoBuilder<T> {
    protected Event<? extends Participant> event;
    protected long eventID;
    protected String lineID;
    protected Period period;
    protected Stake maxStake;
    protected LocalDateTime startTime;
    protected List<ScoreSelection> scoreSelections = new LinkedList<>();
    protected OddsFormat oddsFormat;
    protected Map<String, Object> additionalProperties;

    public T setEventID(long eventID) {
        this.eventID = eventID;
        return (T) this;
    }

    public T setEvent(Event<? extends Participant> event) {
        this.event = event;
        return (T) this;
    }

    public T setLineID(String lineID) {
        this.lineID = lineID;
        return (T) this;
    }

    public T setPeriod(Period period) {
        this.period = period;
        return (T) this;
    }

    public T setMaxStake(Stake maxStake) {
        this.maxStake = maxStake;
        return (T) this;
    }

    public T setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
        return (T) this;
    }

    public T addScoreSelection(Score score, String selectionId) {
        final ScoreSelection<Number> scoreSelection = new ScoreSelection<>(score, selectionId);
        scoreSelections.add(scoreSelection);
        return (T) this;
    }

    public T setOddsFormat(OddsFormat oddsFormat) {
        this.oddsFormat = oddsFormat;
        return (T) this;
    }

    public T setAdditionalProperties(Map<String, Object> additionalProperties) {
        this.additionalProperties = additionalProperties;
        return (T) this;
    }

    public T addProperty(String name, Object value) {
        if (additionalProperties == null) {
            additionalProperties = new HashMap<>();
        }
        additionalProperties.put(name, value);
        return (T) this;
    }

    public CorrectScoreBetLineInfo createCorrectScoreBetLineInfo() {
        CorrectScoreBetLineInfo correctScoreBetLineInfo = new CorrectScoreBetLineInfo(eventID, lineID, period, maxStake, startTime, scoreSelections,
                oddsFormat,
                CollectionUtil.safeMap(additionalProperties));
        correctScoreBetLineInfo.setEvent(event.cloneWithoutBetLines());
        return correctScoreBetLineInfo;
    }
}