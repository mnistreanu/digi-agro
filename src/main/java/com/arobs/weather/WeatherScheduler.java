package com.arobs.weather;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.arobs.weather.provider.WeatherSnapshotProvider;

@Component
public class WeatherScheduler {
	private static final Logger logger = LoggerFactory.getLogger(WeatherScheduler.class);
	
	@Autowired
	private WeatherSnapshotProvider weatherSnapshotProvider;  
	
	@Scheduled(cron = "${weather.snapshot.cron}")
	public void synchronizeWeatherSnapshotsByCity() {
		int synchronizedRows = weatherSnapshotProvider.synchronizeWeatherSnapshotsByCity();
        logger.debug("Au fost sincronizate {} articole", synchronizedRows);
	}
	
	@Scheduled(cron = "${weather.snapshot.cron}")
	public void synchronizeWeatherSnapshotsByCoord() {
		int synchronizedRows = weatherSnapshotProvider.synchronizeWeatherSnapshotsByCoord();
        logger.debug("Au fost sincronizate {} articole", synchronizedRows);
	}
	
}
