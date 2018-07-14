package com.arobs.utils;

import java.math.BigDecimal;

public class NumericUtil {

    public static BigDecimal convertToBigDecimal(String value) {

        if (value == null || value.trim().length() == 0) {
            return null;
        }

        return new BigDecimal(value);
    }

}
