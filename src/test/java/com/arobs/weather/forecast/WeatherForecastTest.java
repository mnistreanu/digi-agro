package com.arobs.weather.forecast;

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

import com.arobs.weather.entity.WeatherForecastHour;
import com.arobs.weather.entity.WeatherLocation;
import com.arobs.weather.location.WeatherLocationJson;
import com.arobs.weather.snapshot.WeatherSnapshotJson;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

public class WeatherForecastTest {
	private static final String LOCATIONS_JSON = "locations.json";
	private static final String SNAPSHOT_JSON = "snapshot.json";
	private static final Logger logger = LoggerFactory.getLogger(WeatherForecastRepositoryTest.class); 
	private DTOBinder binder = DTOBinderFactory.getBinder();

	@Test
	public void testLocations() throws JsonParseException, JsonMappingException, IOException {
		Resource resource = new ClassPathResource(LOCATIONS_JSON);
		File file = resource.getFile(); 
		ObjectMapper objectMapper = new ObjectMapper();
		CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(List.class, WeatherLocationJson.class);
		List<WeatherLocationJson> weatherLocations = objectMapper.readValue(file,  listType);
		List<WeatherLocation> locations = binder.bindFromBusinessObjectList(WeatherLocation.class, weatherLocations);
		assertNotNull(locations);
		logger.info("Au fost salvate {} articole", locations.size());
	}
	
	@Test
	public void testForecasts() throws JsonParseException, JsonMappingException, IOException {
		Resource resource = new ClassPathResource(SNAPSHOT_JSON);
		File file = resource.getFile(); 
		ObjectMapper objectMapper = new ObjectMapper();
		
		WeatherSnapshotJson weatherForecasts = objectMapper.readValue(file,  WeatherSnapshotJson.class);

		
		WeatherForecastHour forecasts = binder.bindFromBusinessObject(WeatherForecastHour.class, weatherForecasts);
		assertNotNull(forecasts);
		logger.info("Au fost salvate {} articole", forecasts);
		logger.info("Au fost salvate {} articole", weatherForecasts);
	}
	
}
