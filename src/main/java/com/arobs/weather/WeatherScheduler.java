package com.arobs.weather;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.arobs.weather.history.WeatherHistoryRepository;
import com.arobs.weather.provider.WeatherForecastProvider;
import com.arobs.weather.provider.WeatherSnapshotProvider;

@Component
public class WeatherScheduler {
	private static final Logger logger = LoggerFactory.getLogger(WeatherScheduler.class);
	
	@Autowired
	private WeatherSnapshotProvider weatherSnapshotProvider;  
	
	@Autowired
	private WeatherForecastProvider weatherForecastProvider;  
	
	@Autowired
	private WeatherHistoryRepository weatherHistoryRepository;  
	
	@Scheduled(cron = "${weather.snapshot.cron}")
	public void synchronizeWeatherSnapshotsByCity() {
        logger.debug("Start sincronizare Snapshot dupa ID localitate: {}", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		int synchronizedRows = weatherSnapshotProvider.synchronizeWeatherSnapshotsByCity();
        logger.debug("Au fost sincronizate {} articole", synchronizedRows);
	}
	
	@Scheduled(cron = "${weather.snapshot.cron}")
	public void synchronizeWeatherSnapshotsByCoord() {
        logger.debug("Start sincronizare Snapshot dupa coordonate localitate: {}", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		int synchronizedRows = weatherSnapshotProvider.synchronizeWeatherSnapshotsByCoord();
        logger.debug("Au fost sincronizate {} articole", synchronizedRows);
	}
	
	@Scheduled(cron = "${weather.history.cron}")
	public void synchronizeWeatherHistory() {
        logger.debug("Start sincronizare History: {}", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		int synchronizedRows = weatherHistoryRepository.synchronizeWeatherHistory();
        logger.debug("Au fost sincronizate {} articole", synchronizedRows);
	}
	
	@Scheduled(cron = "${weather.forecast.hour.cron}")
	public void synchronizeWeatherForecastsHour() {
        logger.debug("Start sincronizare Forecast 5/3: {}", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		int synchronizedRows = weatherForecastProvider.synchronizeWeatherForecastsHour();
        logger.debug("Au fost sincronizate {} articole", synchronizedRows);
	}
	
	@Scheduled(cron = "${weather.forecast.daily.cron}")
	public void synchronizeWeatherForecastsDaily() {
        logger.debug("Start sincronizare Forecast 16/1: {}", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		int synchronizedRows = weatherForecastProvider.synchronizeWeatherForecastsDaily();
        logger.debug("Au fost sincronizate {} articole", synchronizedRows);
	}
}
