package com.arobs.weather;

import java.time.Instant;
import java.util.Calendar;

public class WeatherUtils {
	public static long testUnixTime() {
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		int month = now.get(Calendar.MONTH);
		int day = now.get(Calendar.DATE);
		now.clear();
		now.set(year, month, day);
		Instant instant = Instant.ofEpochMilli(now.getTimeInMillis());
		return instant.getEpochSecond();
	}
}
