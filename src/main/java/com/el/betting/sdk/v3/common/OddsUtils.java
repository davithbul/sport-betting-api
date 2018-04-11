package com.el.betting.sdk.v3.common;

import java.util.Arrays;

public class OddsUtils {

    public static double sumUpOdds(double... odds) {
        return sumUpOdds(odds.length, odds);
    }

    public static double sumUpOdds(int optionCount, double... odds) {
        if (odds.length < 1) {
            return 0;
        }

        if (odds[0] >= 0 && odds[0] <= 1 || String.valueOf(odds[0]).contains("NaN")) { //percent odds
            double sum = Arrays.stream(odds).sum();
            return sum / optionCount;
        } else { //normal odds
            double[] percentOdds = Arrays.stream(odds)
                    .map(OddsConverter::convertDecimalToPercent)
                    .toArray();
            double finalOdd = sumUpOdds(optionCount, percentOdds);
            //convert back to decimal
            return OddsConverter.convertPercentToDecimal(finalOdd);
        }
    }
}
