package com.el.betting.sdk.v2.criteria;

import com.el.betting.sdk.v2.BetType;
import com.el.betting.sdk.v2.EventStatus;
import com.el.betting.sdk.v2.TimeRange;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class EventFilters extends LeagueFilters {
    private String textQuery;
    private Set<Long> eventIDs;
    private Set<String> lineIDs;
    private Set<String> venues;
    private Boolean live;
    private Set<EventStatus> eventStatuses;
    private Set<BetType> includedBetTypes;
    private TimeRange<LocalDateTime> eventStartTime;

    public final static EventFilters NO_FILTER = new EventFilters();

    public String getTextQuery() {
        return textQuery;
    }

    public void setTextQuery(String textQuery) {
        this.textQuery = textQuery;
    }

    public Set<Long> getEventIDs() {
        return eventIDs;
    }

    public void setEventIDs(Set<Long> eventIDs) {
        this.eventIDs = eventIDs;
    }

    public void addEventID(long eventID) {
        if (eventIDs == null) {
            eventIDs = new HashSet<>();
        }
        eventIDs.add(eventID);
    }

    public Set<String> getLineIDs() {
        return lineIDs;
    }

    public void setLineIDs(Set<String> lineIDs) {
        this.lineIDs = lineIDs;
    }

    public void addLineID(String lineID) {
        if (lineIDs == null) {
            lineIDs = new HashSet<>();
        }
        lineIDs.add(lineID);
    }

    public Boolean isLive() {
        return live;
    }

    public void setLive(Boolean live) {
        this.live = live;
    }

    public Set<String> getVenues() {
        return venues;
    }

    public void setVenues(Set<String> venues) {
        this.venues = venues;
    }

    public Set<EventStatus> getEventStatuses() {
        return eventStatuses;
    }

    public void setEventStatuses(Set<EventStatus> eventStatuses) {
        this.eventStatuses = eventStatuses;
    }

    public Set<BetType> getIncludedBetTypes() {
        return includedBetTypes;
    }

    public void includeBetType(BetType betType) {
        if(includedBetTypes == null) {
            includedBetTypes = new HashSet<>();
        }

        includedBetTypes.add(betType);
    }

    public void setIncludedBetTypes(Set<BetType> includedBetTypes) {
        this.includedBetTypes = includedBetTypes;
    }

    public TimeRange<LocalDateTime> getEventStartTime() {
        return eventStartTime;
    }

    public void setEventStartTime(TimeRange<LocalDateTime> eventStartTime) {
        this.eventStartTime = eventStartTime;
    }

    @Override
    public EventFilters clone() {
        try {
            EventFilters clone = (EventFilters) super.clone();
            if(eventIDs != null ) {
                clone.setEventIDs(new HashSet<>(eventIDs));
            }
            if(venues != null) {
                clone.setVenues(new HashSet<>(venues));
            }
            if(eventStatuses != null ) {
                clone.setEventStatuses(new HashSet<>(eventStatuses));
            }
            if(includedBetTypes != null ) {
                clone.setIncludedBetTypes(new HashSet<>(includedBetTypes));
            }
            return clone;
        } catch (CloneNotSupportedException e) {
            // This should never happen
            throw new InternalError(e.toString());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EventFilters)) return false;
        if (!super.equals(o)) return false;

        EventFilters that = (EventFilters) o;

        if (textQuery != null ? !textQuery.equals(that.textQuery) : that.textQuery != null) return false;
        if (eventIDs != null ? !eventIDs.equals(that.eventIDs) : that.eventIDs != null) return false;
        if (venues != null ? !venues.equals(that.venues) : that.venues != null) return false;
        if (eventStatuses != null ? !eventStatuses.equals(that.eventStatuses) : that.eventStatuses != null)
            return false;
        return !(eventStartTime != null ? !eventStartTime.equals(that.eventStartTime) : that.eventStartTime != null);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (textQuery != null ? textQuery.hashCode() : 0);
        result = 31 * result + (eventIDs != null ? eventIDs.hashCode() : 0);
        result = 31 * result + (venues != null ? venues.hashCode() : 0);
        result = 31 * result + (eventStatuses != null ? eventStatuses.hashCode() : 0);
        result = 31 * result + (eventStartTime != null ? eventStartTime.hashCode() : 0);
        return result;
    }
}
