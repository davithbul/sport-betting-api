package com.el.betting.common.math;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import static java.lang.Math.pow;

public class RandomNumberGenerator {

    /**
     * <p>
     * between 5 and 10, average is 7.5  <br/
     * random(5, 10, 0.5);  // between 5 and 10, average is somewhere around 8.5 <br/
     * random(5, 10, 2.0);  // between 5 and 10, average is somewhere around 6.5 <br/
     * </p>
     */
    public static int generateBiasedInt(int low, int high, double bias) {
        double random = Math.random();
        random = pow(random, bias);
        random = low + (high - low) * random;
        return (int) Math.round(random);
    }

    public static double generateBiasedDouble(double low, double high, double bias) {
        double random = Math.random();
        random = pow(random, bias);
        return low + (high - low) * random;
    }
}
