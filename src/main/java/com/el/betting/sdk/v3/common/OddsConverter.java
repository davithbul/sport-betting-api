package com.el.betting.sdk.v3.common;

/**
 * Converts odd from one type to another.
 */
public class OddsConverter {

    /**
     * Converts percent base given odd to decimal odd.
     * E.g. percent = 0.61 (61%) => decimal = 1 / 0.61 = 1.639
     */
    public static double convertPercentToDecimal(double percent) {
        return 1 / percent;
    }

    /**
     * Converts percent base given odd to decimal odd.
     * E.g. percent = 0.61 (61%) => decimal = 1 / 0.61 = 1.639
     */
    public static double convertDecimalToPercent(double decimal) {
        return 1 / decimal;
    }
}
