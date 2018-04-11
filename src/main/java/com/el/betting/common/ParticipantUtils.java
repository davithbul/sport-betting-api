package com.el.betting.common;

import com.el.betting.sdk.v2.Team;
import com.el.betting.sdk.v4.Participant;

import java.util.List;
import java.util.stream.Collectors;

public class ParticipantUtils {
    public static boolean isMatching(List<? extends Participant> participants1, List<? extends Participant> participants2) {
        if (participants1.size() != participants2.size()) {
            return false;
        }

        for (Participant participant1 : participants1) {
            if (!participants2.contains(participant1)) {
                return false;
            }
        }

        return true;
    }

    public static String printNames(List<? extends Participant> participants) {
        if(participants.get(0) instanceof Team) {
            return TeamUtils.printTeamNames((List<Team>)participants);
        } else {
            return participants.stream()
                    .map(Participant::getName)
                    .collect(Collectors.joining(", "));
        }
    }
}
