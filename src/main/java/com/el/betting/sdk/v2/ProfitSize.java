package com.el.betting.sdk.v2;



public enum ProfitSize {
    NEGATIVE(Integer.MIN_VALUE, false),
    TINY(0.001, false),
    SMALL(0.1, false),
    NORMAL(2.7, true),
    BIG(4.2, true),
    HUGE(8, true),
    EXTRAORDINARY(15, true);

    private double min;
    private boolean worthBetting;

    ProfitSize(double min, boolean worthBetting) {
        this.min = min;
        this.worthBetting = worthBetting;
    }

    public boolean isWorthBetting() {
        return worthBetting;
    }

    public static ProfitSize getSize(double percentile) {
        ProfitSize[] values = ProfitSize.values();
        int length = values.length;
        for (int i = length - 1; i >= 0; i--) {
            if (percentile > values[i].min) {
                return values[i];
            }
        }

        throw new IllegalArgumentException("Illegal win percentile: " + percentile);
    }
}
