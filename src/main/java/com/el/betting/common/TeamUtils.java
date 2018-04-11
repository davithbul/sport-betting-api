package com.el.betting.common;

import com.el.betting.sdk.v2.Team;
import com.el.betting.sdk.v4.Participant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.repository.query.parser.Part;

import java.util.ArrayList;
import java.util.List;

public class TeamUtils {
    public static boolean isMatching(List<Team> teamList1, List<Team> teamList2) {
        if(teamList1.size() != teamList2.size()) {
            return false;
        }

        for (Team team1 : teamList1) {
            if(!teamList2.contains(team1)) {
                return false;
            }
        }

        return true;
    }

    public static boolean containsTeam(List<Team> teamList, String teamName) {
        return teamList.stream().anyMatch(team -> StringUtils.equals(team.getName(), teamName));
    }

    public static List<Team> getTeams(String name, String separator) {
        String[] teamNames = StringUtils.splitByWholeSeparator(name, separator);
        List<Team> teams = new ArrayList<>();
        teams.add(new Team(teamNames[0].trim(), Team.Side.HOME));
        for (int i = 1; i < teamNames.length; i++) {
            teams.add(new Team(teamNames[i].trim(), Team.Side.AWAY));
        }
        return teams;
    }

    public static Team getHomeTeam(List<Team> teams) {
        return teams.stream().filter(team -> team.getSide() == Team.Side.HOME).findAny().get();
    }

    public static Team getAwayTeam(List<Team> teams) {
        return teams.stream().filter(team -> team.getSide() == Team.Side.AWAY).findAny().get();
    }

    public static String printTeamNames(List<Team> teams) {
        Team homeTeam = teams.stream().filter(team -> team.getSide() == Team.Side.HOME).findAny().get();
        Team awayTeam = teams.stream().filter(team -> team.getSide() == Team.Side.AWAY).findAny().get();
        return homeTeam.getName() + " vs " + awayTeam.getName();
    }
}
