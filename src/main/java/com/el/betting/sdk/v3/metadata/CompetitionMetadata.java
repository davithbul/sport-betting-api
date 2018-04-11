package com.el.betting.sdk.v3.metadata;


import com.google.common.collect.Sets;

public class CompetitionMetadata {

    private CategoryMetadata categoryMetadata;
    private LeagueMetadata leagueMetadata;

    public CompetitionMetadata() {
        categoryMetadata = new CategoryMetadata(null);
        leagueMetadata = new LeagueMetadata(null);
        categoryMetadata.addLeagueMetadata(new LeagueMetadata(null));
    }

    public CategoryMetadata getCategoryMetadata() {
        return categoryMetadata;
    }

    public void setCategoryMetadata(CategoryMetadata categoryMetadata) {
        this.categoryMetadata = categoryMetadata;
        if (leagueMetadata != null) {
            this.categoryMetadata.setLeaguesMetadata(Sets.newHashSet(leagueMetadata));
        }
    }

    public LeagueMetadata getLeagueMetadata() {
        return leagueMetadata;
    }

    public void setLeagueMetadata(LeagueMetadata leagueMetadata) {
        this.leagueMetadata = leagueMetadata;
        if (categoryMetadata != null) {
            this.categoryMetadata.setLeaguesMetadata(Sets.newHashSet(leagueMetadata));
        }
    }
}
