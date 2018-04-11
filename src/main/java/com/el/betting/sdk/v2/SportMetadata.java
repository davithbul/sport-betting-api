package com.el.betting.sdk.v2;

import com.el.betting.common.StringUtil;
import com.el.betting.sdk.common.SportType;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.*;

@Document
public class SportMetadata implements Cloneable {

    @Id
    private SportType sportType;

    private List<CategoryMetadata> categories;

    public SportMetadata(SportType sportType) {
        this.sportType = sportType;
        this.categories = new ArrayList<>();
    }

    public SportType getSportType() {
        return sportType;
    }

    public void setSportType(SportType sportType) {
        this.sportType = sportType;
    }

    public void addCategory(CategoryMetadata categoryMetadata) {
        if (categories == null) {
            categories = new ArrayList<>();
        }
        categories.add(categoryMetadata);
    }

    public void setCategories(List<CategoryMetadata> categories) {
        this.categories = categories;
    }

    public List<CategoryMetadata> getCategories() {
        return categories;
    }

    @Document
    public static class CategoryMetadata implements Cloneable {
        @Id
        private String name;
        private String continent;
        private String country;
        private String location;
        private String region;
        private Gender genderGroup;
        private String fixes;
        private String ageGroup;
        private LocalDateTime updateTime;

        private List<LeagueMetadata> leaguesMetadata = new ArrayList<>();

        public CategoryMetadata(String name) {
            this.name = name;
        }

        public void setLeaguesMetadata(List<LeagueMetadata> leaguesMetadata) {
            this.leaguesMetadata = leaguesMetadata;
        }

        public void addLeague(LeagueMetadata leagueMetadata) {
            leaguesMetadata.add(leagueMetadata);
        }

