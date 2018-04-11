package com.el.betting.sdk.v2.helpers;

import com.el.betting.sdk.v2.Team;

public class TeamHelper {

    private Team team;

    private boolean refineExisting;

    public TeamHelper(Team team) {
        this(team, true);
    }

    public TeamHelper(Team team, boolean refineExisting) {
        this.team = team;
        this.refineExisting = refineExisting;
    }

    public void setName(String name) {
        if(refineExisting || team.getSetName() == null) {
            team.setName(name);
        }
    }
}
