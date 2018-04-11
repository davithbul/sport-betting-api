package com.el.betting.sdk.v3.metadata;


import com.el.betting.common.CollectionUtil;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * LeagueGroupMetadata represents group of league.
 * E.G. Spain 2nd division have multiple groups - A,B,C ...
 * It is possible to use league instead of group, using special name convention - i.e. "leagueName - groupName"
 */
@Document
public class LeagueGroupMetadata implements TournamentMetadata, Cloneable {
    private String name;
    private String round;
    private Set<String> aliases;
    private Set<TeamMetadata> teamsMetadata;

    public LeagueGroupMetadata(String name, String round) {
        this(name, round, new HashSet<>(), new HashSet<>());
    }

    @PersistenceConstructor
    public LeagueGroupMetadata(String name, String round, Set<String> aliases, Set<TeamMetadata> teamsMetadata) {
        CollectionUtil.applyToCollection(aliases, String::toLowerCase);
        this.name = name;
        this.round = round;
        this.aliases = aliases;
        this.teamsMetadata = teamsMetadata;
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
    public boolean containsTeam(String name) {
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
    public Set<TeamMetadata> getTeamsMetadata() {
        return teamsMetadata;
    }

    public void setTeamsMetadata(Set<TeamMetadata> teamsMetadata) {
        Objects.requireNonNull(teamsMetadata);
        this.teamsMetadata = teamsMetadata;
    }

    public Set<String> getAliases() {
        return aliases;
    }

    public void setAliases(Set<String> aliases) {
        Objects.requireNonNull(aliases);
        CollectionUtil.applyToCollection(aliases, String::toLowerCase);
        this.aliases = aliases;
    }

    public void addAlias(String alias) {
        Objects.requireNonNull(alias);
        aliases.add(alias.toLowerCase());
    }

    public LeagueGroupMetadata clone() {
        try {
            return (LeagueGroupMetadata) super.clone();
        } catch (CloneNotSupportedException e) {
            // This should never happen
            throw new InternalError(e.toString());
        }
    }
}
