package com.el.betting.sdk.v3.trading;

import com.el.betting.common.TeamUtils;
import com.el.betting.sdk.v2.Event;
import com.el.betting.sdk.v2.Team;
import com.el.betting.sdk.v4.Participant;
import com.el.betting.utils.EventUtils;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class TradeContext<T extends Participant> implements Serializable {
    protected Event<T> event;

    public TradeContext(Event<T> event) {
        this.event = event;
    }

    public Event<T> getEvent() {
        return event;
    }


    public LocalDateTime getBeforeEventStartTime() {
        return getEvent().getStartTime().minusDays(1);
    }
}
