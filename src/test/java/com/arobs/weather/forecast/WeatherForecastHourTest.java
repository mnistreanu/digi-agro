package com.arobs.weather.forecast;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jdto.DTOBinder;
import org.jdto.DTOBinderFactory;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.arobs.entity.WeatherForecast;
import com.arobs.entity.WeatherLocation;
import com.arobs.scheduler.weather.WeatherSnapshotJson;
import com.arobs.scheduler.weather.WeatherLocationJson;
import com.arobs.scheduler.weather.forecast.hour.WeatherHourForecast;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

public class WeatherForecastHourTest {
	private static final Logger logger = LoggerFactory.getLogger(WeatherForecastRepositoryTest.class); 
	private DTOBinder binder = DTOBinderFactory.getBinder();

	@Test
	public void testHourForecasts() throws JsonParseException, JsonMappingException, IOException {
		Resource resource = new ClassPathResource("forecast.hour.json");
		File file = resource.getFile(); 
		ObjectMapper objectMapper = new ObjectMapper();
		
		WeatherHourForecast weatherHourForecast = objectMapper.readValue(file,  WeatherHourForecast.class);
		logger.info("Au fost salvate {} articole", weatherHourForecast);
		assertEquals(weatherHourForecast.getCnt().intValue(), weatherHourForecast.getList().size());
		
//		WeatherForecast forecasts = binder.bindFromBusinessObject(WeatherForecast.class, weatherForecasts);
//		assertNotNull(forecasts);
//		logger.info("Au fost salvate {} articole", forecasts);
	}
	
}
