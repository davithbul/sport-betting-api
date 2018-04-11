package com.el.betting.sdk.common;

import com.el.betting.common.Enums;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.el.betting.sdk.common.SportType.Type.*;

public enum SportType {
    ATHLETICS,
    AMERICAN_FOOTBALL,
    AUSTRALIAN_FOOTBALL("Australian Rules"),
    BADMINTON,
    BANDY,
    BASEBALL,
    BASKETBALL(TEAM),
    BOXING(BOXER),
    BEACH_VOLLEYBALL,
    CRICKET,
    CURLING,
    DARTS(PLAYER),
    DARTS_LEGS(PLAYER),
    E_SPORTS(PLAYER, "E_SPORT", "Virtual Sports", "E-Sports", "E-Sport"),
    FINANCIAL("Financial Bets"),
    FLOORBALL,
    FENCING,
    FISHING,
    FOOTBALL("SOCCER"),
    FUTSAL,
    INLINE_HOCKEY,
    LACROSSE,
    FIELD_LACROSSE,
    SPEEDWAY,
    MOTORCYCLE_RACING,
    TRACK_AND_FIELD,
    WRESTLING,
    GOLF(PLAYER),
    GREYHOUND_RACING("Greyhounds"),
    GAELIC_GAMES,
    HANDBALL,
    HOCKEY,
    AUTO_RACING,
    BOWLING(PLAYER),
    HORSE_RACING,
    HARNESS_RACING,
    MIXED_MARTIAL_ARTS("MMA"),
    OTHER_SPORTS,
    ROWING,
    POLITICS,
    RUGBY_LEAGUE,
    RUGBY_UNION,
    SNOOKER("Snooker & Pool"),
    SOFTBALL,
    SQUASH,
    ULTIMATE,
    INDOOR_LACROSSE,
    TABLE_TENNIS(PLAYER),
    TENNIS(PLAYER),
    TRIATHLON,
    VOLLEYBALL,
    WATER_POLO,
    AUSSIE_RULES,
    ALPINE_SKIING,
    BIATHLON,
    POOL,
    SKI_JUMPING,
    CROSS_COUNTRY,
    FORMULA_1,
    CYCLING,
    BOBSLEIGH,
    FIGURE_SKATING,
    FREESTYLE_SKIING,
    LUGE,
    NETBALL,
    NORDIC_COMBINED,
    NFL,
    SHORT_TRACK,
    SKELETON,
    SNOW_BOARDING,
    SPEED_SKATING,
    CHESS,
    PESAPALLO,
    POKER,
    COMBAT_SPORTS(PLAYER),
    ICE_HOCKEY,
    INTERNATIONAL_RULES_FOOTBALL("INTERNATIONAL RULES", "IR", "Inter rules", "Compromise rules"),
    MOTOR_SPORTS("MOTORSPORT", "MOTORSPORTS"),
    RUGBY,
    SOCIETY,
    WINTER_SPORTS,
    ROLLER_HOCKEY("rink hockey", "quad hockey"),
    SAILING("Yachting"),
    SWIMMING,
    SURFING,
    BOWLS;

    private final static Logger log = LoggerFactory.getLogger(SportType.class);

    private Type type;
    private String[] aliases;
    private static Map<String, SportType> aliasToSportMap = new HashMap<>();

    public enum Type {
        PLAYER,
        CLUB,
        TEAM,
        BOXER
    }

    SportType() {
        this(CLUB);
    }

    static {
        for (SportType sportType : SportType.values()) {
            for (String alias : sportType.aliases) {
                String aliasName = StringUtils.replaceChars(alias, ' ', '_').toUpperCase().trim();
                SportType oldValue = aliasToSportMap.put(aliasName, sportType);
                if (oldValue != null) {
                    throw new RuntimeException("Alias is defined twice: " + alias);
                }
            }
        }
    }

    SportType(Type type, String... aliases) {
        this.type = type;
        this.aliases = aliases;
    }

    SportType(String... aliases) {
        this(CLUB, aliases);
    }

    public Type getType() {
        return type;
    }

    public static SportType sportType(String sportName) {
        Optional<SportType> sportTypeOptional = sportTypeOptional(sportName);
        if(!sportTypeOptional.isPresent()) {
            throw new IllegalArgumentException("Can't find sport type: " + sportName);
        }

        return sportTypeOptional.get();
    }

    public static Optional<SportType> sportTypeOptional(String sportName) {
        sportName = StringUtils.replaceChars(sportName, ' ', '_');
        sportName = sportName.toUpperCase().trim();

        Optional<SportType> sportTypeOptional = Enums.getIfPresent(SportType.class, sportName);
        if(sportTypeOptional.isPresent()) {
            return Optional.of(sportTypeOptional.get());
        }

        //check in plural form
        sportTypeOptional = Enums.getIfPresent(SportType.class, sportName + "S");
        if(sportTypeOptional.isPresent()) {
            return Optional.of(sportTypeOptional.get());
        }

        //check in without underscore form
        sportTypeOptional = Enums.getIfPresent(SportType.class, StringUtils.replaceChars(sportName, "_", ""));
        if(sportTypeOptional.isPresent()) {
            return Optional.of(sportTypeOptional.get());
        }

        //remove if in plural form
        if(sportName.endsWith("S")) {
            sportTypeOptional = Enums.getIfPresent(SportType.class, sportName.substring(0, sportName.length() - 2));
            if(sportTypeOptional.isPresent()) {
                return Optional.of(sportTypeOptional.get());
            }
        }

        //check in alias list
        SportType sportType = aliasToSportMap.get(sportName);

        if(sportType == null) {
            log.error("Can't find sport type: " + sportName);
        }

        return Optional.ofNullable(sportType);
    }

    public static boolean isValidName(String sportName) {
        return sportTypeOptional(sportName).isPresent();
    }

    @Override
    public String toString() {
        return StringUtils.replaceChars(this.name(), '_', ' ');
    }
}
