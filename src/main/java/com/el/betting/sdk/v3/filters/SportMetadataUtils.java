package com.el.betting.sdk.v3.filters;

import com.el.betting.common.ObjectReader;
import com.el.betting.common.ObjectWriter;
import com.el.betting.sdk.v3.metadata.CategoryMetadata;
import com.el.betting.sdk.v3.metadata.LeagueGroupMetadata;
import com.el.betting.sdk.v3.metadata.LeagueMetadata;
import com.el.betting.sdk.v3.metadata.TeamMetadata;

import java.util.*;
import java.util.stream.Collectors;

public class SportMetadataUtils {

    public static void mergeCategoryMetadata(CategoryMetadata categoryMetadata, CategoryMetadata mergeCategoryMetadata) {
        Map<String, Object> mergeLiteralFields = ObjectReader.readProperties(categoryMetadata, (object -> !(object instanceof Collection) && object != null));
        ObjectWriter.writeObjectFields(categoryMetadata, mergeLiteralFields);

        //now merge leagues
        Set<LeagueMetadata> mergeLeaguesMetadata = mergeCategoryMetadata.getLeaguesMetadata();
        if (mergeLeaguesMetadata != null) {
            Map<String, List<LeagueMetadata>> mergeLeagueNames = mergeLeaguesMetadata.stream()
                    .collect(Collectors.groupingBy(LeagueMetadata::getName));

            categoryMetadata.getLeaguesMetadata().forEach(leagueMetadata -> {
                if (mergeLeagueNames.containsKey(leagueMetadata.getName())) {
                    List<LeagueMetadata> leagueMetadataList = mergeLeagueNames.remove(leagueMetadata.getName());
                    mergeLeagues(leagueMetadata, leagueMetadataList.get(0));
                }
            });

            List<LeagueMetadata> mergeLeagues = mergeLeagueNames.values().stream().map(collection -> collection.get(0)).collect(Collectors.toList());
            categoryMetadata.getLeaguesMetadata().addAll(mergeLeagues);
        }
    }

    public static void mergeLeagues(LeagueMetadata baseLeagueMetadata, LeagueMetadata mergeLeagueMetadata) {
        Map<String, Object> mergeLiteralFields = ObjectReader.readProperties(mergeLeagueMetadata, (object -> !(object instanceof Collection) && object != null));
        ObjectWriter.writeObjectFields(baseLeagueMetadata, mergeLiteralFields);

        if (mergeLeagueMetadata.getAliases() != null) {
            if (baseLeagueMetadata.getAliases() == null) {
                baseLeagueMetadata.setAliases(new HashSet<>());
            }
            baseLeagueMetadata.getAliases().addAll(mergeLeagueMetadata.getAliases());
        }

        //now merge groups metadata
        Set<LeagueGroupMetadata> mergeGroupsMetadata = mergeLeagueMetadata.getGroupsMetadata();
        if (mergeGroupsMetadata != null) {
            Map<String, List<LeagueGroupMetadata>> mergeGroupNames = mergeGroupsMetadata.stream()
                    .filter(groupMetadata -> groupMetadata.getName() != null)
                    .collect(Collectors.groupingBy(LeagueGroupMetadata::getName));

            baseLeagueMetadata.getGroupsMetadata().forEach(groupMetadata -> {
                if (mergeGroupNames.containsKey(groupMetadata.getName())) {
                    List<LeagueGroupMetadata> groupMetadatas = mergeGroupNames.remove(groupMetadata.getName());
                    mergeLeagueGroupMetadata(groupMetadata, groupMetadatas.get(0));
                }
            });

            List<LeagueGroupMetadata> mergeGroups = mergeGroupNames.values().stream().map(collection -> collection.get(0)).collect(Collectors.toList());
            baseLeagueMetadata.getGroupsMetadata().addAll(mergeGroups);
        }

        if (baseLeagueMetadata.getTeamsMetadata() == null) {
            baseLeagueMetadata.setTeamsMetadata(mergeLeagueMetadata.getTeamsMetadata());
        }

        //now merge team metadata
        mergeTeamsMetadata(baseLeagueMetadata.getTeamsMetadata(), mergeLeagueMetadata.getTeamsMetadata());
    }

    public static void mergeLeagueGroupMetadata(LeagueGroupMetadata baseLeagueGroupMetadata, LeagueGroupMetadata mergeLeagueGroupMetadata) {
        Map<String, Object> mergeLiteralFields = ObjectReader.readProperties(mergeLeagueGroupMetadata,
                (object -> !(object instanceof Collection) && object != null),
                (object -> !(object instanceof LeagueMetadata)));
        ObjectWriter.writeObjectFields(baseLeagueGroupMetadata, mergeLiteralFields);

        if (mergeLeagueGroupMetadata.getAliases() != null) {
            if (baseLeagueGroupMetadata.getAliases() == null) {
                baseLeagueGroupMetadata.setAliases(new HashSet<>());
            }
            baseLeagueGroupMetadata.getAliases().addAll(mergeLeagueGroupMetadata.getAliases());
        }

        if (baseLeagueGroupMetadata.getTeamsMetadata() == null) {
            baseLeagueGroupMetadata.setTeamsMetadata(mergeLeagueGroupMetadata.getTeamsMetadata());
        }
        //now merge teams
        mergeTeamsMetadata(baseLeagueGroupMetadata.getTeamsMetadata(), mergeLeagueGroupMetadata.getTeamsMetadata());
    }

    public static void mergeTeamsMetadata(Set<TeamMetadata> baseTeamsMetadata, Set<TeamMetadata> mergeTeamsMetadata) {
        if (mergeTeamsMetadata != null) {
            Map<String, List<TeamMetadata>> mergeTeamNames = mergeTeamsMetadata.stream()
                    .collect(Collectors.groupingBy(TeamMetadata::getName));

            baseTeamsMetadata.forEach(teamMetadata -> {
                if (mergeTeamNames.containsKey(teamMetadata.getName())) {
                    List<TeamMetadata> mergeTeamMeta = mergeTeamNames.remove(teamMetadata.getName());
                    mergeTeamMetadata(teamMetadata, mergeTeamMeta.get(0));
                }
            });

            List<TeamMetadata> mergeTeamMetadatas = mergeTeamNames.values().stream().map(collection -> collection.get(0)).collect(Collectors.toList());
            baseTeamsMetadata.addAll(mergeTeamMetadatas);
        }
    }

    public static void mergeTeamMetadata(TeamMetadata baseTeamMetadata, TeamMetadata mergeTeamMetadata) {
        if (mergeTeamMetadata.getName() != null) {
            baseTeamMetadata.setName(mergeTeamMetadata.getName());
        }

        if (mergeTeamMetadata.getAliases() != null) {
            if (baseTeamMetadata.getAliases() == null) {
                baseTeamMetadata.setAliases(new HashSet<>());
            }
            baseTeamMetadata.getAliases().addAll(mergeTeamMetadata.getAliases());
        }
    }
}
