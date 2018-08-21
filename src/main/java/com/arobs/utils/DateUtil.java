package com.arobs.utils;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public static boolean isSameYearAndMonth(Date d1, Date d2) {

        Calendar c1 = Calendar.getInstance();
        c1.setTime(d1);

        Calendar c2 = Calendar.getInstance();
        c2.setTime(d2);

        return c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR) && c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH);
    }

}
