package com.arobs.weather.forecast.daily;

import java.util.Date;

public interface WeatherForecastDailyRepositoryCustom {

	/**
	 * Agregheaza previziunile meteo si le transfera din Forecast 5/3 in Forecast 16/1. 
	 * @return numarul de articole transferate.
	 */
	int synchronizeForecastHourDaily(Date referenceDate);
}
