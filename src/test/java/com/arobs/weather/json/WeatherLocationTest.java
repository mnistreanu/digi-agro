package com.arobs.weather.json;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

import com.arobs.entity.WeatherLocation;
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
		logger.info("location. Lon(): {}, Country: {}", locations.get(0).getCoord().getLon(), locations.get(0).getCountry());
		assertEquals(3, locations.size());
	}

	@Test
	public void testBinding() throws JsonParseException, JsonMappingException, IOException {
		List<WeatherLocationJson> locations = getJsonObject();
		
		List<WeatherLocation> locationEntities = binder.bindFromBusinessObjectList(WeatherLocation.class, locations);
		assertTrue(locations.size() > 0);
		assertEquals(locations.size(), locationEntities.size());
		WeatherLocationJson city = locations.get(0);
		WeatherLocation location = locationEntities.get(0);
		assertEquals(city.getId(), location.getId());
		assertEquals(city.getCountry(), location.getCountryCode());
		assertEquals(city.getName(), location.getName());
		assertEquals(city.getCoord().getLon(), location.getLon());
		assertEquals(city.getCoord().getLat(), location.getLat());
		logger.info("location. Lon(): {}, Lat(): {}, Country: {}", locationEntities.get(0).getLon(), locationEntities.get(0).getLat(), locationEntities.get(0).getCountryCode());
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
