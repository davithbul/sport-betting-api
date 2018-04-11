package com.el.betting.sdk.v2.common;

import com.el.betting.sdk.v2.SportMetadata;
import com.el.betting.sdk.v2.criteria.SportMetadataFilter;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SportMetadataFilterUtils {

    public static SportMetadata getSportMetadata(SportMetadata sportMetadata, SportMetadataFilter sportMetadataFilter) {
        Stream<SportMetadata.CategoryMetadata> categoryMetadataStream = filterCategoryMetadata(sportMetadata.getCategories().stream(), sportMetadataFilter)
                .map(categoryMetadata -> {
                    SportMetadata.CategoryMetadata categoryMetadataClone = categoryMetadata.clone();
                    Stream<SportMetadata.LeagueMetadata> leagueMetadataStream = filterLeagueMetadata(categoryMetadata.getLeaguesMetadata().stream(), sportMetadataFilter);

                    //filter by teams
                    leagueMetadataStream = leagueMetadataStream.map(leagueMetadata -> {
                        SportMetadata.LeagueMetadata leagueMetadataClone = leagueMetadata.clone();
                        if(leagueMetadataClone.getTeamsMetadata() == null) {
                            leagueMetadataClone.setTeamsMetadata(new ArrayList<>());
                        }
                        Stream<SportMetadata.TeamMetadata> teamMetadataStream = filterTeamMetadata(leagueMetadataClone.getTeamsMetadata().stream(), sportMetadataFilter);
                        leagueMetadataClone.setTeamsMetadata(teamMetadataStream.collect(Collectors.toList()));
                        return leagueMetadataClone;
                    });

                    if (hasTeamFilter(sportMetadataFilter)) {
                        leagueMetadataStream = leagueMetadataStream.filter(leagueMetadata -> !leagueMetadata.getTeamsMetadata().isEmpty());
                    }

                    //filter by groups
                    leagueMetadataStream = leagueMetadataStream.map(leagueMetadata -> {
                        if(leagueMetadata.getGroupsMetadata() == null) {
                            leagueMetadata.setGroupsMetadata(new ArrayList<>());
                        }

                        Stream<SportMetadata.GroupMetadata> groupMetadataStream = filterGroupMetadata(leagueMetadata.getGroupsMetadata().stream(), sportMetadataFilter);

                        //filter group's team metadata
                        groupMetadataStream = groupMetadataStream.map(groupMetadata -> {
                            SportMetadata.GroupMetadata groupMetadataClone = groupMetadata.clone();
                            if(groupMetadataClone.getTeamsMetadata() == null) {
                                groupMetadataClone.setTeamsMetadata(new ArrayList<>());
                            }
                            Stream<SportMetadata.TeamMetadata> teamMetadataStream = filterTeamMetadata(groupMetadataClone.getTeamsMetadata().stream(), sportMetadataFilter);
                            groupMetadataClone.setTeamsMetadata(teamMetadataStream.collect(Collectors.toList()));
                            return groupMetadataClone;
                        });

                        if(hasTeamFilter(sportMetadataFilter)) {
                            groupMetadataStream = groupMetadataStream.filter(groupMetadata -> !groupMetadata.getTeamsMetadata().isEmpty());
                        }

                        leagueMetadata.setGroupsMetadata(groupMetadataStream.collect(Collectors.toList()));
                        return leagueMetadata;
                    });

                    categoryMetadataClone.setLeaguesMetadata(leagueMetadataStream.collect(Collectors.toList()));
                    return categoryMetadataClone;
                });

        if(hasLeagueFilter(sportMetadataFilter)) {
            categoryMetadataStream = categoryMetadataStream.filter(categoryMetadata -> !categoryMetadata.getLeaguesMetadata().isEmpty());
        }

        sportMetadata.setCategories(categoryMetadataStream.collect(Collectors.toList()));

        return sportMetadata;
    }

    private static Stream<SportMetadata.CategoryMetadata> filterCategoryMetadata(Stream<SportMetadata.CategoryMetadata> categoryMetadataStream, SportMetadataFilter sportMetadataFilter) {
        if (CollectionUtils.isEmpty(sportMetadataFilter.getCategoryPredicates())) {
            return categoryMetadataStream;
        }

        return categoryMetadataStream
                .filter(categoryMetadata -> sportMetadataFilter.getCategoryPredicates().stream().anyMatch(predicate -> predicate.test(categoryMetadata)));
    }

    private static Stream<SportMetadata.LeagueMetadata> filterLeagueMetadata(Stream<SportMetadata.LeagueMetadata> leagueMetadataStream, SportMetadataFilter sportMetadataFilter) {
        if (CollectionUtils.isEmpty(sportMetadataFilter.getLeagueMetadataPredicates())) {
            return leagueMetadataStream;
        }

        return leagueMetadataStream
                .filter(leagueMetadata -> sportMetadataFilter.getLeagueMetadataPredicates().stream().anyMatch(predicate -> predicate.test(leagueMetadata)));
    }

    private static Stream<SportMetadata.GroupMetadata> filterGroupMetadata(Stream<SportMetadata.GroupMetadata> groupMetadataStream, SportMetadataFilter sportMetadataFilter) {
        if (CollectionUtils.isEmpty(sportMetadataFilter.getGroupMetadataPredicates())) {
            return groupMetadataStream;
        }

        return groupMetadataStream
                .filter(groupMetadata -> sportMetadataFilter.getGroupMetadataPredicates().stream().anyMatch(predicate -> predicate.test(groupMetadata)));
    }

    private static Stream<SportMetadata.TeamMetadata> filterTeamMetadata(Stream<SportMetadata.TeamMetadata> teamMetadataStream, SportMetadataFilter sportMetadataFilter) {
        if (CollectionUtils.isEmpty(sportMetadataFilter.getTeamMetadataPredicates())) {
            return teamMetadataStream;
        }

        return teamMetadataStream
                .filter(teamMetadata -> sportMetadataFilter.getTeamMetadataPredicates().stream().anyMatch(predicate -> predicate.test(teamMetadata)));
    }

    public static boolean hasGroupFilter(SportMetadataFilter sportMetadataFilter) {
        return !sportMetadataFilter.getGroupMetadataPredicates().isEmpty() || hasTeamFilter(sportMetadataFilter);
    }

    public static boolean hasTeamFilter(SportMetadataFilter sportMetadataFilter) {
        return !sportMetadataFilter.getTeamMetadataPredicates().isEmpty();
    }

    public static boolean hasLeagueFilter(SportMetadataFilter sportMetadataFilter) {
        return !sportMetadataFilter.getLeagueMetadataPredicates().isEmpty() || hasTeamFilter(sportMetadataFilter);
    }

    public static boolean hasCategoryFilter(SportMetadataFilter sportMetadataFilter) {
        return !sportMetadataFilter.getCategoryPredicates().isEmpty() || hasLeagueFilter(sportMetadataFilter) || hasGroupFilter(sportMetadataFilter);
    }

}
