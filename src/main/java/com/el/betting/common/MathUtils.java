package com.el.betting.common;

import com.google.common.base.Preconditions;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Optional;

public class MathUtils {

    public static BigDecimal evalExpression(String expression) {
        return evalExpression(expression, BigDecimal.class);
    }

    public static <T> T evalExpression(String expression, Class<T> type) {
        Optional<BigDecimal> valueOptional = getIfConstant(expression);
        if(valueOptional.isPresent()) {
            if(!type.isAssignableFrom(Double.class)) {
                return (T) valueOptional.get();
            }
        }

        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        Object evaledExpression;
        try {
            evaledExpression = engine.eval(expression);
        } catch (ScriptException e) {
            throw new RuntimeException(e);
        }

        Preconditions.checkArgument(evaledExpression != null);
        Double doubleValue;
        if (evaledExpression instanceof Double) {
            doubleValue = (double) evaledExpression;
        } else {
            doubleValue = Double.parseDouble(evaledExpression.toString());
        }

        if (BigDecimal.class == type) {
            return (T) BigDecimal.valueOf(doubleValue);
        }

        return (T) doubleValue;
    }


    public static BigDecimal convertToDecimal(String expression) {
        Optional<BigDecimal> valueOptional = getIfConstant(expression);
        if(valueOptional.isPresent()) {
            return valueOptional.get();
        }

        Double value = evalExpression(expression, Double.class);

        //check if it was decimal
        if(expression.contains("/")) {
            return new BigDecimal(value + 1);
        } else {
            return BigDecimal.valueOf(value);
        }
    }

    public static <T, V> T nullSafeParse(V value) {
        if(value == null) {
            return null;
        }

        return (T) value;
    }

    public static Optional<BigDecimal> getIfConstant(String expression) {
        switch (expression.toUpperCase()) {
            case "EVS":
            case "EVENS":
                return Optional.of(BigDecimal.valueOf(2));
        }
        return Optional.empty();
    }

    /**
     * Returns closest upper number to value which division to mod
     * has remaining 0. If mode is zero than it fails.
     * E.g.
     * roundUp(5,5) = 5;
     * roundUp(5.1,5) = 10;
     * roundUp(4,5) = 5;
     * roundUp(8,5) = 10;
     * @param value the number which should be rounded
     * @param mod is a division factor, dividing to which will have remaining 0
     * @return
     */
    public static int roundUp(BigDecimal value, int mod) {
        BigDecimal scaled = value.setScale(0, RoundingMode.UP);
        int intValue = scaled.intValue();
        return roundUp(intValue, mod);
    }

    public static int roundUp(int value, int mod) {
        int remaining = value % mod;
        return value + (mod - remaining);
    }

    /**
     * Returns closest down number to value which division to mod
     * has remaining 0. If mode is zero than it fails.
     * E.g.
     * roundUp(5,5) = 5;
     * roundUp(5.9,5) = 5;
     * roundUp(4,5) = 0;
     * roundUp(8,5) = 5;
     * @param value the number which should be rounded
     * @param mod is a division factor, dividing to which will have remaining 0
     * @return
     */
    public static int roundDown(BigDecimal value, int mod) {
        BigDecimal scaled = value.setScale(0, RoundingMode.DOWN);
        int intValue = scaled.intValue();
        return roundDown(intValue, mod);
    }

    public static int roundDown(BigDecimal value, BigDecimal mod) {
        int intValue = value.setScale(0, RoundingMode.DOWN).intValue();
        int intMod = value.setScale(0, RoundingMode.UP).intValue();
        return roundDown(intValue, intMod);
    }

    public static int roundDown(int value, int mod) {
        int remaining = value % mod;
        return value - remaining;
    }

    public static BigDecimal min(BigDecimal value1, BigDecimal value2) {
        return value1.compareTo(value2) > 0 ? value2 : value1;
    }

    public static BigDecimal max(BigDecimal value1, BigDecimal value2) {
        return value1.compareTo(value2) > 0 ? value1 : value2;
    }

    public static String format(double number, int decimalPlaces) {
        DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.setMaximumFractionDigits(decimalPlaces);
        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
        return decimalFormat.format(number);
    }

    public static boolean signMatches(double number, double... numbers) {
        final double signum = Math.signum(number);
        return Arrays.stream(numbers)
                .allMatch(nextNumber -> Math.signum(nextNumber) == signum);
    }

    public static boolean isInteger(double doubleValue) {
        return (doubleValue == Math.floor(doubleValue)) && !Double.isInfinite(doubleValue);
    }
}
