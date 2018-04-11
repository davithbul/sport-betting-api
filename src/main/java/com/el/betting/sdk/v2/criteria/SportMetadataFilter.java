package com.el.betting.sdk.v2.criteria;

import com.el.betting.sdk.v2.SportMetadata;
import com.el.betting.sdk.common.SportType;
import org.apache.commons.lang3.StringUtils;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

public class SportMetadataFilter {
    private SportType sportType;
    private Set<Predicate<SportMetadata.CategoryMetadata>> categoryPredicates = new HashSet<>();
    private Set<Predicate<SportMetadata.LeagueMetadata>> leagueMetadataPredicates = new HashSet<>();
    private Set<Predicate<SportMetadata.GroupMetadata>> groupMetadataPredicates = new HashSet<>();
    private Set<Predicate<SportMetadata.TeamMetadata>> teamMetadataPredicates = new HashSet<>();

    public SportType getSportType() {
        return sportType;
    }

    public void setSportType(SportType sportType) {
        this.sportType = sportType;
    }

    public Set<Predicate<SportMetadata.CategoryMetadata>> getCategoryPredicates() {
        return categoryPredicates;
    }

    public void setCategoryPredicates(Set<Predicate<SportMetadata.CategoryMetadata>> categoryPredicates) {
        this.categoryPredicates = categoryPredicates;
    }

    public void addCategoryPredicate(Predicate<SportMetadata.CategoryMetadata> categoryPredicate) {
        categoryPredicates.add(categoryPredicate);
    }

    public Set<Predicate<SportMetadata.LeagueMetadata>> getLeagueMetadataPredicates() {
        return leagueMetadataPredicates;
    }

    public void setLeagueMetadataPredicates(Set<Predicate<SportMetadata.LeagueMetadata>> leagueMetadataPredicates) {
        this.leagueMetadataPredicates = leagueMetadataPredicates;
    }

    public void addLeagueMetadataPredicate(Predicate<SportMetadata.LeagueMetadata> leagueMetadataPredicate) {
        leagueMetadataPredicates.add(leagueMetadataPredicate);
    }

    public Set<Predicate<SportMetadata.GroupMetadata>> getGroupMetadataPredicates() {
        return groupMetadataPredicates;
    }

    public void setGroupMetadataPredicates(Set<Predicate<SportMetadata.GroupMetadata>> groupMetadataPredicates) {
        this.groupMetadataPredicates = groupMetadataPredicates;
    }

    public void addGroupMetadataPredicate(Predicate<SportMetadata.GroupMetadata> groupMetadataPredicate) {
        this.groupMetadataPredicates.add(groupMetadataPredicate);
    }

    public Set<Predicate<SportMetadata.TeamMetadata>> getTeamMetadataPredicates() {
        return teamMetadataPredicates;
    }

    public void setTeamMetadataPredicates(Set<Predicate<SportMetadata.TeamMetadata>> teamMetadataPredicates) {
        this.teamMetadataPredicates = teamMetadataPredicates;
    }

    public void addTeamMetadataPredicate(Predicate<SportMetadata.TeamMetadata> teamMetadataPredicate) {
        this.teamMetadataPredicates.add(teamMetadataPredicate);
    }

    public void addCategoryName(String categoryName) {
        categoryPredicates.add(categoryMetadata -> StringUtils.equals(categoryMetadata.getName(),categoryName));
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
