package com.arobs.weather.history;

public interface WeatherHistoryRepositoryCustom {

	/**
	 * Transfera observatiile meteo de pe data peredenta date curente din Snapshot in History. 
	 * @return numarul de articole transferate.
	 */
	int synchronizeWeatherHistory();
}
