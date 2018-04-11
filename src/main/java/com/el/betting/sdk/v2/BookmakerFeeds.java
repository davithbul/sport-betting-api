package com.el.betting.sdk.v2;

import com.el.betting.sdk.v2.provider.Bookmaker;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class BookmakerFeeds {

    @Id
    private Bookmaker bookmaker;

    private List<Sport> sports;

    public BookmakerFeeds(Bookmaker bookmaker, List<Sport> sports) {
        this.bookmaker = bookmaker;
        this.sports = sports;
    }

    public Bookmaker getBookmaker() {
        return bookmaker;
    }

    public List<Sport> getSports() {
        return sports;
    }
}
