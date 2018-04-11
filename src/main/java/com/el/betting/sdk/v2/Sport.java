package com.el.betting.sdk.v2;

import com.el.betting.sdk.common.SportType;

import java.util.List;

public class Sport implements Cloneable {

    private long id;
    private String name;
    private SportType sportType;
    private boolean hasContent;
    private List<League> leagues;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SportType getSportType() {
        return sportType;
    }

    public void setSportType(SportType sportType) {
        this.sportType = sportType;
    }

    public boolean isHasContent() {
        return hasContent;
    }

    public void setHasContent(boolean hasContent) {
        this.hasContent = hasContent;
    }

    public List<League> getLeagues() {
        return leagues;
    }

    public void setLeagues(List<League> leagues) {
        this.leagues = leagues;
    }

    @Override
    public String toString() {
        return "Sport{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sportType=" + sportType +
                ", hasContent=" + hasContent +
                ", leagues=" + leagues +
                '}';
    }

    public Sport clone() {
        try {
            return (Sport) super.clone();
        } catch (CloneNotSupportedException e) {
            // This should never happen
            throw new InternalError(e.toString());
        }
    }
}
