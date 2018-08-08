package com.arobs.weather.location;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.persistence.Query;

import org.jdto.DTOBinder;
import org.jdto.DTOBinderFactory;
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

import com.arobs.entity.WeatherLocation;
import com.arobs.scheduler.weather.WeatherLocationJson;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@AutoConfigureWebClient 
public class WeatherLocationTest {
	private static final Logger logger = LoggerFactory.getLogger(WeatherLocationTest.class); 

	@Autowired
	private TestEntityManager entityManager;

	@Test
	public void testEntityManager() {
		assertNotNull(entityManager);
	}

	@Test
	@Rollback(false)
	public void testListMachinery() {
		Query query = entityManager.getEntityManager().createQuery("SELECT weatherLocation FROM WeatherLocation weatherLocation");
		@SuppressWarnings("unchecked")
		List<WeatherLocation> list = query.getResultList();
		assertNotNull(list);
	}

	@Test
	public void testReadJson() throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		File file = new File("R:\\digi-agro\\city.test_list.json");
		CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(List.class, WeatherLocationJson.class);
		List<WeatherLocationJson> cities = objectMapper.readValue(file,  listType);
		logger.info("location. Lon(): {}, Country: {}", cities.get(0).getCoord().getLon(), cities.get(0).getCountry());
		assertEquals(3, cities.size());
	}

	@Test
	public void testDTOBinder() throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		File file = new File("R:\\digi-agro\\city.test_list.json");
		CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(List.class, WeatherLocationJson.class);
		List<WeatherLocationJson> cities = objectMapper.readValue(file,  listType);
		DTOBinder binder = DTOBinderFactory.getBinder();
		List<WeatherLocation> locations = binder.bindFromBusinessObjectList(WeatherLocation.class, cities);
		assertTrue(cities.size() > 0);
		assertEquals(cities.size(), locations.size());
		WeatherLocationJson city = cities.get(0);
		WeatherLocation location = locations.get(0);
		assertEquals(city.getId(), location.getId());
		assertEquals(city.getCountry(), location.getCountryCode());
		assertEquals(city.getName(), location.getName());
		assertEquals(city.getCoord().getLon(), location.getLon());
		assertEquals(city.getCoord().getLat(), location.getLat());
		logger.info("location. Lon(): {}, Lat(): {}, Country: {}", locations.get(0).getLon(), locations.get(0).getLat(), locations.get(0).getCountryCode());
	}
}
