package com.el.betting.sdk.v2.builders;

import com.el.betting.common.Wrapper;
import com.el.betting.sdk.v2.SportMetadata;
import com.el.betting.sdk.common.SportType;

import java.util.*;
import java.util.stream.Collectors;

public class SportMetadataBuilder {

    private Map<SportType, SportMetadata> sportCategoryMap = new LinkedHashMap<>();

    public void addLeague(SportType sportType, String leagueName) {
        add(sportType, "UNKNOWN", leagueName, null);
    }

    public void add(SportType sportType, SportMetadata.CategoryMetadata categoryMetadata) {
        SportMetadata sportMetadata = sportCategoryMap.get(sportType);
        if (sportMetadata == null) {
            sportMetadata = new SportMetadata(sportType);
            sportMetadata.addCategory(categoryMetadata);
            sportCategoryMap.put(sportType, sportMetadata);
        } else {
            sportMetadata.addCategory(categoryMetadata);
        }
    }

    public void add(SportType sportType, SportMetadata.CategoryMetadata categoryMetadata, String tournamentName, String leagueType) {
        add(sportType, categoryMetadata, tournamentName, leagueType, null);
    }

    public void add(SportType sportType, SportMetadata.CategoryMetadata categoryMetadata, String tournamentName, String leagueType, List<SportMetadata.TeamMetadata> teams) {
        SportMetadata sportMetadata = sportCategoryMap.get(sportType);
        if (sportMetadata == null) {
            sportMetadata = new SportMetadata(sportType);
            sportCategoryMap.put(sportType, sportMetadata);
        }

        Optional<SportMetadata.CategoryMetadata> sportCategoryOptional = sportMetadata.getCategories()
                .stream()
                .filter(sportCategory2 -> sportCategory2.getName().equals(categoryMetadata.getName()))
                .findAny();

        SportMetadata.CategoryMetadata newCategoryMetadata;
        if (sportCategoryOptional.isPresent()) {
            newCategoryMetadata = sportCategoryOptional.get();
        } else {
            newCategoryMetadata = categoryMetadata;
            sportMetadata.addCategory(newCategoryMetadata);
        }

        List<SportMetadata.LeagueMetadata> leaguesMetadata = newCategoryMetadata.getLeaguesMetadata();
        Optional<SportMetadata.LeagueMetadata> leagueOptional = leaguesMetadata.stream()
                .filter(league -> league.getName().equals(tournamentName))
                .findAny();
        if (!leagueOptional.isPresent()) {
            SportMetadata.LeagueMetadata leagueMetadata = new SportMetadata.LeagueMetadata(tournamentName, leagueType);
            leagueMetadata.setTeamsMetadata(teams);
            newCategoryMetadata.addLeague(leagueMetadata);
        } else {
            SportMetadata.LeagueMetadata leagueMetadata = leagueOptional.get();
            if (leagueMetadata.getTeamsMetadata() != null) {
                if (teams != null) {
                    leagueMetadata.getTeamsMetadata().addAll(teams);
                    List<SportMetadata.TeamMetadata> mergedTeams = leagueMetadata.getTeamsMetadata().stream()
                            .map(teamMetadata -> new Wrapper<>(teamMetadata.getName(), teamMetadata))
                            .distinct()
                            .map(Wrapper::unwrap)
                            .collect(Collectors.toList());
                    leagueMetadata.setTeamsMetadata(mergedTeams);
                }
            } else {
                leagueMetadata.setTeamsMetadata(teams);
            }
        }
    }

    public void add(SportType sportType, String categoryName, String tournamentName, String leagueType) {
        add(sportType, categoryName, tournamentName, leagueType, null);
    }

    public void add(SportType sportType, String categoryName, String leagueName, String leagueType, List<SportMetadata.TeamMetadata> teams) {
        String tournamentName;
        String category;
        Optional<String> groupName = Optional.empty();
        if (leagueName.contains("::")) {
            groupName = Optional.of(leagueName.substring(leagueName.indexOf("::") + 2).trim());
            tournamentName = leagueName.substring(0, leagueName.indexOf("::")).trim();
            category = categoryName;
        } else if (leagueName.toUpperCase().startsWith("GROUP") || isQualification(leagueName)) {
            groupName = Optional.of(leagueName);
            tournamentName = categoryName;
            category = categoryName;
        } else {
            tournamentName = leagueName;
            category = categoryName;
        }

        SportMetadata sportMetadata = sportCategoryMap.get(sportType);
        if (sportMetadata == null) {
            sportMetadata = new SportMetadata(sportType);
            sportCategoryMap.put(sportType, sportMetadata);
        }

        Optional<SportMetadata.CategoryMetadata> sportCategoryOptional = sportMetadata.getCategories()
                .stream()
                .filter(sportCategory2 -> sportCategory2.getName().equals(category))
                .findAny();

        SportMetadata.CategoryMetadata categoryMetadata;
        if (sportCategoryOptional.isPresent()) {
            categoryMetadata = sportCategoryOptional.get();
        } else {
            categoryMetadata = new SportMetadata.CategoryMetadata(category);
            sportMetadata.addCategory(categoryMetadata);
        }

        List<SportMetadata.LeagueMetadata> leaguesMetadata = categoryMetadata.getLeaguesMetadata();
        Optional<SportMetadata.LeagueMetadata> leagueOptional = leaguesMetadata.stream()
                .filter(league -> league.getName().equals(tournamentName))
                .findAny();

        if (!leagueOptional.isPresent()) {
            SportMetadata.LeagueMetadata leagueMetadata = new SportMetadata.LeagueMetadata(tournamentName, leagueType);
            categoryMetadata.addLeague(leagueMetadata);
            if (groupName.isPresent()) {
                SportMetadata.GroupMetadata groupMetadata = new SportMetadata.GroupMetadata();
                groupMetadata.setName(groupName.get());
                groupMetadata.setTeamsMetadata(teams);
                leagueMetadata.addGroupMetadata(groupMetadata);
            } else {
                leagueMetadata.setTeamsMetadata(teams);
            }
        } else {
            SportMetadata.LeagueMetadata leagueMetadata = leagueOptional.get();
            if (groupName.isPresent()) {
                SportMetadata.GroupMetadata groupMetadata = new SportMetadata.GroupMetadata();
                groupMetadata.setName(groupName.get());
                groupMetadata.setTeamsMetadata(teams);
                leagueMetadata.addGroupMetadata(groupMetadata);
            }

            leagueMetadata.addTeamsMetadata(teams);
        }
    }


    public List<SportMetadata> createSportsMetadata() {
        return new ArrayList<>(sportCategoryMap.values());
    }

    private static List<String> qualifications = Arrays.asList("ROUND", "FINAL", "QUALIFICATION");
    private boolean isQualification(String leagueName) {
        String name = leagueName.toUpperCase();
        return qualifications.stream().anyMatch(name::contains);
    }


}