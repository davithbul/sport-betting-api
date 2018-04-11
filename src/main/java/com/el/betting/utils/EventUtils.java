package com.el.betting.utils;

import com.el.betting.sdk.v2.*;import com.el.betting.sdk.v4.*;
import com.el.betting.sdk.v2.betline.api.LayBackBetLine;
import com.el.betting.sdk.v2.betline.bettype.totalpoints.TotalPointsBetLineInfo;
import com.el.betting.sdk.v2.betline.bettype.totalpoints.TotalPointsLayBackBetLine;
import com.el.betting.sdk.v2.betoption.bettype.layback.LayBackBetOption;
import com.el.betting.sdk.v3.trading.TotalPointsBettingRule;
import com.el.betting.sdk.v3.trading.MoneyLineBettingRule;
import com.el.betting.sdk.v3.trading.TradeRule;
import com.el.betting.sdk.v4.Participant;
import org.springframework.data.repository.query.parser.Part;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.el.betting.sdk.v2.TotalType.OVER;
import static com.el.betting.sdk.v2.TotalType.UNDER;

/**
 * EventUtils contains simple and general analyzing about event.
 */
public class EventUtils {

    public static Optional<Integer> estimateGoalCount(Event<Team> event) {
        OptionalInt goalCountOptional = IntStream.range(0, 10)
                .filter(goalNumber -> isAnyOptionAvailable(event, goalNumber + 0.5))
                .findFirst();

        if(goalCountOptional.isPresent()) {
            return Optional.of(goalCountOptional.getAsInt());
        }

        return Optional.empty();
    }

    public static boolean isOddsNormalized(Event<Team> event, Period period, Team.Side side, BigDecimal allowedDifference) {
        Optional<LayBackBetOption> backBetOption = LayBackBetUtils.getMoneyLineBetOption(event, period, side, BetExchangeType.BACK);
        Optional<LayBackBetOption> layBetOption = LayBackBetUtils.getMoneyLineBetOption(event, period, side, BetExchangeType.LAY);
        return backBetOption.isPresent() && layBetOption.isPresent()
                && layBetOption.get().getPrice().subtract(backBetOption.get().getPrice()).compareTo(allowedDifference) <= 0;
    }

    public static boolean isOddsNormalized(Event<Team> event, TradeRule tradeRule, BigDecimal allowedDifference) {
        if(tradeRule instanceof TotalPointsBettingRule) {
            TotalPointsBettingRule totalPointsBettingRule = (TotalPointsBettingRule) tradeRule;
            return isOddsNormalized(event, totalPointsBettingRule.getPoints(), totalPointsBettingRule.getTotalType(), allowedDifference);
        } else if(tradeRule instanceof MoneyLineBettingRule) {
            MoneyLineBettingRule moneyLineBettingRule = (MoneyLineBettingRule) tradeRule;
            return isOddsNormalized(event, Period.MATCH, moneyLineBettingRule.getSide(), allowedDifference);
        } else {
            throw new RuntimeException("Not supported bet rule " + tradeRule);
        }
    }

    public static boolean isOddsNormalized(Event<Team> event, double totalPoints, TotalType totalType, BigDecimal allowedDifference) {
        Optional<LayBackBetOption> backBetOption = LayBackBetUtils.getTotalPointsBetOption(event, totalPoints, BetExchangeType.BACK, totalType);
        Optional<LayBackBetOption> layBetOption = LayBackBetUtils.getTotalPointsBetOption(event, totalPoints, BetExchangeType.LAY, totalType);
        return backBetOption.isPresent() && layBetOption.isPresent()
                && layBetOption.get().getPrice().subtract(backBetOption.get().getPrice()).compareTo(allowedDifference) <= 0;
    }

    /**
     * Verifies if the odds presented in event better than minPrice
     */
    public static boolean isOddBetterThan(LayBackBetOption betOption, BetExchangeType betExchangeType, BigDecimal minPrice) {
        switch (betExchangeType) {
            case BACK:
                return betOption.getPrice().compareTo(minPrice) >= 0;
            case LAY:
                return betOption.getPrice().compareTo(minPrice) <= 0;
        }
        return false;
    }

