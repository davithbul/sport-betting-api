package com.el.betting.sdk.v2.market;

public class AffiliateStrategy {

    public static final AffiliateStrategy NONE = new AffiliateStrategy(0);

    private double returnPercentage;

    public static AffiliateStrategy grossStrategy(double returnPercentage) {
        return new AffiliateStrategy(returnPercentage);
    }

    public static AffiliateStrategy netStrategy(double grossReturnPercentage, double otherExpenses) {
        return new AffiliateStrategy(grossReturnPercentage * (100 - otherExpenses) / 100);
    }

    private AffiliateStrategy(double returnPercentage) {
        this.returnPercentage = returnPercentage;
    }

    public double getReturnPercentage() {
        return returnPercentage;
    }
}
