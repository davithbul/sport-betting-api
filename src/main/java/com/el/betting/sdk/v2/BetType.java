package com.el.betting.sdk.v2;

import java.io.Serializable;

/**
 * BetType enum lists all possible bet types.
 */
public enum BetType implements Serializable {
    /**
     * A Handicap bet counters the perceived bias in ability of contestants by applying
     * a positive or negative goal/point/game start to each side or player.
     * A Handicap bet has only two options eliminating the Draw.
     */
    HANDICAP,
    /**
     * Money line and 3 way Money line, also called head to head, match odds or 2 way is
     * simply betting on the outcome of a game. Will the home team win or will the away team win.
     */
    MONEY_LINE,
    /**
     * Totals over/under also called total game points +/-, is betting whether the total amount of points
     * scored in a game will be over or under what the betting community expects it to be.
     */
    TOTAL_POINTS,
    /**
     * Team Totals over/under also called team total game points +/-, is betting whether the total amount of points
     * scored in a team during the game will be over or under what the betting community expects it to be.
     */
    TEAM_POINTS,
    /**
     * draw no bet means if the game results in a draw, your bet is refunded.
     */
    DRAW_NO_BET,
    /**
     * Double chance Allows you to cover two of the three possible outcomes in a football match with one bet.
     * Home/Draw, Your bet is a winner if the home team wins or draws.
     * Home/Away,Your bet wins if the home team or the away team wins.
     * Away/Draw,Your bet wins if the away team wins or draws.
     * It's possible to set equivalent of double chance using handicap.
     * E.G. double chance - HOME / DRAW = handicap (-0.5)
     * Also double chance might be similar to lay back
     * E.G double chance - HOME / DRAW = LAY AWAY
     */
    DOUBLE_CHANCE,
    /**
     * Futures refers to betting on the overall winner of a season or tournament.
     */
    SEASON_WINNER,
    /**
     * Futures refers to betting on the winner of a race, might be horse race or other dog run race, or presidential winner race
     */
    RACE_WINNER,
    /**
     * Applicable only for football or other binary option games.
     */
    CORRECT_SCORE
}
