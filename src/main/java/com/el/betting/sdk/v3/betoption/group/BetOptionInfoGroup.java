package com.el.betting.sdk.v3.betoption.group;

import com.el.betting.sdk.v2.betoption.api.BetOptionInfo;
import com.el.betting.sdk.v2.pages.BettingPage;
import com.google.common.base.Preconditions;


public class BetOptionInfoGroup<T extends BetOptionInfo> {

    private final T[] betOptionInfoList;

    @SafeVarargs
    public BetOptionInfoGroup(T... betOptionInfoList) {
//        Preconditions.checkArgument(betOptionInfoList.length >= 1);
        this.betOptionInfoList = betOptionInfoList;
    }

    public void setBetOptionInfo(int betOptionInfoNumber, T betOptionInfo) {
        Preconditions.checkArgument(betOptionInfoNumber <= betOptionInfoList.length && betOptionInfoNumber > 0, "BetOption number should be between 1 to 3, " + betOptionInfoNumber);
        betOptionInfoList[betOptionInfoNumber - 1] = betOptionInfo;
    }

    public T getBetOptionInfo(int betOptionInfoNumber) {
        Preconditions.checkArgument(betOptionInfoNumber <= betOptionInfoList.length && betOptionInfoNumber > 0, "BetOption number should be between 1 to 3, " + betOptionInfoNumber);
        return betOptionInfoList[betOptionInfoNumber - 1];
    }

    public BettingPage getBettingPage(int betOptionInfoNumber) {
        Preconditions.checkArgument(betOptionInfoNumber <= betOptionInfoList.length && betOptionInfoNumber > 0, "BetOption number should be between 1 to 3, " + betOptionInfoNumber);
        return betOptionInfoList[betOptionInfoNumber - 1].getBettingPage();
    }

    public int getOptionCount() {
        return betOptionInfoList.length;
    }

    public T[] getBetOptionInfoList() {
        return betOptionInfoList;
    }
}
