package com.el.betting.sdk.v2.common;

import com.el.betting.common.ObjectReader;
import com.el.betting.common.ObjectWriter;
import com.el.betting.sdk.v2.SportMetadata;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SportMetadataUtils {

    public static void mergeCategoryMetadata(SportMetadata.CategoryMetadata categoryMetadata, SportMetadata.CategoryMetadata mergeCategoryMetadata) {
        Map<String, Object> mergeLiteralFields = ObjectReader.readProperties(categoryMetadata, (object -> !(object instanceof Collection) && object != null));
        ObjectWriter.writeObjectFields(categoryMetadata, mergeLiteralFields);

        //now merge leagues
        List<SportMetadata.LeagueMetadata> mergeLeaguesMetadata = mergeCategoryMetadata.getLeaguesMetadata();
        if(mergeLeaguesMetadata != null) {
            Map<String, List<SportMetadata.LeagueMetadata>> mergeLeagueNames = mergeLeaguesMetadata.stream()
                    .collect(Collectors.groupingBy(SportMetadata.LeagueMetadata::getName));

            categoryMetadata.getLeaguesMetadata().forEach(leagueMetadata -> {
                if (mergeLeagueNames.containsKey(leagueMetadata.getName())) {
                    List<SportMetadata.LeagueMetadata> leagueMetadatas = mergeLeagueNames.remove(leagueMetadata.getName());
                    mergeLeagues(leagueMetadata, leagueMetadatas.get(0));
                }
            });

            List<SportMetadata.LeagueMetadata> mergeLeagues = mergeLeagueNames.values().stream().map(collection -> collection.get(0)).collect(Collectors.toList());
            categoryMetadata.getLeaguesMetadata().addAll(mergeLeagues);
        }
    }

    public static void mergeLeagues(SportMetadata.LeagueMetadata baseLeagueMetadata, SportMetadata.LeagueMetadata mergeLeagueMetadata) {
        Map<String, Object> mergeLiteralFields = ObjectReader.readProperties(mergeLeagueMetadata, (object -> !(object instanceof Collection) && object != null));
        ObjectWriter.writeObjectFields(baseLeagueMetadata, mergeLiteralFields);

        if(mergeLeagueMetadata.getAliases() != null) {
            if(baseLeagueMetadata.getAliases() == null) {
                baseLeagueMetadata.setAliases(new HashSet<>());
            }
            baseLeagueMetadata.getAliases().addAll(mergeLeagueMetadata.getAliases());
        }

        //now merge groups metadata
        List<SportMetadata.GroupMetadata> mergeGroupsMetadata = mergeLeagueMetadata.getGroupsMetadata();
        if(mergeGroupsMetadata != null) {
            Map<String, List<SportMetadata.GroupMetadata>> mergeGroupNames = mergeGroupsMetadata.stream()
                    .filter(groupMetadata -> groupMetadata.getName() != null)
                    .collect(Collectors.groupingBy(SportMetadata.GroupMetadata::getName));

            baseLeagueMetadata.getGroupsMetadata().forEach(groupMetadata -> {
                if (mergeGroupNames.containsKey(groupMetadata.getName())) {
                    List<SportMetadata.GroupMetadata> groupMetadatas = mergeGroupNames.remove(groupMetadata.getName());
                    mergeGroupMetadata(groupMetadata, groupMetadatas.get(0));
                }
            });

            List<SportMetadata.GroupMetadata> mergeGroups = mergeGroupNames.values().stream().map(collection -> collection.get(0)).collect(Collectors.toList());
            baseLeagueMetadata.getGroupsMetadata().addAll(mergeGroups);
        }

        if(baseLeagueMetadata.getTeamsMetadata() == null) {
            baseLeagueMetadata.setTeamsMetadata(mergeLeagueMetadata.getTeamsMetadata());
        }

        //now merge team metadata
        mergeTeamsMetadata(baseLeagueMetadata.getTeamsMetadata(), mergeLeagueMetadata.getTeamsMetadata());
    }

    public static void mergeGroupMetadata(SportMetadata.GroupMetadata baseGroupMetadata, SportMetadata.GroupMetadata mergeGroupMetadata) {
        Map<String, Object> mergeLiteralFields = ObjectReader.readProperties(mergeGroupMetadata,
                (object -> !(object instanceof Collection) && object != null),
                (object -> !(object instanceof SportMetadata.LeagueMetadata)));
        ObjectWriter.writeObjectFields(baseGroupMetadata, mergeLiteralFields);

        if(mergeGroupMetadata.getAliases() != null) {
            if(baseGroupMetadata.getAliases() == null) {
                baseGroupMetadata.setAliases(new HashSet<>());
            }
            baseGroupMetadata.getAliases().addAll(mergeGroupMetadata.getAliases());
        }

        if(baseGroupMetadata.getTeamsMetadata() == null) {
            baseGroupMetadata.setTeamsMetadata(mergeGroupMetadata.getTeamsMetadata());
        }
        //now merge teams
        mergeTeamsMetadata(baseGroupMetadata.getTeamsMetadata(), mergeGroupMetadata.getTeamsMetadata());
    }

    public static void mergeTeamsMetadata(List<SportMetadata.TeamMetadata> baseTeamsMetadata, List<SportMetadata.TeamMetadata> mergeTeamsMetadata) {
        if(mergeTeamsMetadata != null) {
            Map<String, List<SportMetadata.TeamMetadata>> mergeTeamNames = mergeTeamsMetadata.stream()
                    .collect(Collectors.groupingBy(SportMetadata.TeamMetadata::getName));

            baseTeamsMetadata.forEach(teamMetadata -> {
                if (mergeTeamNames.containsKey(teamMetadata.getName())) {
                    List<SportMetadata.TeamMetadata> mergeTeamMeta = mergeTeamNames.remove(teamMetadata.getName());
                    mergeTeamMetadata(teamMetadata, mergeTeamMeta.get(0));
                }
            });

            List<SportMetadata.TeamMetadata> mergeTeamMetadatas = mergeTeamNames.values().stream().map(collection -> collection.get(0)).collect(Collectors.toList());
            baseTeamsMetadata.addAll(mergeTeamMetadatas);
        }
    }

    public static void mergeTeamMetadata(SportMetadata.TeamMetadata baseTeamMetadata, SportMetadata.TeamMetadata mergeTeamMetadata) {
        if(mergeTeamMetadata.getName() != null) {
            baseTeamMetadata.setName(mergeTeamMetadata.getName());
        }

        if(mergeTeamMetadata.getAliases() != null) {
            if(baseTeamMetadata.getAliases() == null) {
                baseTeamMetadata.setAliases(new HashSet<>());
            }
            baseTeamMetadata.getAliases().addAll(mergeTeamMetadata.getAliases());
        }
    }
}
