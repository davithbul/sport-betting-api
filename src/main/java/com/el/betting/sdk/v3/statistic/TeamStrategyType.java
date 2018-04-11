package com.el.betting.sdk.v3.statistic;

public enum TeamStrategyType {
    MESHOK,     //Team who concedes a lot of goals
    OFFENCIVE, //Team who is good at atacking, have a atacking mindset
    DEFENCIVE,  //Team who is good defencively, but there is nothing specific about their offence - Lescter city
    KEEP_WINS, //if team has advantage they will stick on that - Atletico, Juventus
    ALWAYS_ATACKS //atack no matter the current score e.g. Barcelona
}
