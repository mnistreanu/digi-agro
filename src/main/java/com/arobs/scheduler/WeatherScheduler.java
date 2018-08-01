package com.arobs.scheduler;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherScheduler {
	private static final Logger logger = LoggerFactory.getLogger(WeatherScheduler.class);
	
	@Value("${weather.url}")
	private String weatherUrl;
	
	@Value("${weather.lat}")
	private String weatherLat;
	
	@Value("${weather.lon}")
	private String weatherLon;
	
	@Value("${weather.appid}")
	private String weatherAppid;
	
	@Value("${weather.units}")
	private String weatherUnits;
	
	@Scheduled(cron = "${cron.weather}")
	public void readWeather() {
		String url = String.format(weatherUrl, Double.valueOf(weatherLat), Double.valueOf(weatherLon), weatherAppid, weatherUnits);
		logger.info("Este ora: {}, URL: {}", new Date(), url);
		RestTemplate restTemplate = new RestTemplate();
		WeatherJson weather = restTemplate.getForObject(url, WeatherJson.class);
        logger.info(weather.toString());
    
	}
}
