package com.arobs.weather;

import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

	/**
	 * 
	 * @param referenceDate
	 * @return
	 */
	public static Map<Integer, Integer> splitDate(Date referenceDate) {
		Calendar referenceCalendar = Calendar.getInstance();
		referenceCalendar.setTime(referenceDate);
		Map<Integer, Integer> retValue =  new HashMap<>();
		retValue.put(Calendar.YEAR, referenceCalendar.get(Calendar.YEAR));
		retValue.put(Calendar.MONTH, referenceCalendar.get(Calendar.MONTH));
		retValue.put(Calendar.DATE, referenceCalendar.get(Calendar.DATE));
		retValue.put(Calendar.HOUR, referenceCalendar.get(Calendar.HOUR));
		retValue.put(Calendar.MINUTE, referenceCalendar.get(Calendar.MINUTE));
		retValue.put(Calendar.SECOND, referenceCalendar.get(Calendar.SECOND)); 
		return retValue;
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
