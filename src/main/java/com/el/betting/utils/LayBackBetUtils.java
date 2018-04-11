package com.el.betting.utils;

import com.el.betting.sdk.v2.*;import com.el.betting.sdk.v4.*;
import com.el.betting.sdk.v2.betline.api.LayBackBetLine;
import com.el.betting.sdk.v2.betline.bettype.moneyline.MoneyLineLayBackBetLine;
import com.el.betting.sdk.v2.betline.bettype.totalpoints.TotalPointsBetLineInfo;
import com.el.betting.sdk.v2.betline.bettype.totalpoints.TotalPointsLayBackBetLine;
import com.el.betting.sdk.v2.betoption.bettype.layback.LayBackBetOption;
import com.el.betting.sdk.v2.betoption.bettype.moneyline.MoneyLineBetOption;
import com.el.betting.sdk.v2.betoption.bettype.totalpoints.TotalPointsBetOption;
import com.el.betting.sdk.v3.trading.TotalPointsBettingRule;
import com.el.betting.sdk.v3.trading.MoneyLineBettingRule;
import com.el.betting.sdk.v3.trading.TradeRule;
import com.el.betting.sdk.v4.Participant;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class LayBackBetUtils {

    public static Optional<BetPrice> findHighestBetPrice(List<BetPrice> betPriceList) {
        if (betPriceList == null) {
            return Optional.empty();
        }
        return betPriceList.stream().max((betPrice1, betPrice2) -> ((int) betPrice1.getPrice().subtract(betPrice2.getPrice()).doubleValue() * 1000));
    }

    public static Optional<BetPrice> findHighestBackPrice(List<BetPrice> betPriceList) {
        if (betPriceList == null) {
            return Optional.empty();
        }
        return betPriceList.stream().max((betPrice1, betPrice2) -> ((int) betPrice2.getPrice().subtract(betPrice1.getPrice()).doubleValue() * 1000));
    }

    public static Optional<BetPrice> findLowestLayPrice(List<BetPrice> betPriceList) {
        if (betPriceList == null) {
            return Optional.empty();
        }
        return betPriceList.stream().min((betPrice1, betPrice2) -> ((int) betPrice1.getPrice().subtract(betPrice2.getPrice()).doubleValue() * 1000));
    }

    public static Optional<MoneyLineLayBackBetLine> getMoneyLineBetLine(Event<? extends Participant> event, Period period) {
        for (LayBackBetLine layBackBetLine : event.getLayBackBetLines()) {
            if (layBackBetLine.getBetType() != BetType.MONEY_LINE) {
                continue;
            }

            MoneyLineLayBackBetLine moneyLineLayBackBetLine = (MoneyLineLayBackBetLine) layBackBetLine;
            if (moneyLineLayBackBetLine.getPeriod() == period) {
                return Optional.of(moneyLineLayBackBetLine);
            }
        }

        return Optional.empty();
    }

    public static Optional<TotalPointsLayBackBetLine> getTotalPointsBetLine(Event<? extends Participant> event, double points) {
        for (LayBackBetLine layBackBetLine : event.getLayBackBetLines()) {
            if (layBackBetLine.getBetType() != BetType.TOTAL_POINTS || !(layBackBetLine instanceof TotalPointsLayBackBetLine)) {
                continue;
            }
            TotalPointsLayBackBetLine totalPointsBetLine = (TotalPointsLayBackBetLine) layBackBetLine;
            TotalPointsBetLineInfo betLineInfo = (TotalPointsBetLineInfo) layBackBetLine.getBetLineInfo();
            if (betLineInfo.getPoints() == points) {
                return Optional.of(totalPointsBetLine);
            }
        }

        return Optional.empty();
    }

    public static Optional<LayBackBetOption> getBetOption(Event<? extends Participant> event, TradeRule tradeRule, BetExchangeType betExchangeType) {
        if(tradeRule instanceof TotalPointsBettingRule) {
            TotalPointsBettingRule totalPointsBettingRule = (TotalPointsBettingRule) tradeRule;
            return getTotalPointsBetOption(event, totalPointsBettingRule.getPoints(), betExchangeType, totalPointsBettingRule.getTotalType());
        } else if(tradeRule instanceof MoneyLineBettingRule) {
            MoneyLineBettingRule moneyLineBettingRule = (MoneyLineBettingRule) tradeRule;
            return getMoneyLineBetOption(event, Period.MATCH, moneyLineBettingRule.getSide(), betExchangeType);
        } else {
            throw new RuntimeException("Can't get bet option for " + tradeRule.getClass());
        }
    }

    public static Optional<LayBackBetOption> getTotalPointsBetOption(Event<? extends Participant> event, double points, BetExchangeType betExchangeType, TotalType totalType) {
        Optional<TotalPointsLayBackBetLine> totalPointsBetLineOptional = LayBackBetUtils.getTotalPointsBetLine(event, points);
        if (totalPointsBetLineOptional.isPresent()) {
            //now back with the best back price
            return totalPointsBetLineOptional.get().getBetOptions()
                    .stream()
                    .filter(layBackBetOption -> {
                        TotalPointsBetOption betOptionInfo = (TotalPointsBetOption) layBackBetOption.getBetOptionInfo();
                        return layBackBetOption.getBetExchangeType() == betExchangeType
                                && betOptionInfo.getTotalType() == totalType;
                    })
                    .findAny();

        }

        return Optional.empty();
    }


    public static Optional<LayBackBetOption> getMoneyLineBetOption(Event<? extends Participant> event, Period period, Team.Side side, BetExchangeType betExchangeType) {
        Optional<MoneyLineLayBackBetLine> moneyLineBetLine = LayBackBetUtils.getMoneyLineBetLine(event, period);
        if (moneyLineBetLine.isPresent()) {
            //now find best price
            return moneyLineBetLine.get().getBetOptions()
                    .stream()
                    .filter(layBackBetOption -> {
                        MoneyLineBetOption betOptionInfo = (MoneyLineBetOption) layBackBetOption.getBetOptionInfo();
                        return layBackBetOption.getBetExchangeType() == betExchangeType &&
                                betOptionInfo.getTeam().getSide() == side;
                    })
                    .findAny();

        }

        return Optional.empty();
    }

    public static boolean isSelectionWon(int goalCount, double points, BetExchangeType betExchangeType, TotalType totalType) {
        return (betExchangeType == BetExchangeType.BACK && totalType == TotalType.OVER
                || betExchangeType == BetExchangeType.LAY && totalType == TotalType.UNDER) ?
                goalCount > points :
                goalCount < points;
    }

    public static boolean isSelectionWon(Score<Integer> score, BetExchangeType betExchangeType, Team.Side side) {
        boolean sideWon;
        switch (side) {
            case HOME:
                sideWon = score.getHomeSideScore() > score.getAwaySideScore();
                break;
            case AWAY:
                sideWon = score.getAwaySideScore() > score.getHomeSideScore();
                break;
            case DRAW:
                sideWon = Objects.equals(score.getHomeSideScore(), score.getAwaySideScore());
                break;
            default:
                throw new RuntimeException("Unknown side " + side);
        }

        return (betExchangeType == BetExchangeType.BACK) == sideWon;
    }
}