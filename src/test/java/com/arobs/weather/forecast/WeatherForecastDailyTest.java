package com.arobs.weather.forecast;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;

import org.jdto.DTOBinder;
import org.jdto.DTOBinderFactory;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.arobs.scheduler.weather.WeatherDailyForecast;
import com.arobs.scheduler.weather.forecast.daily.WeatherDailyForecastJson;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class WeatherForecastDailyTest {
	private static final Logger logger = LoggerFactory.getLogger(WeatherForecastDailyTest.class); 
	private static final String FORECAST_DAILY_JSON = "forecast.daily.json";
	private DTOBinder binder = DTOBinderFactory.getBinder();

	@Test
	public void testJsonObject() throws JsonParseException, JsonMappingException, IOException {
		WeatherDailyForecastJson weatherDailyForecast = getJsonObject();
		logger.info("Au fost salvate {} articole", weatherDailyForecast);
		assertEquals(weatherDailyForecast.getCnt().intValue(), weatherDailyForecast.getList().size());
	}

	@Test
	public void testBinding() throws JsonParseException, JsonMappingException, IOException {
		WeatherDailyForecastJson dailyForecast = getJsonObject();
		WeatherDailyForecast forecasts = binder.bindFromBusinessObject(WeatherDailyForecast.class, dailyForecast);
		assertNotNull(forecasts);
		logger.info("Au fost salvate {} articole", forecasts);
	}

	private WeatherDailyForecastJson getJsonObject() throws IOException, JsonParseException, JsonMappingException {
		Resource resource = new ClassPathResource(FORECAST_DAILY_JSON);
		File file = resource.getFile(); 
		ObjectMapper objectMapper = new ObjectMapper();
		
		WeatherDailyForecastJson weatherDailyForecast = objectMapper.readValue(file,  WeatherDailyForecastJson.class);
		return weatherDailyForecast;
	}
	
}
