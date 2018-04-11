package com.el.betting.sdk.v2;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

public class CompetitionMetadata {

    private SportMetadata.CategoryMetadata categoryMetadata;

    private SportMetadata.LeagueMetadata leagueMetadata;

    private Optional<SportMetadata.GroupMetadata> groupMetadataOptional = Optional.empty();

    public CompetitionMetadata() {
        categoryMetadata = new SportMetadata.CategoryMetadata(null);
        leagueMetadata = new SportMetadata.LeagueMetadata(null);
    }

    public static CompetitionMetadata init() {
        CompetitionMetadata competitionMetadata = new CompetitionMetadata();
        competitionMetadata.setCategoryMetadata(new SportMetadata.CategoryMetadata(null));
        competitionMetadata.setLeagueMetadata(new SportMetadata.LeagueMetadata(null));
        return competitionMetadata;
    }

    public SportMetadata.CategoryMetadata getCategoryMetadata() {
        return categoryMetadata;
    }

    public void setCategoryMetadata(SportMetadata.CategoryMetadata categoryMetadata) {
        this.categoryMetadata = categoryMetadata;
        if(leagueMetadata != null) {
            this.categoryMetadata.setLeaguesMetadata(Arrays.asList(leagueMetadata));
        }
    }

    public SportMetadata.LeagueMetadata getLeagueMetadata() {
        return leagueMetadata;
    }

    public void setLeagueMetadata(SportMetadata.LeagueMetadata leagueMetadata) {
        this.leagueMetadata = leagueMetadata;
        if(categoryMetadata != null) {
            this.categoryMetadata.setLeaguesMetadata(Arrays.asList(leagueMetadata));
        }
    }

    public Optional<SportMetadata.GroupMetadata> getGroupMetadataOptional() {
        return groupMetadataOptional;
    }

    public void setGroupMetadataOptional(Optional<SportMetadata.GroupMetadata> groupMetadataOptional) {
        this.groupMetadataOptional = groupMetadataOptional;
        if(groupMetadataOptional.isPresent()) {
            this.leagueMetadata.setGroupsMetadata(Arrays.asList(groupMetadataOptional.get()));
        } else {
            this.leagueMetadata.setGroupsMetadata(Collections.emptyList());
        }
    }
}
