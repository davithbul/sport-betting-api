package com.el.betting.sdk.v2.criteria;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SportFilters implements Cloneable {

    public final static SportFilters EMPTY = new SportFilters();

    private Set<Long> sportIDs;
    private final Map<String, Object> additionalFilters = new HashMap<>();

    public Set<Long> getSportIDs() {
        return sportIDs;
    }

    public void setSportIDs(Set<Long> sportIDs) {
        this.sportIDs = sportIDs;
    }

    public void addSportID(long sportID) {
        if (sportIDs == null) {
            sportIDs = new HashSet<>();
        }
        sportIDs.add(sportID);
    }

    public void setAdditionalFilter(String name, Object value) {
        additionalFilters.put(name, value);
    }

    public Map<String, Object> getAdditionalFilters() {
        return additionalFilters;
    }

    public Object getFilter(String name) {
        return additionalFilters.get(name);
    }

    public boolean hasFilter(String name) {
        return additionalFilters.containsKey(name);
    }

    @Override
    protected SportFilters clone() throws CloneNotSupportedException {
        try {
            SportFilters clone = (SportFilters) super.clone();
            if (sportIDs != null) {
                clone.setSportIDs(new HashSet<>(sportIDs));
            }
            clone.additionalFilters.putAll(additionalFilters);
            return clone;
        } catch (CloneNotSupportedException e) {
            // This should never happen
            throw new InternalError(e.toString());
        }
    }

    public boolean hasFilters() {
        return this.hashCode() != 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SportFilters)) return false;

        SportFilters that = (SportFilters) o;

        if (sportIDs != null ? !sportIDs.equals(that.sportIDs) : that.sportIDs != null) return false;
        return additionalFilters != null ? additionalFilters.equals(that.additionalFilters) : that.additionalFilters == null;

    }

    @Override
    public int hashCode() {
        int result = sportIDs != null ? sportIDs.hashCode() : 0;
        result = 31 * result + (additionalFilters != null ? additionalFilters.hashCode() : 0);
        return result;
    }
}
