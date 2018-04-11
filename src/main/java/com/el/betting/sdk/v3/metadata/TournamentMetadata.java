package com.el.betting.sdk.v3.metadata;

import java.util.Set;

public interface TournamentMetadata {

    Set<TeamMetadata> getTeamsMetadata();

    TeamMetadata getTeamMetadata(String name);

    void addTeamMetadata(TeamMetadata teamMetadata);

    boolean containsTeam(String name);
}
