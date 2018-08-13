package com.arobs.weather.json;

import static org.junit.Assert.assertEquals;

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

import com.arobs.weather.entity.WeatherLocation;
import com.arobs.weather.location.WeatherLocationJson;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

public class WeatherLocationTest {
	private static final Logger logger = LoggerFactory.getLogger(WeatherLocationTest.class); 
	private static final String FORECAST_DAILY_JSON = "locations.json";
	private DTOBinder binder = DTOBinderFactory.getBinder();

	@Test
	public void testJsonObject() throws JsonParseException, JsonMappingException, IOException {
		List<WeatherLocationJson> locations = getJsonObject();
		logger.info("Location. {} articole", locations.size());
		assertEquals(3, locations.size());
		WeatherLocationJson location = locations.get(0);
		assertEquals(Integer.valueOf(707860), location.getId());
		assertEquals("Hurzuf", location.getName());
		assertEquals("UA", location.getCountry());
		assertEquals(Double.valueOf(34.283333), location.getCoord().getLon());
		assertEquals(Double.valueOf(44.549999), location.getCoord().getLat());
	}

	@Test
	public void testBinding() throws JsonParseException, JsonMappingException, IOException {
		List<WeatherLocationJson> locationsJson = getJsonObject();
		
		List<WeatherLocation> locationsEntity = binder.bindFromBusinessObjectList(WeatherLocation.class, locationsJson);
		assertEquals(3, locationsEntity.size());
		assertEquals(locationsJson.size(), locationsEntity.size());
		WeatherLocationJson locationJson = locationsJson.get(0);
		WeatherLocation locationEntity = locationsEntity.get(0);
		assertEquals(locationJson.getId(), locationEntity.getId());
		assertEquals(locationJson.getCountry(), locationEntity.getCountryCode());
		assertEquals(locationJson.getName(), locationEntity.getName());
		assertEquals(locationJson.getCoord().getLon(), locationEntity.getLon());
		assertEquals(locationJson.getCoord().getLat(), locationEntity.getLat());
	}
	
	private List<WeatherLocationJson> getJsonObject() throws IOException, JsonParseException, JsonMappingException {
		Resource resource = new ClassPathResource(FORECAST_DAILY_JSON);
		File file = resource.getFile(); 
		ObjectMapper objectMapper = new ObjectMapper();
		
		CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(List.class, WeatherLocationJson.class);
		List<WeatherLocationJson> locations = objectMapper.readValue(file,  listType);
		return locations;
	}
}
