package com.arobs.weather.json;

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

import com.arobs.weather.entity.WeatherForecastDaily;
import com.arobs.weather.forecast.daily.WeatherForecastDailyJson;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class WeatherForecastDailyTest {
	private static final Logger logger = LoggerFactory.getLogger(WeatherForecastDailyTest.class); 
	private static final String FORECAST_DAILY_JSON = "forecast.daily.json";
	private DTOBinder binder = DTOBinderFactory.getBinder();

	@Test
	public void testJsonObject() throws JsonParseException, JsonMappingException, IOException {
		WeatherForecastDailyJson weatherDailyForecast = getJsonObject();
		logger.info("Au fost salvate {} articole", weatherDailyForecast);
		assertEquals(weatherDailyForecast.getCnt().intValue(), weatherDailyForecast.getList().size());
	}

	@Test
	public void testBinding() throws JsonParseException, JsonMappingException, IOException {
		WeatherForecastDailyJson dailyForecast = getJsonObject();
		WeatherForecastDaily forecasts = binder.bindFromBusinessObject(WeatherForecastDaily.class, dailyForecast);
		assertNotNull(forecasts);
		logger.info("Au fost salvate {} articole", forecasts);
	}

	private WeatherForecastDailyJson getJsonObject() throws IOException, JsonParseException, JsonMappingException {
		Resource resource = new ClassPathResource(FORECAST_DAILY_JSON);
		File file = resource.getFile(); 
		ObjectMapper objectMapper = new ObjectMapper();
		
		WeatherForecastDailyJson weatherDailyForecast = objectMapper.readValue(file,  WeatherForecastDailyJson.class);
		return weatherDailyForecast;
	}
	
}
