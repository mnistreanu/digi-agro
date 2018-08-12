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

import com.arobs.entity.WeatherForecast;
import com.arobs.weather.forecast.WeatherForecastRepositoryTest;
import com.arobs.weather.forecast.hour.WeatherForecastHourJson;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class WeatherForecastHourTest {
	private static final String FORECAST_HOUR_JSON = "forecast.hour.json";
	private static final Logger logger = LoggerFactory.getLogger(WeatherForecastRepositoryTest.class); 
	private DTOBinder binder = DTOBinderFactory.getBinder();

	@Test
	public void testJsonObject() throws JsonParseException, JsonMappingException, IOException {
		WeatherForecastHourJson weatherHourForecast = getJsonObject();
		logger.info("Au fost salvate {} articole", weatherHourForecast);
		assertEquals(weatherHourForecast.getCnt().intValue(), weatherHourForecast.getList().size());
	}

	@Test
	public void testBinding() throws JsonParseException, JsonMappingException, IOException {
		WeatherForecastHourJson weatherHourForecast = getJsonObject();
		
		WeatherForecast forecasts = binder.bindFromBusinessObject(WeatherForecast.class, weatherHourForecast);
		assertNotNull(forecasts);
		logger.info("Au fost salvate {} articole", forecasts);
	}

	private WeatherForecastHourJson getJsonObject() throws IOException, JsonParseException, JsonMappingException {
		Resource resource = new ClassPathResource(FORECAST_HOUR_JSON);
		File file = resource.getFile(); 
		ObjectMapper objectMapper = new ObjectMapper();
		
		WeatherForecastHourJson weatherHourForecast = objectMapper.readValue(file,  WeatherForecastHourJson.class);
		return weatherHourForecast;
	}
	
}
