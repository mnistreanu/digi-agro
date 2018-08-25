package com.arobs.weather.history;

import java.util.Date;

public interface WeatherHistoryRepositoryCustom {

	/**
	 * Transfera observatiile meteo de pe data specificata din Snapshot in History. 
	 * @param referenceDate - data specificata
	 * @return numarul de articole transferate.
	 */
	int insertFromSnapshot(Date referenceDate);
}
