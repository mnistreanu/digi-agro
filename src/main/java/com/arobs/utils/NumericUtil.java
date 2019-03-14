package com.arobs.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.function.BiFunction;

public class NumericUtil {

    public static BigDecimal convertToBigDecimal(String value) {

        if (value == null || value.trim().length() == 0) {
            return null;
        }

        return new BigDecimal(value);
    }

    public static BigDecimal calc(BiFunction<BigDecimal, BigDecimal, BigDecimal> fn, BigDecimal a, BigDecimal b) {
        if (a == null) a = BigDecimal.ZERO;
        if (b == null) b = BigDecimal.ZERO;

        try {
            return fn.apply(a, b).setScale(5, RoundingMode.HALF_UP);
        }
        catch (Exception e) {
            return BigDecimal.ZERO;
        }
    }

}
