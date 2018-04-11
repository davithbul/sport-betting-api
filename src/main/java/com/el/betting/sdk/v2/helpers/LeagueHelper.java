package com.el.betting.sdk.v2.helpers;

import com.el.betting.sdk.v2.League;
import org.apache.commons.lang3.StringUtils;

public class LeagueHelper {
    private League league;
    private boolean refineExisting;

    public LeagueHelper(League league) {
        this(league, true);
    }

    public LeagueHelper(League league, boolean refineExisting) {
        this.league = league;
        this.refineExisting = refineExisting;
    }

    public boolean isNamePresent() {
        return StringUtils.isNotBlank(league.getSetName());
    }

    public void setName(String name) {
        if(refineExisting || league.getSetName() == null) {
            league.setName(name);
        }
    }

    public void setName(String leagueName, String groupName) {
        if(refineExisting || league.getSetName() == null) {
            league.setName(leagueName + " " + groupName);
        }
    }

    public void setCategory(String category) {
        if(refineExisting || league.getSetName() == null) {
            league.setCategory(category);
        }
    }
}
