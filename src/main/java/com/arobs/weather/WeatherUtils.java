package com.arobs.weather;

import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

public class WeatherUtils {
	/**
	 * Transforma data calendaristica in Unix timestamp - secude de la 1970.
	 * @param referenceDate - data care urmeaza a fi transformata
	 * @return - secunde del al 1970 pina la data specificat 
	 */
	public static long getUnixTime(Date referenceDate) {
		Calendar referenceCalendar = Calendar.getInstance();
		referenceCalendar.setTime(referenceDate);
		return convert(referenceCalendar);
	}
	
	/**
	 * returneaza data curenta in forma de Unix timestamp 
	 * @return secunde de la 1970 pina la momentul curent. 
	 */
	public static long getUnixTime() {
		Calendar now = Calendar.getInstance();
		return convert(now);
	}

	private static long convert(Calendar referenceCalendar) {
		int year = referenceCalendar.get(Calendar.YEAR);
		int month = referenceCalendar.get(Calendar.MONTH);
		int day = referenceCalendar.get(Calendar.DATE);
		referenceCalendar.clear();
		referenceCalendar.set(year, month, day);
		Instant instant = Instant.ofEpochMilli(referenceCalendar.getTimeInMillis());
		return instant.getEpochSecond();
	}
}