        public List<LeagueMetadata> getLeaguesMetadata() {
            return leaguesMetadata;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getFixes() {
            return fixes;
        }

        public void setFixes(String fixes) {
            this.fixes = fixes;
        }

        public String getAgeGroup() {
            return ageGroup;
        }

        public void setAgeGroup(String ageGroup) {
            this.ageGroup = ageGroup;
        }

        public Gender getGenderGroup() {
            return genderGroup;
        }

        public void setGenderGroup(Gender genderGroup) {
            this.genderGroup = genderGroup;
        }

        public String getContinent() {
            return continent;
        }

        public void setContinent(String continent) {
            this.continent = continent;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public LocalDateTime getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(LocalDateTime updateTime) {
            this.updateTime = updateTime;
        }

        public void updateTime() {
            this.updateTime = LocalDateTime.now();
        }

        public CategoryMetadata clone() {
            try {
                return (CategoryMetadata) super.clone();
            } catch (CloneNotSupportedException e) {
                // This should never happen
                throw new InternalError(e.toString());
            }
        }
    }

    public static class LeagueMetadata implements TournamentMetadata, Cloneable {
        private String name;
        private String code;
        private String abbreviation;
        private Set<String> aliases;
        private String type;
        private String length;
        private String memberCount;

        private List<GroupMetadata> groupsMetadata = new ArrayList<>();
        private List<TeamMetadata> teamsMetadata;

        public LeagueMetadata(String name) {
            this(name, null);
        }

        public LeagueMetadata(String name, String type) {
            this(name, type, new HashSet<>());
        }

        public LeagueMetadata(String name, String type, Set<String> aliases) {
            this(name, null, aliases, type, new ArrayList<>());
        }

        @PersistenceConstructor
        public LeagueMetadata(String name, String abbreviation, Set<String> aliases, String type, List<TeamMetadata> teamsMetadata) {
            this.name = name;
            this.abbreviation = abbreviation;
            this.aliases = aliases;
            this.type = type;
            this.teamsMetadata = teamsMetadata;
        }

        public String getName() {
            return name;
        }

        public String getType() {
            return type;
        }

        public String getAbbreviation() {
            return abbreviation;
        }

        public void setAbbreviation(String abbreviation) {
            this.abbreviation = abbreviation;
        }

        public Set<String> getAliases() {
            if(aliases == null) {
                aliases = new HashSet<>();
            }
            return aliases;
        }

        public void setAliases(Set<String> aliases) {
            this.aliases = aliases;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setType(String type) {
            this.type = type;
        }

        public List<TeamMetadata> getTeamsMetadata() {
            if(teamsMetadata == null) {
                teamsMetadata = new ArrayList<>();
            }
            return teamsMetadata;
        }

        public void setTeamsMetadata(List<TeamMetadata> teamsMetadata) {
            this.teamsMetadata = teamsMetadata;
        }

        public void addTeamMetadata(TeamMetadata teamMetadata) {
            this.teamsMetadata.add(teamMetadata);
        }

        public void addTeamsMetadata(List<TeamMetadata> teamsMetadata) {
            if (this.teamsMetadata == null) {
                this.teamsMetadata = new ArrayList<>();
            }
            this.teamsMetadata.addAll(teamsMetadata);
        }

        public void addGroupMetadata(GroupMetadata groupMetadata) {
            if(this.groupsMetadata == null) {
                this.groupsMetadata = new ArrayList<>();
            }
            this.groupsMetadata.add(groupMetadata);
            List<TeamMetadata> groupTeams = groupMetadata.getTeamsMetadata();
            if (groupTeams == null) {
                groupTeams = new ArrayList<>();
                groupMetadata.setTeamsMetadata(groupTeams);
            }

            groupTeams.forEach(this::addTeamMetadata);
        }

        public String getLength() {
            return length;
        }

        public void setLength(String length) {
            this.length = length;
        }

        public String getMemberCount() {
            return memberCount;
        }

        public void setMemberCount(String memberCount) {
            this.memberCount = memberCount;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public List<GroupMetadata> getGroupsMetadata() {
            return groupsMetadata;
        }

        public void setGroupsMetadata(List<GroupMetadata> groupsMetadata) {
            this.groupsMetadata = groupsMetadata;
        }

        public boolean containsLeague(String name) {
            if (this.name.equalsIgnoreCase(name)) {
                return true;
            }
            return aliases != null && aliases.contains(name);
        }

        public Optional<GroupMetadata> getGroupIfExist(String name) {
            if (getGroupsMetadata() == null || aliases == null) {
                return Optional.empty();
            }

            String[] strings = aliases.toArray(new String[aliases.size()]);
            name = StringUtil.removeWords(name, this.name);
            name = StringUtil.removeWords(name, strings);

            final String newName = name;
            Optional<GroupMetadata> groupMetadataOptional = getGroupsMetadata().stream()
                    .filter(groupMetadata ->
                            StringUtil.removeWords(newName, groupMetadata.getName()).trim().isEmpty()
                                     || (groupMetadata.getAliases() != null && StringUtil.removeWords(newName, groupMetadata.getAliases().toArray(new String[groupMetadata.getAliases().size()])).trim().isEmpty()))
                    .findAny();
            return groupMetadataOptional;
        }

        public boolean containsTeamName(String name) {
            if (teamsMetadata == null) {
                System.out.println("TEAM METADATA IS NULL");
                teamsMetadata = new ArrayList<>();
            }
            return teamsMetadata.stream()
                    .anyMatch(teamMetadata -> teamMetadata.containsTeam(name));
        }

        public TeamMetadata getTeamMetadata(String name) {
            return teamsMetadata.stream()
                    .filter(teamMetadata -> teamMetadata.containsTeam(name))
                    .findAny().get();
        }

        public void addAlias(String alias) {
            if (aliases == null) {
                aliases = new HashSet<>();
            }
            aliases.add(alias);
        }

        public LeagueMetadata clone() {
            try {
                return (LeagueMetadata) super.clone();
            } catch (CloneNotSupportedException e) {
                // This should never happen
                throw new InternalError(e.toString());
            }
        }
    }

    public static class GroupMetadata implements TournamentMetadata, Cloneable {
        private String name;
        private Set<String> aliases = new HashSet<>();
        private String round;
        private List<TeamMetadata> teamsMetadata = new ArrayList<>();
        private LeagueMetadata leagueMetadata;

        public LeagueMetadata getLeagueMetadata() {
            return leagueMetadata;
        }

        public void setLeagueMetadata(LeagueMetadata leagueMetadata) {
            this.leagueMetadata = leagueMetadata;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRound() {
            return round;
        }

        public void setRound(String round) {
            this.round = round;
        }

        @Override
        public boolean containsTeamName(String name) {
            if (teamsMetadata == null) {
                System.out.println("TEAM METADATA IS NULL");
                teamsMetadata = new ArrayList<>();
            }
            return teamsMetadata.stream()
                    .anyMatch(teamMetadata -> teamMetadata.containsTeam(name));
        }

        public TeamMetadata getTeamMetadata(String name) {
            return teamsMetadata.stream()
                    .filter(teamMetadata -> teamMetadata.containsTeam(name))
                    .findAny().get();
        }

        public void addTeamMetadata(TeamMetadata teamMetadata) {
            this.teamsMetadata.add(teamMetadata);
        }

        @Override
        public List<TeamMetadata> getTeamsMetadata() {
            return teamsMetadata;
        }

        public void setTeamsMetadata(List<TeamMetadata> teamsMetadata) {
            this.teamsMetadata = teamsMetadata;
        }

        public Set<String> getAliases() {
            return aliases;
        }

        public void setAliases(Set<String> aliases) {
            this.aliases = aliases;
        }

        public void addAlias(String alias) {
            if (aliases == null) {
                aliases = new HashSet<>();
            }
            aliases.add(alias);
        }

        public GroupMetadata clone() {
            try {
                return (GroupMetadata) super.clone();
            } catch (CloneNotSupportedException e) {
                // This should never happen
                throw new InternalError(e.toString());
            }
        }
    }

    public static class TeamMetadata implements Cloneable {
        private String name;
        private Set<String> aliases;

        public TeamMetadata(String name) {
            this.name = name;
            aliases = new HashSet<>();
        }

        @PersistenceConstructor
        public TeamMetadata(String name, Set<String> aliases) {
            this.name = name;
            this.aliases = aliases;
        }

        public String getName() {
            return name;
        }

        public Set<String> getAliases() {
            return aliases;
        }

        public void setAliases(Set<String> aliases) {
            this.aliases = aliases;
        }

        public void addAlias(String alias) {
            if (aliases == null) {
                aliases = new HashSet<>();
            }
            aliases.add(alias);
        }

        public boolean containsTeam(String teamName) {
            if (teamName.equals(name)) {
                return true;
            }
            return aliases.contains(teamName);
        }

        public void setName(String name) {
            this.name = name;
        }

        public TeamMetadata clone() {
            try {
                return (TeamMetadata) super.clone();
            } catch (CloneNotSupportedException e) {
                // This should never happen
                throw new InternalError(e.toString());
            }
        }
    }

    public enum Gender {
        MALE("MEN"),
        FEMALE("WOMEN"),
        MIXED("MIXED");
        String name;

        Gender(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public SportMetadata clone() {
        try {
            return (SportMetadata) super.clone();
        } catch (CloneNotSupportedException e) {
            // This should never happen
            throw new InternalError(e.toString());
        }
    }
}
