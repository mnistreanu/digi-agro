package com.arobs.weather.provider;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.client.RestTemplate;

import com.arobs.weather.location.WeatherLocationJson;
import com.arobs.weather.snapshot.WeatherSnapshotJson;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

public class WeatherSnapshotProviderTest {
	private static final String LOCATION_JSON = "locations.json";
	private static final String weatherSnapshotCityUrl = "http://api.openweathermap.org/data/2.5/weather?id=%1$d&appid=%2$s";
	private static final String weatherSnapshotCoordUrl = "http://api.openweathermap.org/data/2.5/weather?lat=%1$f&lon=%2$f&appid=%3$s&units=metric";
	private static final String weatherSnapshotAppid="101db96830e0a1520f5274ed6c10b742";


	private RestTemplate restTemplate = new RestTemplate();
	
	@Test
	public void testRestTemplate() {
		assertNotNull(restTemplate);
	}

	@Test
	public void testReadSnapshotByCity() throws IOException {
		assertNotNull(restTemplate);
		Resource resource = new ClassPathResource(LOCATION_JSON);
		File file = resource.getFile(); 
		ObjectMapper objectMapper = new ObjectMapper();
		
		CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(List.class, WeatherLocationJson.class);
		List<WeatherLocationJson> locations = objectMapper.readValue(file,  listType);
		WeatherLocationJson testLocation = locations.get(0);
		String url = String.format(weatherSnapshotCityUrl, testLocation.getId(), weatherSnapshotAppid);
		WeatherSnapshotJson weatherSnapshotJson = restTemplate.getForObject(url, WeatherSnapshotJson.class);
		assertNotNull(weatherSnapshotJson);
	}

	@Test
	public void testReadSnapshotByCoord() throws IOException {
		assertNotNull(restTemplate);
		Resource resource = new ClassPathResource(LOCATION_JSON);
		File file = resource.getFile(); 
		ObjectMapper objectMapper = new ObjectMapper();
		
		CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(List.class, WeatherLocationJson.class);
		List<WeatherLocationJson> locations = objectMapper.readValue(file,  listType);
		WeatherLocationJson testLocation = locations.get(0);
		String url = String.format(weatherSnapshotCoordUrl, testLocation.getCoord().getLat(), testLocation.getCoord().getLon(), weatherSnapshotAppid);
		WeatherSnapshotJson weatherSnapshotJson = restTemplate.getForObject(url, WeatherSnapshotJson.class);
		assertNotNull(weatherSnapshotJson);
	}
}
