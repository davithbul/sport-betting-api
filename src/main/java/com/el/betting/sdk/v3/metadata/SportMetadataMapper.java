package com.el.betting.sdk.v3.metadata;

import com.el.betting.sdk.common.SportType;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

/**
 * SportMetadataMapper keeps metadata for the sport category, league and team names
 * crawled from bookmaker. This is mapping between system defined metadata names
 * and bookmaker represented names.
 *
 * Current usage is based on oddschecker.com data.
 */
@Document
@CompoundIndex(name = "sportMetadataMapper_root_index", def = "{'sportType' : 1, 'category' : 1, 'league' : 1, 'team' : 1}", unique = true, sparse = true, background = true)
public class SportMetadataMapper {
    @Nonnull
    private final SportType sportType;
    private String category;
    @Nonnull
    private String league;
    @Nonnull
    private String team;
    private List<BookmakerSportMetadata> bookmakerSportMetadataList;

    public SportMetadataMapper(SportType sportType) {
        this.sportType = sportType;
        this.bookmakerSportMetadataList = new ArrayList<>();
    }

    @PersistenceConstructor
    public SportMetadataMapper(SportType sportType, String category, String league, String team) {
        this.sportType = sportType;
        this.category = category;
        this.league = league;
        this.team = team;
        this.bookmakerSportMetadataList = new ArrayList<>();
    }

    public SportType getSportType() {
        return sportType;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLeague() {
        return league;
    }

    public void setLeague(String league) {
        this.league = league;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public List<BookmakerSportMetadata> getBookmakerSportMetadataList() {
        return bookmakerSportMetadataList;
    }

    public void setBookmakerSportMetadataList(List<BookmakerSportMetadata> bookmakerSportMetadataList) {
        this.bookmakerSportMetadataList = bookmakerSportMetadataList;
    }

    public void addBookmakerSportMetadata(BookmakerSportMetadata bookmakerSportMetadata) {
        bookmakerSportMetadataList.add(bookmakerSportMetadata);
    }
}
