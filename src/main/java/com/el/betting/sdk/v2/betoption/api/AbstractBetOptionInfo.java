package com.el.betting.sdk.v2.betoption.api;

import com.el.betting.sdk.v2.*;import com.el.betting.sdk.v4.*;
import com.el.betting.sdk.v2.pages.BettingPage;
import com.el.betting.sdk.v4.Participant;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import java.util.Map;

@Immutable
public abstract class AbstractBetOptionInfo implements BetOptionInfo {
    private final long eventID;
    @Nullable private Event<? extends Participant> event;
    private final String selectionID;
    private final String lineID;
    private final Period period;
    private final BettingPage bettingPage;
    @Nullable private Stake minStake;
    @Nullable private Stake maxStake;
    private final Map<String, Object> additionalProperties;

    public AbstractBetOptionInfo(Event<? extends Participant> event, long eventID, String selectionID, String lineID, Period period, BettingPage bettingPage, Map<String, Object> additionalProperties) {
        this(event, eventID, selectionID, lineID, period, bettingPage, null, null, additionalProperties);
    }

    public AbstractBetOptionInfo(Event<? extends Participant> event, long eventID, String selectionID, String lineID, Period period, BettingPage bettingPage, Stake minStake, Stake maxStake, Map<String, Object> additionalProperties) {
        this.event = event;
        this.eventID = eventID;
        this.lineID = lineID;
        this.selectionID = selectionID;
        this.period = period;
        this.bettingPage = bettingPage;
        this.minStake = minStake;
        this.maxStake = maxStake;
        this.additionalProperties = additionalProperties;
    }

    public AbstractBetOptionInfo(BetOptionInfo betOptionInfo) {
        this(betOptionInfo.getEvent(), betOptionInfo.getEventID(), betOptionInfo.getSelectionID(), betOptionInfo.getLineID(),
                betOptionInfo.getPeriod(), betOptionInfo.getBettingPage(), betOptionInfo.getMinStake(),
                betOptionInfo.getMaxStake(), betOptionInfo.getAdditionalProperties());
    }

    public String getLineID() {
        return lineID;
    }

    public abstract BetType getBetType();

    @Override
    public Event<? extends Participant> getEvent() {
        return event;
    }

    public long getEventID() {
        return eventID;
    }

    public Stake getMinStake() {
        return minStake;
    }

    public Stake getMaxStake() {
        return maxStake;
    }

    @Override
    public Period getPeriod() {
        return period;
    }

    public String getSelectionID() {
        return selectionID;
    }

    public BettingPage getBettingPage() {
        return bettingPage;
    }

    public void addProperty(String name, Object value) {
        additionalProperties.put(name, value);
    }

    public Map<String, Object> getAdditionalProperties() {
        return additionalProperties;
    }

    public Object getProperty(String propertyName) {
        return additionalProperties.get(propertyName);
    }

    public <T> T getProperty(String propertyName, Class<T> type) {
        return (T) additionalProperties.get(propertyName);
    }

    public void setMinStake(Stake minStake) {
        this.minStake = minStake;
    }

    public void setMaxStake(Stake maxStake) {
        this.maxStake = maxStake;
    }

    public void setEvent(Event<? extends Participant> event) {
        this.event = event;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractBetOptionInfo)) return false;

        AbstractBetOptionInfo that = (AbstractBetOptionInfo) o;

        if (eventID != that.eventID) return false;
        if (selectionID != null ? !selectionID.equals(that.selectionID) : that.selectionID != null) return false;
        if (lineID != null ? !lineID.equals(that.lineID) : that.lineID != null) return false;
        if (bettingPage != null ? !bettingPage.equals(that.bettingPage) : that.bettingPage != null) return false;
        if (minStake != null ? !minStake.equals(that.minStake) : that.minStake != null) return false;
        if (maxStake != null ? !maxStake.equals(that.maxStake) : that.maxStake != null) return false;
        return additionalProperties != null ? additionalProperties.equals(that.additionalProperties) : that.additionalProperties == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (eventID ^ (eventID >>> 32));
        result = 31 * result + (selectionID != null ? selectionID.hashCode() : 0);
        result = 31 * result + (lineID != null ? lineID.hashCode() : 0);
        result = 31 * result + (bettingPage != null ? bettingPage.hashCode() : 0);
        result = 31 * result + (minStake != null ? minStake.hashCode() : 0);
        result = 31 * result + (maxStake != null ? maxStake.hashCode() : 0);
        result = 31 * result + (additionalProperties != null ? additionalProperties.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AbstractBetOptionInfo{" +
                "eventID=" + eventID +
                ", selectionID='" + selectionID + '\'' +
                ", lineID='" + lineID + '\'' +
                ", period=" + period +
                ", minStake=" + minStake +
                ", maxStake=" + maxStake +
                '}';
    }
}
