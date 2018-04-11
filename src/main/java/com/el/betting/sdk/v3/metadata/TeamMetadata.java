package com.el.betting.sdk.v3.metadata;

import com.el.betting.common.CollectionUtil;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Document
public class TeamMetadata implements Cloneable {
    @Id
//    @Indexed(unique = true)
    private String name;
    private Set<String> aliases;

    public TeamMetadata(String name) {
        this(name, new HashSet<>());
    }

    @PersistenceConstructor
    public TeamMetadata(String name, Set<String> aliases) {
        Objects.requireNonNull(aliases);
        CollectionUtil.applyToCollection(aliases, String::toLowerCase);
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
        Objects.requireNonNull(aliases);
        CollectionUtil.applyToCollection(aliases, String::toLowerCase);
        this.aliases = aliases;
    }

    public void addAlias(String alias) {
        String canonicalAlias = Objects.requireNonNull(alias).toLowerCase();
        aliases.add(canonicalAlias);
    }

    public boolean containsTeam(String teamName) {
        return teamName.equalsIgnoreCase(name) || aliases.contains(teamName.toLowerCase());
    }

    public void setName(String name) {
        this.name = Objects.requireNonNull(name);
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
