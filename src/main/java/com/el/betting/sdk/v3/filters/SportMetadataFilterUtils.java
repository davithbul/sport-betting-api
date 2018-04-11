package com.el.betting.sdk.v3.filters;

import com.el.betting.sdk.v3.criteria.SportMetadataFilter;
import com.el.betting.sdk.v3.metadata.*;
import org.springframework.util.CollectionUtils;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SportMetadataFilterUtils {

    public static SportMetadata getSportMetadata(SportMetadata sportMetadata, SportMetadataFilter sportMetadataFilter) {
        Stream<CategoryMetadata> categoryMetadataStream = filterCategoryMetadata(sportMetadata.getCategories().stream(), sportMetadataFilter)
                .map(categoryMetadata -> {
                    //filter by leagues
                    CategoryMetadata categoryMetadataClone = categoryMetadata.clone();
                    Stream<LeagueMetadata> leagueMetadataStream = filterLeagueMetadata(categoryMetadata.getLeaguesMetadata().stream(), sportMetadataFilter);

                    //filter by teams
                    leagueMetadataStream = leagueMetadataStream.map(leagueMetadata -> {
                        LeagueMetadata leagueMetadataClone = leagueMetadata.clone();
                        Stream<TeamMetadata> teamMetadataStream = filterTeamMetadata(leagueMetadataClone.getTeamsMetadata().stream(), sportMetadataFilter);
                        leagueMetadataClone.setTeamsMetadata(teamMetadataStream.collect(Collectors.toSet()));
                        return leagueMetadataClone;
                    });

                    if (hasTeamFilter(sportMetadataFilter)) {
                        leagueMetadataStream = leagueMetadataStream.filter(leagueMetadata -> !leagueMetadata.getTeamsMetadata().isEmpty());
                    }

                    //filter by groups
                    leagueMetadataStream = leagueMetadataStream.map(leagueMetadata -> {
                        Stream<LeagueGroupMetadata> groupMetadataStream = filterGroupMetadata(leagueMetadata.getGroupsMetadata().stream(), sportMetadataFilter);

                        //filter group's team metadata
                        groupMetadataStream = groupMetadataStream.map(groupMetadata -> {
                            LeagueGroupMetadata groupMetadataClone = groupMetadata.clone();
                            Stream<TeamMetadata> teamMetadataStream = filterTeamMetadata(groupMetadataClone.getTeamsMetadata().stream(), sportMetadataFilter);
                            groupMetadataClone.setTeamsMetadata(teamMetadataStream.collect(Collectors.toSet()));
                            return groupMetadataClone;
                        });

                        if (hasTeamFilter(sportMetadataFilter)) {
                            groupMetadataStream = groupMetadataStream.filter(groupMetadata -> !groupMetadata.getTeamsMetadata().isEmpty());
                        }

                        leagueMetadata.setGroupsMetadata(groupMetadataStream.collect(Collectors.toSet()));
                        return leagueMetadata;
                    });

                    categoryMetadataClone.setLeaguesMetadata(leagueMetadataStream.collect(Collectors.toSet()));
                    return categoryMetadataClone;
                });

        if (hasLeagueFilter(sportMetadataFilter)) {
            categoryMetadataStream = categoryMetadataStream.filter(categoryMetadata -> !categoryMetadata.getLeaguesMetadata().isEmpty());
        }

        sportMetadata.setCategories(categoryMetadataStream.collect(Collectors.toSet()));

        return sportMetadata;
    }

    private static Stream<CategoryMetadata> filterCategoryMetadata(Stream<CategoryMetadata> categoryMetadataStream, SportMetadataFilter sportMetadataFilter) {
        if (CollectionUtils.isEmpty(sportMetadataFilter.getCategoryPredicates())) {
            return categoryMetadataStream;
        }

        return categoryMetadataStream
                .filter(categoryMetadata -> sportMetadataFilter.getCategoryPredicates().stream().anyMatch(predicate -> predicate.test(categoryMetadata)));
    }

    private static Stream<LeagueMetadata> filterLeagueMetadata(Stream<LeagueMetadata> leagueMetadataStream, SportMetadataFilter sportMetadataFilter) {
        if (CollectionUtils.isEmpty(sportMetadataFilter.getLeagueMetadataPredicates())) {
            return leagueMetadataStream;
        }

        return leagueMetadataStream
                .filter(leagueMetadata -> sportMetadataFilter.getLeagueMetadataPredicates().stream().anyMatch(predicate -> predicate.test(leagueMetadata)));
    }

    private static Stream<LeagueGroupMetadata> filterGroupMetadata(Stream<LeagueGroupMetadata> groupMetadataStream, SportMetadataFilter sportMetadataFilter) {
        if (CollectionUtils.isEmpty(sportMetadataFilter.getGroupMetadataPredicates())) {
            return groupMetadataStream;
        }

        return groupMetadataStream
                .filter(groupMetadata -> sportMetadataFilter.getGroupMetadataPredicates().stream().anyMatch(predicate -> predicate.test(groupMetadata)));
    }

    private static Stream<TeamMetadata> filterTeamMetadata(Stream<TeamMetadata> teamMetadataStream, SportMetadataFilter sportMetadataFilter) {
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
