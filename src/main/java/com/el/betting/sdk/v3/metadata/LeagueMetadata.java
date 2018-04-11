package com.el.betting.sdk.v3.metadata;

import com.el.betting.common.CollectionUtil;
import com.el.betting.common.StringUtil;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.annotation.Nonnull;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Document
public class LeagueMetadata implements TournamentMetadata, Cloneable {
    @Id
//    @Indexed(unique = true)
    private String name;
    private String code;
    private String abbreviation;
    private String type;
    private String length;
    private String memberCount;
    private Set<String> aliases;
    private Set<LeagueGroupMetadata> groupsMetadata;
    private Set<TeamMetadata> teamsMetadata;

    public LeagueMetadata(@Nonnull String name) {
        this(name, null);
    }

    public LeagueMetadata(@Nonnull String name, String type) {
        this(name, type, new HashSet<>());
    }

    public LeagueMetadata(@Nonnull String name, String type, @Nonnull Set<String> aliases) {
        this(name, null, aliases, type, new HashSet<>());
    }

    public LeagueMetadata(@Nonnull String name, String abbreviation, @Nonnull Set<String> aliases, String type, @Nonnull Set<TeamMetadata> teamsMetadata) {
        this(name, abbreviation, type, aliases, teamsMetadata, new HashSet<>());
    }

    @PersistenceConstructor
    public LeagueMetadata(@Nonnull String name, String abbreviation, String type, @Nonnull Set<String> aliases, @Nonnull Set<TeamMetadata> teamsMetadata, @Nonnull Set<LeagueGroupMetadata> groupsMetadata) {
        CollectionUtil.applyToCollection(aliases, String::toLowerCase);
        this.name = name;
        this.abbreviation = abbreviation;
        this.type = type;
        this.aliases = aliases;
        this.teamsMetadata = teamsMetadata;
        this.groupsMetadata = groupsMetadata;
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
        return aliases;
    }

    public void setAliases(Set<String> aliases) {
        CollectionUtil.applyToCollection(aliases, String::toLowerCase);
        this.aliases = aliases;
    }

    public void addAlias(String alias) {
        String canonicalAlias = Objects.requireNonNull(alias).toLowerCase();
        aliases.add(canonicalAlias);
    }

    public void setName(String name) {
        this.name = Objects.requireNonNull(name);
    }

    public void setType(String type) {
        this.type = type;
    }

    public Set<TeamMetadata> getTeamsMetadata() {
        return teamsMetadata;
    }

    public void setTeamsMetadata(Set<TeamMetadata> teamsMetadata) {
        this.teamsMetadata = Objects.requireNonNull(teamsMetadata);
    }

    public void addTeamMetadata(TeamMetadata teamMetadata) {
        this.teamsMetadata.add(teamMetadata);
    }

    public void addTeamsMetadata(Set<TeamMetadata> teamsMetadata) {
        this.teamsMetadata.addAll(teamsMetadata);
    }

    public void addGroupMetadata(LeagueGroupMetadata groupMetadata) {
        this.groupsMetadata = new HashSet<>();
        this.groupsMetadata.add(groupMetadata);
        Set<TeamMetadata> groupTeams = groupMetadata.getTeamsMetadata();
        if (groupTeams == null) {
            groupTeams = new HashSet<>();
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

    public Set<LeagueGroupMetadata> getGroupsMetadata() {
        return groupsMetadata;
    }

    public void setGroupsMetadata(Set<LeagueGroupMetadata> groupsMetadata) {
        this.groupsMetadata = groupsMetadata;
    }

    public boolean containsLeague(String name) {
        return this.name.equalsIgnoreCase(name) || aliases.contains(name.toLowerCase());
    }

    public Optional<LeagueGroupMetadata> getGroupIfExist(String name) {
        if (getGroupsMetadata() == null || aliases == null) {
            return Optional.empty();
        }

        String[] strings = aliases.toArray(new String[aliases.size()]);
        name = StringUtil.removeWords(name, this.name);
        name = StringUtil.removeWords(name, strings);

        final String newName = name;
        Optional<LeagueGroupMetadata> groupMetadataOptional = getGroupsMetadata().stream()
                .filter(groupMetadata ->
                        StringUtil.removeWords(newName, groupMetadata.getName()).trim().isEmpty()
                                || (groupMetadata.getAliases() != null && StringUtil.removeWords(newName, groupMetadata.getAliases().toArray(new String[groupMetadata.getAliases().size()])).trim().isEmpty()))
                .findAny();
        return groupMetadataOptional;
    }

    public boolean containsTeam(String name) {
        return teamsMetadata.stream()
                .anyMatch(teamMetadata -> teamMetadata.containsTeam(name));
    }

    public TeamMetadata getTeamMetadata(String name) {
        return teamsMetadata.stream()
                .filter(teamMetadata -> teamMetadata.containsTeam(name))
                .findAny().get();
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
