package com.arobs.weather;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.arobs.weather.provider.WeatherLocationProvider;
import com.arobs.weather.provider.WeatherSnapshotProvider;
import com.arobs.weather.snapshot.WeatherSnapshotJson;
import com.arobs.weather.snapshot.WeatherSnapshotModel;

@Component
public class WeatherScheduler {
	private static final Logger logger = LoggerFactory.getLogger(WeatherScheduler.class);
	
	@Value("${weather.snapshot.city.url}")
	private String weatherSnapshotCityUrl;
	
	@Value("${weather.snapshot.coord.url}")
	private String weatherSnapshotCoordUrl;
	
	@Value("${openweather.url}")
	private String openWeatherUrl;
	
	@Value("${openweather.appid}")
	private String openWeatherAppid;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private WeatherSnapshotProvider weatherSnapshotProvider;  
	
	@Autowired
	private WeatherLocationProvider weatherLocationProvider;  
	
//	@Scheduled(cron = "${weather.snapshot.cron}")
	public void synchronizeWeatherSnapshotsByCity() {
		int synchronizedRows = weatherSnapshotProvider.synchronizeWeatherSnapshotsByCity();
        logger.debug("Au fost sincronizate {} articole", synchronizedRows);
	}
	
//	@Scheduled(cron = "${weather.snapshot.cron}")
	public void synchronizeWeatherSnapshotsByCoord() {
		int synchronizedRows = weatherSnapshotProvider.synchronizeWeatherSnapshotsByCoord();
        logger.debug("Au fost sincronizate {} articole", synchronizedRows);
	}
	
}
