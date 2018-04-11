package com.el.betting.sdk.v3.metadata;

import com.el.betting.sdk.v3.common.Gender;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.annotation.Nonnull;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Document
public class CategoryMetadata implements Cloneable {
    @Id
//    @Indexed(unique = true)
    private String name;
    @Nonnull
    private Set<String> subCategories;
    private String continent;
    private String country;
    private String location;
    private String region;
    private Gender genderGroup;
    private String fixes;
    private String ageGroup;
    private LocalDateTime updateTime;
    @Nonnull
    private Set<LeagueMetadata> leaguesMetadata;

    public CategoryMetadata(String name) {
        this(name, new HashSet<>());
    }

    public CategoryMetadata(String name, Set<LeagueMetadata> leaguesMetadata) {
        this(name, new LinkedHashSet<>(), leaguesMetadata);
    }

    @PersistenceConstructor
    public CategoryMetadata(String name, Set<String> subCategories, Set<LeagueMetadata> leaguesMetadata) {
        this.name = name;
        this.subCategories = subCategories;
        this.leaguesMetadata = leaguesMetadata;
    }

    public void setLeaguesMetadata(Set<LeagueMetadata> leaguesMetadata) {
        this.leaguesMetadata = Objects.requireNonNull(leaguesMetadata);
    }

    public void addLeagueMetadata(LeagueMetadata leagueMetadata) {
        leaguesMetadata.add(leagueMetadata);
    }

    public Set<LeagueMetadata> getLeaguesMetadata() {
        return leaguesMetadata;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(Set<String> subCategories) {
        this.subCategories = Objects.requireNonNull(subCategories);
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
