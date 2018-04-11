package com.el.betting.sdk.v3.metadata;

import com.el.betting.sdk.v2.provider.Bookmaker;
import org.springframework.data.annotation.PersistenceConstructor;

public class BookmakerSportMetadata {
    private final Bookmaker bookmaker;
    private String url;
    private String category;
    private String league;
    private String team;
    private CrawlState crawlState;
    private String message;

    public BookmakerSportMetadata(Bookmaker bookmaker) {
        this.bookmaker = bookmaker;
    }

    public BookmakerSportMetadata(Bookmaker bookmaker, CrawlState crawlState) {
        this.bookmaker = bookmaker;
        this.crawlState = crawlState;
    }

    @PersistenceConstructor
    public BookmakerSportMetadata(Bookmaker bookmaker, CrawlState crawlState, String url, String category, String league, String team) {
        this.bookmaker = bookmaker;
        this.category = category;
        this.league = league;
        this.team = team;
        this.crawlState = crawlState;
    }

    public Bookmaker getBookmaker() {
        return bookmaker;
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

    public CrawlState getCrawlState() {
        return crawlState;
    }

    public void setCrawlState(CrawlState crawlState) {
        this.crawlState = crawlState;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
