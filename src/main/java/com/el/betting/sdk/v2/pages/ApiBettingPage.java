package com.el.betting.sdk.v2.pages;

import com.el.betting.sdk.v2.provider.Bookmaker;
import org.springframework.data.annotation.PersistenceConstructor;

import java.io.Serializable;

public class ApiBettingPage extends BettingPage implements Serializable {

//    private static final long serialVersionUID = 1L;

    private long eventID;
    private String lineID;

    @PersistenceConstructor
    public ApiBettingPage(Bookmaker bookmaker) {
        super(bookmaker);
    }

    public ApiBettingPage(Bookmaker bookmaker, long eventID, String lineID) {
        super(bookmaker);
        this.eventID = eventID;
        this.lineID = lineID;
    }

    public long getEventID() {
        return eventID;
    }

    public String getLineID() {
        return lineID;
    }

    @Override
    public String toString() {
        return "ApiBettingPage{" +
                "eventID=" + eventID +
                ", lineID=" + lineID +
                "} " + super.toString();
    }
}
