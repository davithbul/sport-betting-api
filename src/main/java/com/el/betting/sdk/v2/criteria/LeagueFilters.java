package com.el.betting.sdk.v2.criteria;

import com.el.betting.sdk.v2.EventStatus;
import com.el.betting.sdk.v2.TimeRange;

import java.util.HashSet;
import java.util.Set;

public class LeagueFilters extends SportFilters {
    private Set<Long> leagueIds;

    public Set<Long> getLeagueIds() {
        return leagueIds;
    }

    public void setLeagueIds(Set<Long> leagueIds) {
        this.leagueIds = leagueIds;
    }

    public void addLeagueID(long leagueID) {
        if (leagueIds == null) {
            leagueIds = new HashSet<>();
        }
        leagueIds.add(leagueID);
    }

    @Override
    protected LeagueFilters clone() throws CloneNotSupportedException {
        try {
            LeagueFilters clone = (LeagueFilters) super.clone();
            if (leagueIds != null) {
                clone.setLeagueIds(new HashSet<>(leagueIds));
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
        if (!(o instanceof LeagueFilters)) return false;
        if (!super.equals(o)) return false;

        LeagueFilters that = (LeagueFilters) o;

        return !(leagueIds != null ? !leagueIds.equals(that.leagueIds) : that.leagueIds != null);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (leagueIds != null ? leagueIds.hashCode() : 0);
        return result;
    }
}
