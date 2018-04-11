package com.el.betting.sdk.v2;

import com.el.betting.common.Enums;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public enum EventStatus {
    OPEN("AVAILABLE"),
    SUSPENDED("UNAVAILABLE"),
    CLOSED("CLOSED")
    ;

    private String[] aliases;
    private static Map<String, EventStatus> aliasToStatusMap = new HashMap<>();

    static {
        for (EventStatus eventStatus : EventStatus.values()) {
            for (String alias : eventStatus.aliases) {
                String aliasName = alias.toUpperCase().trim();
                EventStatus oldValue = aliasToStatusMap.put(aliasName, eventStatus);
                if (oldValue != null) {
                    throw new RuntimeException("Alias is defined twice: " + alias);
                }
            }
        }
    }

    EventStatus(String... aliases) {
        this.aliases = aliases;
    }

    public static EventStatus eventStatus(String name) {
        name = name.toUpperCase();
        Optional<EventStatus> eventStatusOptional = Enums.getIfPresent(EventStatus.class, name);
        if(eventStatusOptional.isPresent()) {
            return eventStatusOptional.get();
        }

        if(aliasToStatusMap.containsKey(name)) {
            return aliasToStatusMap.get(name);
        }

        throw new IllegalArgumentException("Can't find event status having name - " + name);
    }
}
