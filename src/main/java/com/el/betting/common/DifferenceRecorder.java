package com.el.betting.common;

import java.util.Arrays;
import java.util.function.BiFunction;

public class DifferenceRecorder {

    private StringBuilder difference = new StringBuilder();

    public DifferenceRecorder compareTeamNames(BiFunction<String, String, Boolean> predicate, String teamName1, String teamName2) {
        if(!predicate.apply(teamName1, teamName2)) {
           difference.append(String.format("Team names are different: %s <> %s.", teamName1, teamName2));
           difference.append(" ");
        }
        return this;
    }

    public DifferenceRecorder compareBetTypes(BiFunction<String, String, Boolean> predicate, String expected, String original) {
        long count = Arrays.stream(original.split("\\|"))
                .filter(originalValue -> predicate.apply(expected, originalValue))
                .count();
        if(count == 0) {
            difference.append(String.format("Bet types are different %s <> %s.", expected, original));
            difference.append(" ");
        }
        return this;
    }

    public String get() {
        return difference.toString();
    }


}
