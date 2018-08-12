package com.arobs.weather.location;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;
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
import org.springframework.test.context.junit4.SpringRunner;

import com.arobs.entity.WeatherLocation;
import com.arobs.scheduler.WeatherLocationRepository;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@AutoConfigureWebClient 
public class WeatherLocationRepositoryTest {
	private static final Logger logger = LoggerFactory.getLogger(WeatherLocationRepositoryTest.class); 

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	public WeatherLocationRepository repository;
	
	@Test
	public void testEntityManager() {
		assertNotNull(entityManager);
	}
	
	@Test
	public void testFindOne() {
		Integer id = 617077;
		WeatherLocation location = repository.findOne(id);
		assertEquals("Raionul Edineţ", location.getName());
		assertEquals("MD", location.getCountryCode());
	}
	
	@Test
	public void testFindByCodeNameMD() {
		String countryCode = "MD";
		String name = "Raion";
		List<WeatherLocation> locations = repository.find(countryCode, name + "%");
		assertEquals(30, locations.size());
	}
	
	@Test
	public void testFindByCodeNameRO() {
		String countryCode = "RO";
		String name = "Judeţ";
		List<WeatherLocation> locations = repository.find(countryCode, name + "%");
		assertEquals(37, locations.size());
	}
	
	@Test
	public void testFindAllMdRo() {
		List<WeatherLocation> locations = repository.findAllMdRo();
		assertEquals(70, locations.size());
	}
	
	@Test
	@Ignore
	public void testDTOBinder() throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		File file = new File("R:\\digi-agro\\city.list.json");
		CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(List.class, WeatherLocationJson.class);
		List<WeatherLocationJson> locationsJson = objectMapper.readValue(file,  listType);
		DTOBinder binder = DTOBinderFactory.getBinder();
		List<WeatherLocation> locations = binder.bindFromBusinessObjectList(WeatherLocation.class, locationsJson);
		int count = 0;
		for (WeatherLocation location : locations) {
			if (location.getCountryCode().equalsIgnoreCase("md") || location.getCountryCode().equalsIgnoreCase("ro")) {
				repository.save(location);
				count++;
			}
		}
		logger.info("Au fost salvate {} articole", count);
	}
}
