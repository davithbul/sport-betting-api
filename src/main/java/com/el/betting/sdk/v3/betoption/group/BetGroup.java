package com.el.betting.sdk.v3.betoption.group;

import com.el.betting.sdk.v2.betoption.api.Bet;
import com.el.betting.sdk.v2.betoption.api.BetOption;
import com.google.common.base.Preconditions;

import java.util.Arrays;
import java.util.function.Consumer;

@SuppressWarnings("unchecked")
public class BetGroup<bet extends Bet, betOption extends BetOption> extends BetOptionGroup<betOption> {

    private final bet[] bets;

    public BetGroup(BetOptionGroup betOptionGroup) {
        this((betOption[]) betOptionGroup.getBetOptions());
    }

    public BetGroup(betOption... betOptions) {
        this(betOptions, (bet[])new Object[betOptions.length]);
    }

    public BetGroup(bet... bets) {
        this((betOption[]) bets, bets);
    }

    public BetGroup(betOption[] betOptions, bet[] bets) {
        super(betOptions);
        Preconditions.checkArgument(betOptions.length == bets.length);
        this.bets = bets;
    }

    public void setBet(int betNumber, bet bet) {
        Preconditions.checkArgument(betNumber > 0 && betNumber<= getOptionCount(), "Bet number should be between 1 to " + getOptionCount());
        bets[betNumber - 1] = bet;
    }

    public bet getBet(int betOptionNumber) {
        Preconditions.checkArgument(betOptionNumber > 0 && betOptionNumber <= getOptionCount(), "BetOption number should be between 1 to " + getOptionCount());
        return bets[betOptionNumber - 1];
    }

    public bet[] getBets() {
        return bets;
    }

    public void forEach(Consumer<bet> betConsumer) {
        Arrays.stream(bets).forEach(betConsumer);
    }

    public int betSize() {
        return bets.length;
    }

    @Override
    public String toString() {
        return "BetGroup{" +
                "bets=" + Arrays.toString(bets) +
                "} " + super.toString();
    }
}
