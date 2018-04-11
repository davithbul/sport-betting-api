package com.el.betting.sdk.v2;

import com.el.betting.common.Enums;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public enum MarketStatus {
    AVAILABLE,
    OPEN,
    INVISIBLE, //This status indicates that one or more lines have a red circle (a lower maximum bet amount).
    SUSPENDED("UNAVAILABLE"), //market can be suspended temporarily, E.G. after goal
    CLOSED; //after market is closed, it can't go back to open stat

    private String[] aliases;
    private static Map<String, MarketStatus> aliasToStatusMap = new HashMap<>();

    static {
        for (MarketStatus eventStatus : MarketStatus.values()) {
            for (String alias : eventStatus.aliases) {
                String aliasName = alias.toUpperCase().trim();
                MarketStatus oldValue = aliasToStatusMap.put(aliasName, eventStatus);
                if (oldValue != null) {
                    throw new RuntimeException("Alias is defined twice: " + alias);
                }
            }
        }
    }

    MarketStatus(String... aliases) {
        this.aliases = aliases;
    }

    public static MarketStatus value(String name) {
        name = name.toUpperCase();
        Optional<MarketStatus> eventStatusOptional = Enums.getIfPresent(MarketStatus.class, name);
        if(eventStatusOptional.isPresent()) {
            return eventStatusOptional.get();
        }

        if(aliasToStatusMap.containsKey(name)) {
            return aliasToStatusMap.get(name);
        }

        throw new IllegalArgumentException("Can't find event status having name - " + name);
    }
}
