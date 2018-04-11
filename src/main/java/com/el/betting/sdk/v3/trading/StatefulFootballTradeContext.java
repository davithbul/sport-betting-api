package com.el.betting.sdk.v3.trading;

import com.el.betting.sdk.v2.Event;
import com.el.betting.sdk.v2.Team;
import com.el.betting.sdk.v4.Participant;

import java.util.HashMap;
import java.util.Map;

public class StatefulFootballTradeContext extends FootballTradeContext {

    private Map<String, Object> additionalProperties = new HashMap<>();

    public StatefulFootballTradeContext(Event<Team> event) {
        super(event);
    }


    public StatefulFootballTradeContext updateEvent(Event<Team> event) {
        Event<Team> oldEvent = super.event;
        super.event = event;
        league = null;
        category = null;
        homeTeamName = null;
        awayTeamName = null;
        return this;
    }

    public Object recordValue(String name, Object value) {
        return additionalProperties.put(name, value);
    }

    public Object getValue(String name) {
        return additionalProperties.get(name);
    }

    public boolean hasValue(String name) {
        return additionalProperties.containsKey(name);
    }

    public void removeValue(String name) {
        additionalProperties.remove(name);
    }

    @Override
    public String toString() {
        return "TradeBetOptionContext{" +
                "additionalProperties=" + additionalProperties +
                "} " + super.toString();
    }
}
