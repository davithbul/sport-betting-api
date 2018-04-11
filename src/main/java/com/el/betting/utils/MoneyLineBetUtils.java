package com.el.betting.utils;

import com.el.betting.sdk.v2.*;import com.el.betting.sdk.v4.*;
import com.el.betting.sdk.v2.betoption.api.Bet;
import com.el.betting.sdk.v2.betoption.api.BetOption;
import com.el.betting.sdk.v2.betoption.api.BetOptionInfo;
import com.el.betting.sdk.v2.betoption.bettype.layback.LayBackBet;
import com.el.betting.sdk.v2.betoption.bettype.layback.LayBackBetOption;
import com.el.betting.sdk.v2.betoption.bettype.layback.LayBackBetOptionInfo;
import com.el.betting.sdk.v2.betoption.bettype.moneyline.DefaultMoneyLineBet;
import com.el.betting.sdk.v2.betoption.bettype.moneyline.MoneyLineBet;
import com.el.betting.sdk.v2.betoption.bettype.moneyline.MoneyLineBetOption;
import com.el.betting.sdk.v2.betoption.bettype.moneyline.MoneyLineBetOptionInfo;
import com.google.common.base.Preconditions;

import java.math.BigDecimal;
import java.util.Optional;

public class MoneyLineBetUtils {

    public static MoneyLineBetOptionInfo getMoneyLineBetOptionInfo(BetOptionInfo betOptionInfo) {
        MoneyLineBetOptionInfo moneyLineBetOptionInfo;
        if(betOptionInfo instanceof MoneyLineBetOptionInfo) {
            moneyLineBetOptionInfo = (MoneyLineBetOptionInfo) betOptionInfo;
        } else if(betOptionInfo instanceof LayBackBetOptionInfo && ((LayBackBetOptionInfo) betOptionInfo).getBetOptionInfo() instanceof MoneyLineBetOptionInfo) {
            Preconditions.checkArgument(((LayBackBetOptionInfo) betOptionInfo).getBetExchangeType() == BetExchangeType.BACK);
            moneyLineBetOptionInfo = (MoneyLineBetOptionInfo) ((LayBackBetOptionInfo) betOptionInfo).getBetOptionInfo();
        } else {
            throw new RuntimeException(betOptionInfo.getBettingPage().getBookmaker()  + " bettingAPI doesn't support bet with type " + betOptionInfo);
        }
        return moneyLineBetOptionInfo;
    }

    public static MoneyLineBetOption getMoneyLineBetOption(BetOption betOption) {
        MoneyLineBetOption moneyLineBetOption;
        if(betOption instanceof MoneyLineBetOption) {
            moneyLineBetOption = (MoneyLineBetOption) betOption;
        } else {
            throw new RuntimeException(betOption.getBettingPage().getBookmaker()  + " bettingAPI doesn't support bet with type " + betOption);
        }
        return moneyLineBetOption;
    }

    public static MoneyLineBet getMoneyLineBackBet(Bet bet) {
        MoneyLineBet moneyLineBet;
        if(bet instanceof MoneyLineBet) {
            moneyLineBet = (MoneyLineBet) bet;
        } else if(bet instanceof LayBackBet && bet.getBetType() == BetType.MONEY_LINE) {
            Preconditions.checkArgument(((LayBackBet) bet).getBetExchangeType() == BetExchangeType.BACK);
            moneyLineBet = new DefaultMoneyLineBet((MoneyLineBetOption) ((LayBackBet) bet).getBetOption(), bet.getStake());
        } else {
            throw new RuntimeException(bet.getBettingPage().getBookmaker()  + " bettingAPI doesn't support bet with type " + bet);
        }
        return moneyLineBet;
    }

    public static BigDecimal getBackPrice(Event<? extends Participant> event, Period period, Team.Side side) {
        Optional<LayBackBetOption> betOption = LayBackBetUtils.getMoneyLineBetOption(event, period, side, BetExchangeType.BACK);
        if (!betOption.isPresent()) {
            return BigDecimal.ONE;
        }
        return betOption.get().getPrice();
    }

    public static BigDecimal getLayPrice(Event<? extends Participant> event, Period period, Team.Side side) {
        Optional<LayBackBetOption> betOption = LayBackBetUtils.getMoneyLineBetOption(event, period, side, BetExchangeType.LAY);
        if (!betOption.isPresent()) {
            return BigDecimal.valueOf(100000);
        }
        return betOption.get().getPrice();
    }
}
