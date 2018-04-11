package com.el.betting.common;

import java.math.BigDecimal;

public class OddsComparisonService {

    private static final BigDecimal DELTA = BigDecimal.valueOf(1E-2);

    public static boolean isEqual(BigDecimal odd1, BigDecimal odd2) {
        BigDecimal subtract = odd1.subtract(odd2);
        return DELTA.compareTo(subtract.abs()) != -1;
    }
}
