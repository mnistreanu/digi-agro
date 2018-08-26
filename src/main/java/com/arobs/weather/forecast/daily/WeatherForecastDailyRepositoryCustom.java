package com.arobs.weather.forecast.daily;

import java.util.Date;

public interface WeatherForecastDailyRepositoryCustom {

	/**
	 * Transfera observatiile meteo de pe data specificata din Snapshot in History. 
	 * @param referenceDate - data specificata
	 * @return numarul de articole transferate.
	 */
	int insertFromForecastHour(Date referenceDate);
}
