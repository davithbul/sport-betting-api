package com.el.betting.sdk.v3.criteria;

import com.el.betting.sdk.common.SportType;
import com.el.betting.sdk.v3.metadata.CategoryMetadata;
import com.el.betting.sdk.v3.metadata.LeagueGroupMetadata;
import com.el.betting.sdk.v3.metadata.LeagueMetadata;
import com.el.betting.sdk.v3.metadata.TeamMetadata;
import org.apache.commons.lang3.StringUtils;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

public class SportMetadataFilter {
    private SportType sportType;
    private Set<Predicate<CategoryMetadata>> categoryPredicates = new HashSet<>();
    private Set<Predicate<LeagueMetadata>> leagueMetadataPredicates = new HashSet<>();
    private Set<Predicate<LeagueGroupMetadata>> groupMetadataPredicates = new HashSet<>();
    private Set<Predicate<TeamMetadata>> teamMetadataPredicates = new HashSet<>();

    public SportType getSportType() {
        return sportType;
    }

    public void setSportType(SportType sportType) {
        this.sportType = sportType;
    }

    public Set<Predicate<CategoryMetadata>> getCategoryPredicates() {
        return categoryPredicates;
    }

    public void setCategoryPredicates(Set<Predicate<CategoryMetadata>> categoryPredicates) {
        this.categoryPredicates = categoryPredicates;
    }

    public void addCategoryPredicate(Predicate<CategoryMetadata> categoryPredicate) {
        categoryPredicates.add(categoryPredicate);
    }

    public Set<Predicate<LeagueMetadata>> getLeagueMetadataPredicates() {
        return leagueMetadataPredicates;
    }

    public void setLeagueMetadataPredicates(Set<Predicate<LeagueMetadata>> leagueMetadataPredicates) {
        this.leagueMetadataPredicates = leagueMetadataPredicates;
    }

    public void addLeagueMetadataPredicate(Predicate<LeagueMetadata> leagueMetadataPredicate) {
        leagueMetadataPredicates.add(leagueMetadataPredicate);
    }

    public Set<Predicate<LeagueGroupMetadata>> getGroupMetadataPredicates() {
        return groupMetadataPredicates;
    }

    public void setGroupMetadataPredicates(Set<Predicate<LeagueGroupMetadata>> groupMetadataPredicates) {
        this.groupMetadataPredicates = groupMetadataPredicates;
    }

    public void addGroupMetadataPredicate(Predicate<LeagueGroupMetadata> groupMetadataPredicate) {
        this.groupMetadataPredicates.add(groupMetadataPredicate);
    }

    public Set<Predicate<TeamMetadata>> getTeamMetadataPredicates() {
        return teamMetadataPredicates;
    }

    public void setTeamMetadataPredicates(Set<Predicate<TeamMetadata>> teamMetadataPredicates) {
        this.teamMetadataPredicates = teamMetadataPredicates;
    }

    public void addTeamMetadataPredicate(Predicate<TeamMetadata> teamMetadataPredicate) {
        this.teamMetadataPredicates.add(teamMetadataPredicate);
    }

    public void addCategoryName(String categoryName) {
        categoryPredicates.add(categoryMetadata -> StringUtils.equals(categoryMetadata.getName(), categoryName));
    }

    public void addLeagueName(String leagueName) {
        leagueMetadataPredicates.add((leagueMetadata) -> leagueMetadata.getName().equals(leagueName));
    }

    public void addTeamName(String teamName) {
        teamMetadataPredicates.add((teamMetadata) -> teamMetadata.getName().equals(teamName));
    }

    public void addGroupName(String groupName) {
        groupMetadataPredicates.add((groupMetadata -> groupMetadata.getName().equals(groupName)));
    }

    public void addLeagueAlias(String aliasName) {
        leagueMetadataPredicates.add((leagueMetadata -> leagueMetadata.getAliases().contains(aliasName)));
    }

    public void addTeamAlias(String aliasName) {
        teamMetadataPredicates.add(teamMetadata -> teamMetadata.getAliases().contains(aliasName));
    }
}
