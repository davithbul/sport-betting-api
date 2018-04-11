package com.el.betting.sdk.v3.trading;

import com.el.betting.common.TeamUtils;
import com.el.betting.sdk.v2.Event;
import com.el.betting.sdk.v2.Team;
import com.el.betting.utils.EventUtils;

import java.time.LocalDateTime;

public class FootballTradeContext extends TradeContext<Team> {
    protected String league;
    protected String category;
    protected String homeTeamName;
    protected String awayTeamName;

    public FootballTradeContext(Event<Team> event) {
        super(event);
    }

    public int estimateGoalCount() {
        return EventUtils.estimateGoalCount(event).orElse(1000);
    }

    public String getLeague() {
        if (league == null) {
            league = (String) getEvent().getAdditionalProperties().get("leagueName");
        }
        return league;
    }

    public String getCategory() {
        if (category == null) {
            category = (String) getEvent().getAdditionalProperties().get("category");
        }
        return category;
    }

    public String getHomeTeam() {
        if (homeTeamName == null) {
            homeTeamName = TeamUtils.getHomeTeam(event.getParticipants()).getName();
        }
        return homeTeamName;
    }

    public String getAwayTeam() {
        if (awayTeamName == null) {
            awayTeamName = TeamUtils.getAwayTeam(event.getParticipants()).getName();
        }
        return awayTeamName;
    }

    public LocalDateTime getBeforeEventStartTime() {
        return getEvent().getStartTime().minusDays(1);
    }
}
