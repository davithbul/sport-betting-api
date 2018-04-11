package com.el.betting.sdk.v2;

import com.el.betting.sdk.v2.betline.api.BetLine;
import com.el.betting.sdk.v2.betline.api.LayBackBetLine;
import com.el.betting.sdk.v4.Participant;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import javax.annotation.Nonnull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Represents general sport or political event, it might be game, or race or any other sport event.
 * Contains metadata describing event place, time and participants along with bet lines.
 * {@link Event} contains betLines since it describes sport event available for betting.
 */
public class Event<T extends Participant> implements Serializable {

    @Id
    private String mongoId;

    @Indexed
    private long id;

    private long leagueId;

    private long sportId;

    @Indexed
    private LocalDateTime startTime;

    private LocalDateTime updateTime;

    private boolean live;

    private EventStatus status;

    private List<T> participants;

    private List<BetLine> betLines = new ArrayList<>();

    private List<LayBackBetLine> layBackBetLines;

    private final Map<String, Object> additionalProperties = new HashMap<>();

    public String getMongoId() {
        return mongoId;
    }

    public void setMongoId(String mongoId) {
        this.mongoId = mongoId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(long leagueId) {
        this.leagueId = leagueId;
    }

    public long getSportId() {
        return sportId;
    }

    public void setSportId(long sportId) {
        this.sportId = sportId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    public EventStatus getStatus() {
        return status;
    }

    public void setStatus(EventStatus status) {
        this.status = status;
    }

    public List<T> getParticipants() {
        return participants;
    }

    public void setParticipants(List<T> participants) {
        this.participants = participants;
    }

    public void addBetLines(List<BetLine> betLines) {
        betLines.forEach(this::addBetLine);
    }

    public void addBetLine(@Nonnull BetLine betLine) {
        betLines.add(betLine);
    }

    public void setBetLines(BetType betType, List<? extends BetLine> betLines) {
        ArrayList<BetLine> updatedBetLiens = betLines.stream()
                .filter(betLine -> betLine.getBetType() != betType)
                .collect(Collectors.toCollection(ArrayList::new));
        updatedBetLiens.addAll(betLines);
    }

    /**
     * Removes bet lines having type - betType
     */
    public void cleanBetLines(@Nonnull BetType betType) {
        betLines = betLines.stream()
                .filter(betLine -> betLine.getBetType() != betType)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public List<LayBackBetLine> getLayBackBetLines() {
        return layBackBetLines != null ? layBackBetLines : Collections.emptyList();
    }

    public void addLayBackBetLine(LayBackBetLine layBackBetOption) {
        if (layBackBetLines == null) {
            layBackBetLines = new ArrayList<>();
        }
        layBackBetLines.add(layBackBetOption);
    }

    public void setLayBackBetLines(List<LayBackBetLine> layBackBetLines) {
        this.layBackBetLines = layBackBetLines;
    }

    @JsonIgnore
    public List<BetLine> getBetLines() {
        List<BetLine> allBetLines = new ArrayList<>();
        allBetLines.addAll(betLines);
        if (layBackBetLines != null) {
            betLines.addAll(layBackBetLines);
        }

        return allBetLines;
    }

    @JsonIgnore
    public List<BetLine> getBetLine(BetType betType) {
        return getBetLine(betType, BetLine.class);
    }

    @JsonIgnore
    @SuppressWarnings("unchecked")
    public <T extends BetLine> List<T> getBetLine(BetType betType, Class<T> clazz) {
        return (List<T>) betLines.stream()
                .filter(betLine -> betLine.getBetType() == betType)
                .collect(Collectors.toList());
    }

    public Map<String, Object> getAdditionalProperties() {
        return additionalProperties;
    }

    public void addProperty(String name, Object value) {
        additionalProperties.put(name, value);
    }

    public Object getProperty(String name) {
        return additionalProperties.get(name);
    }

    public void addAdditionalProperties(Map<String, Object> additionalProperties) {
        this.additionalProperties.putAll(additionalProperties);
    }

    public Event<? extends Participant> cloneWithoutBetLines() {
        Event<? extends Participant> event = new Event<>();
        event.mongoId = mongoId;
        event.id = id;
        event.leagueId = leagueId;
        event.sportId = sportId;
        event.startTime = startTime;
        event.updateTime = updateTime;
        event.live = live;
        event.status = status;
        return event;
    }

    @Override
    public String toString() {
        return "Event{" +
                "additionalProperties=" + additionalProperties +
                ", mongoId='" + mongoId + '\'' +
                ", id=" + id +
                ", leagueId=" + leagueId +
                ", sportId=" + sportId +
                ", startTime=" + startTime +
                ", updateTime=" + updateTime +
                ", live=" + live +
                ", status=" + status +
                ", participants=" + participants +
                ", betLines=" + betLines +
                ", layBackBetLines=" + layBackBetLines +
                '}';
    }
}
