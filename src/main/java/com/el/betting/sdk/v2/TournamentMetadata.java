package com.el.betting.sdk.v2;

import java.util.List;

public interface TournamentMetadata {

    boolean containsTeamName(String name);

    List<SportMetadata.TeamMetadata> getTeamsMetadata();

    SportMetadata.TeamMetadata getTeamMetadata(String name);

    void addTeamMetadata(SportMetadata.TeamMetadata teamMetadata);
}
