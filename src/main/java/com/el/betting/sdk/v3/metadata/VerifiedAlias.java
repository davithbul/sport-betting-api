package com.el.betting.sdk.v3.metadata;

import com.el.betting.sdk.common.SportType;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.annotation.Nonnull;

@Document
@CompoundIndex(name = "verifiedAlias_unique_index", def = "{'sportType' : 1, 'name' : 1, 'alias' : 1}", unique = true, sparse = true, background = true)
public class VerifiedAlias {

    private SportType sportType;
    private String name;
    private String alias;

    public VerifiedAlias(@Nonnull SportType sportType,
                         @Nonnull String name,
                         @Nonnull String alias) {
        this.sportType = sportType;
        this.name = name;
        this.alias = alias;
    }

    public SportType getSportType() {
        return sportType;
    }

    public String getName() {
        return name;
    }

    public String getAlias() {
        return alias;
    }
}
