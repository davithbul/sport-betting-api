package com.el.betting.sdk.v3.metadata;

import com.el.betting.sdk.common.SportType;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.annotation.Nonnull;
import java.util.HashSet;
import java.util.Set;

@Document
public class SportMetadata implements Cloneable {

    @Id
    private SportType sportType;

    private Set<CategoryMetadata> categories;

    public SportMetadata(@Nonnull SportType sportType) {
        this(sportType, new HashSet<>());
    }

    @PersistenceConstructor
    public SportMetadata(@Nonnull SportType sportType, @Nonnull Set<CategoryMetadata> categories) {
        this.sportType = sportType;
        this.categories = categories;
    }

    public SportType getSportType() {
        return sportType;
    }

    public void setSportType(SportType sportType) {
        this.sportType = sportType;
    }

    public void addCategory(CategoryMetadata categoryMetadata) {
        categories.add(categoryMetadata);
    }

    public void setCategories(Set<CategoryMetadata> categories) {
        this.categories = categories;
    }

    public Set<CategoryMetadata> getCategories() {
        return categories;
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
