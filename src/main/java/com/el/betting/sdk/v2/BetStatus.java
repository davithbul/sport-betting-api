package com.el.betting.sdk.v2;

import com.el.betting.common.Enums;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public enum BetStatus {
    NEW,
    REJECTED,
    REFUNDED,
    PARTIALLY_CANCELLED,
    CANCELLED,
    EXPIRED,
    PARTIAL_MATCHED,
    OPEN("UNMATCHED", "PENDING_ACCEPTANCE"),
    PAUSED,
    FAILED("FAILURE", "PROCESSED_WITH_ERRORS"),
    LOSE,
    WON,
    TIMEOUT,
    SUCCESS("MATCHED", "ACCEPTED");

    private String[] aliases;
    private static Map<String, BetStatus> aliasToBetStatusMap = new HashMap<>();

    static {
        for (BetStatus betStatus : BetStatus.values()) {
            for (String alias : betStatus.aliases) {
                BetStatus oldValue = aliasToBetStatusMap.put(alias, betStatus);
                if (oldValue != null) {
                    throw new RuntimeException("Alias is defined twice: " + alias);
                }
            }
        }
    }

    BetStatus(String... aliases) {
        this.aliases = aliases;
    }

    public static BetStatus getValue(@Nonnull  String statusName) {
        statusName = statusName.toUpperCase().trim();
        BetStatus betStatus;

        Optional<BetStatus> betStatusOptional = Enums.getIfPresent(BetStatus.class, statusName);
        if (betStatusOptional.isPresent()) {
            betStatus = betStatusOptional.get();
        } else {
            betStatus = aliasToBetStatusMap.get(statusName);
            if(betStatus == null) {
                throw new IllegalArgumentException("Can't find status with name: " + statusName);
            }
        }

        return betStatus;
    }
}