    public static List<Event<?>> getLiveEventsTypeless(List<Event<?>> eventList) {
        return eventList.stream()
                .filter(event -> event.getUpdateTime() != null && event.getStartTime() != null)
                .filter(event -> event.getUpdateTime().compareTo(event.getStartTime()) >= 0)
                .sorted((event1, event2) -> event1.getUpdateTime().compareTo(event2.getUpdateTime()))
                .collect(Collectors.toList());
    }

    public static <T extends Participant> List<Event<T>> getLiveEvents(List<Event<T>> eventList) {
        return eventList.stream()
                .filter(event -> event.getUpdateTime() != null && event.getStartTime() != null)
                .filter(event -> event.getUpdateTime().compareTo(event.getStartTime()) >= 0)
                .sorted((event1, event2) -> event1.getUpdateTime().compareTo(event2.getUpdateTime()))
                .collect(Collectors.toList());
    }

    public static <T extends Participant> List<Event<T>> getBeforeGameEvents(List<Event<T>> eventList) {
        return eventList.stream()
                .filter(event -> event.getUpdateTime().compareTo(event.getStartTime()) < 0)
                .collect(Collectors.toList());
    }

    public static boolean isAnyOptionAvailable(Event<Team> event, double points) {
        return findBestPrice(event, points, BetExchangeType.BACK, UNDER).isPresent()
                || findBestPrice(event, points, BetExchangeType.LAY, UNDER).isPresent()
                || findBestPrice(event, points, BetExchangeType.BACK, OVER).isPresent()
                || findBestPrice(event, points, BetExchangeType.LAY, OVER).isPresent();
    }

    public static <T extends Participant> List<Event<T>> sortByUpdateTime(List<Event<T>> eventList) {
        return eventList
                .stream()
                .sorted((event1, event2) -> event1.getUpdateTime().compareTo(event2.getUpdateTime()))
                .collect(Collectors.toList());
    }

    public static long getUpdateMinute(Event<? extends Participant> event) {
        return event.getStartTime().until(event.getUpdateTime(), ChronoUnit.MINUTES);
    }

    public static long getCurrentMinute(Event<? extends Participant> event) {
        return event.getStartTime().until(LocalDateTime.now(), ChronoUnit.MINUTES);
    }

    public static Optional<BetPrice> findBestPrice(Event<Team> event, double points, BetExchangeType betExchangeType, TotalType totalType) {
        for (LayBackBetLine layBackBetLine : event.getLayBackBetLines()) {

            if (layBackBetLine.getBetType() != BetType.TOTAL_POINTS || !(layBackBetLine instanceof TotalPointsLayBackBetLine)) {
                continue;
            }

            TotalPointsBetLineInfo betLineInfo = (TotalPointsBetLineInfo) layBackBetLine.getBetLineInfo();

            if (!(betLineInfo.getPoints() == points)) {
                continue;
            }

            TotalPointsLayBackBetLine totalPointsBetLine = (TotalPointsLayBackBetLine) layBackBetLine;

            if (totalType == OVER && betExchangeType == BetExchangeType.BACK) {
                return LayBackBetUtils.findHighestBackPrice(totalPointsBetLine.getOverBackPrices());
            } else if (totalType == OVER && betExchangeType == BetExchangeType.LAY) {
                return LayBackBetUtils.findLowestLayPrice(totalPointsBetLine.getOverLayPrices());
            } else if (totalType == UNDER && betExchangeType == BetExchangeType.BACK) {
                return LayBackBetUtils.findHighestBackPrice(totalPointsBetLine.getUnderBackPrices());
            } else if (totalType == UNDER && betExchangeType == BetExchangeType.LAY) {
                return LayBackBetUtils.findLowestLayPrice(totalPointsBetLine.getUnderLayPrices());
            } else {
                throw new RuntimeException("Not found requirement...");
            }
        }
        return Optional.empty();
    }
}
