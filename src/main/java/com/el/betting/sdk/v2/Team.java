package com.el.betting.sdk.v2;


import com.el.betting.common.SimilarityUtils;
import com.el.betting.common.StringUtil;
import com.el.betting.sdk.v2.criteria.MoneyLineCriterion;
import com.el.betting.sdk.v2.criteria.SpreadLineCriterion;
import com.el.betting.sdk.v2.criteria.TeamPointsLineCriterion;
import com.el.betting.sdk.v2.criteria.TotalPointsLineCriterion;
import com.el.betting.sdk.v4.Participant;
import org.springframework.data.annotation.PersistenceConstructor;

import java.io.Serializable;

public class Team extends Participant implements MoneyLineCriterion, SpreadLineCriterion, TeamPointsLineCriterion, Serializable {

    public static final Team DRAW = new Team("DRAW", "DRAW", Side.DRAW);

    public enum Side implements TotalPointsLineCriterion, TeamPointsLineCriterion {
        HOME, AWAY, DRAW
    }

    private String originalName;

    private Side side;

    public Team(String originalName, Side side) {
        this(originalName, null, side);
    }

    @PersistenceConstructor
    public Team(String originalName, String name, Side side) {
        super(name);
        this.originalName = originalName;
        this.side = side;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public String getOriginalName() {
        return originalName;
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public String getName() {
        String name = super.getName();
        if (name == null) {
            return originalName;
        }
        return name;
    }

    public String getSetName() {
        return super.getName();
    }

    public Side getSide() {
        return side;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Team)) return false;

        Team team = (Team) o;

        if (team.getSide() != this.getSide()) {
            return false;
        }

        if (StringUtil.isAllBlank(team.getSetName(), getSetName())) {
            return SimilarityUtils.totallyEqual(originalName, team.originalName);
        }

        if (SimilarityUtils.totallyEqual(
                StringUtil.firstNotBlank(this.getSetName(), this.originalName),
                StringUtil.firstNotBlank(team.getSetName(), team.originalName)
                )) {
            return true;
        }

        if (SimilarityUtils.totallyEqual(
                StringUtil.firstNotBlank(this.originalName, this.getSetName()),
                StringUtil.firstNotBlank(team.originalName, team.getSetName())
                )) {
            return true;
        }

        if (team.side != ((Team) o).side) {
            return false;
        }

        return SimilarityUtils.totallyEqual(getSetName(), team.getSetName());
    }

    @Override
    public String toString() {
        return "Team{" +
                "originalName='" + originalName + '\'' +
                ", side=" + side +
                "} " + super.toString();
    }
}
