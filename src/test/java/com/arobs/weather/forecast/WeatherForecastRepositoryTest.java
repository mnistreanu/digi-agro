package com.arobs.weather.forecast;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdto.DTOBinder;
import org.jdto.DTOBinderFactory;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.arobs.entity.WeatherForecast;
import com.arobs.entity.WeatherLocation;
import com.arobs.scheduler.WeatherForecastRepository;
import com.arobs.scheduler.weather.WeatherForecastJson;
import com.arobs.scheduler.weather.WeatherLocationJson;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@AutoConfigureWebClient 
public class WeatherForecastRepositoryTest {
	private static final Logger logger = LoggerFactory.getLogger(WeatherForecastRepositoryTest.class); 

	@Autowired
	private DTOBinder binder;

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	public WeatherForecastRepository repository;
	
	@Test
	public void testEntityManager() {
		assertNotNull(entityManager);
	}
	
	@Test
	public void testFindAll() {
		List<WeatherForecast> weatherForecasts = repository.findAll();
		assertNotNull(weatherForecasts);
	}
	
	@Test
	public void testFindOne() {
		Integer id = 617077;
		WeatherForecast location = repository.findOne(id);
		assertEquals("Raionul Edineţ", location.getName());
	}
	
	@Test
	@Ignore
	public void testDTOBinder() throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		File file = new File("R:\\digi-agro\\city.list.json");
		CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(List.class, WeatherLocationJson.class);
		List<WeatherLocationJson> weatherLocations = objectMapper.readValue(file,  listType);
		DTOBinder binder = DTOBinderFactory.getBinder();
		List<WeatherLocation> locations = binder.bindFromBusinessObjectList(WeatherLocation.class, weatherLocations);
		assertNotNull(locations);
		int count = 0;
//		for (WeatherLocation location : locations) {
//			if (location.getCountryCode().equalsIgnoreCase("md") || location.getCountryCode().equalsIgnoreCase("ro")) {
//				repository.save(location);
//				count++;
//			}
//		}
		logger.info("Au fost salvate {} articole", count);
	}
	
	@Test
	@Rollback(false)
	public void testBinder() throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		WeatherForecastJson weatherForecast = objectMapper.readValue(locationContent,  WeatherForecastJson.class);
		assertNotNull(weatherForecast);
		List<WeatherForecastJson> weatherForecasts = new ArrayList<>();
		weatherForecasts.add(weatherForecast);
		
		List<WeatherForecast> results = binder.bindFromBusinessObjectList(WeatherForecast.class, weatherForecasts);
		assertNotNull(results);
		WeatherForecast result = binder.bindFromBusinessObject(WeatherForecast.class, weatherForecast);
		assertNotNull(result);
		repository.deleteAll();
		repository.save(results);
	}
	
	private String locationContent = "{" + 
			"  \"coord\": {" + 
			"    \"lon\": 29.3," + 
			"    \"lat\": 47.15" + 
			"  }," + 
			"  \"weather\": [" + 
			"    {" + 
			"      \"id\": 802," + 
			"      \"main\": \"Clouds\"," + 
			"      \"description\": \"scattered clouds\"," + 
			"      \"icon\": \"03d\"" + 
			"    }" + 
			"  ]," + 
			"  \"base\": \"stations\"," + 
			"  \"main\": {" + 
			"    \"temp\": 303.15," + 
			"    \"pressure\": 1015," + 
			"    \"humidity\": 40," + 
			"    \"temp_min\": 303.15," + 
			"    \"temp_max\": 303.15" + 
			"  }," + 
			"  \"visibility\": 10000," + 
			"  \"wind\": {" + 
			"    \"speed\": 3.1," + 
			"    \"deg\": 310" + 
			"  }," + 
			"  \"clouds\": {" + 
			"    \"all\": 40" + 
			"  }," + 
			"  \"dt\": 1533391200," + 
			"  \"sys\": {" + 
			"    \"type\": 1," + 
			"    \"id\": 6086," + 
			"    \"message\": 0.0026," + 
			"    \"country\": \"MD\"," + 
			"    \"sunrise\": 1533350754," + 
			"    \"sunset\": 1533403858" + 
			"  }," + 
			"  \"id\": 618234," + 
			"  \"name\": \"Grigoriopol\"," + 
			"  \"cod\": 200" + 
			"}";

}
