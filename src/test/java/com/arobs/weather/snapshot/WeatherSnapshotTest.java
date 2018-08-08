package com.arobs.weather.snapshot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.util.Date;
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
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.arobs.entity.WeatherSnapshot;
import com.arobs.scheduler.weather.WeatherSnapshotJson;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@AutoConfigureWebClient 
public class WeatherSnapshotTest {
	private static final Logger logger = LoggerFactory.getLogger(WeatherSnapshotTest.class); 

	@Autowired
	private TestEntityManager entityManager;

	@Test
	public void testEntityManager() {
		assertNotNull(entityManager);
	}

	@Test
	@Rollback(false)
	public void testListMachinery() {
		Query query = entityManager.getEntityManager().createQuery("SELECT weatherSnapshot FROM WeatherSnapshot weatherSnapshot");
		@SuppressWarnings("unchecked")
		List<WeatherSnapshot> list = query.getResultList();
		assertNotNull(list);
	}

	@Test
	public void testReadJson() throws JsonParseException, JsonMappingException, IOException {
		Resource resource = new ClassPathResource("snapshot.test.json");
		File file = resource.getFile(); 
		ObjectMapper objectMapper = new ObjectMapper();
		WeatherSnapshotJson weatherSnapshotJson = objectMapper.readValue(file,  WeatherSnapshotJson.class);
		logger.info("Snapshot. ID: {}, Name: {}", weatherSnapshotJson.getId(), weatherSnapshotJson.getName());
		assertEquals(Integer.valueOf(618510), weatherSnapshotJson.getId());
		assertEquals("Briceni", weatherSnapshotJson.getName());
		assertEquals(Integer.valueOf(200), weatherSnapshotJson.getCod());
		assertEquals(new Date(1533684016L * 1000), weatherSnapshotJson.getDt());
		assertEquals("stations", weatherSnapshotJson.getBase());

		assertEquals(Double.valueOf(27.1), weatherSnapshotJson.getCoord().getLon());
		assertEquals(Double.valueOf(48.31), weatherSnapshotJson.getCoord().getLat());
		
		assertEquals(Integer.valueOf(800), weatherSnapshotJson.getWeather().get(0).getId());
		assertEquals("Clear", weatherSnapshotJson.getWeather().get(0).getMain());
		assertEquals("clear sky", weatherSnapshotJson.getWeather().get(0).getDescription());
		assertEquals("01n", weatherSnapshotJson.getWeather().get(0).getIcon());
		
		assertEquals(Double.valueOf(287.832), weatherSnapshotJson.getMain().getTemp());
		assertEquals(Double.valueOf(1003.99), weatherSnapshotJson.getMain().getPressure());
		assertEquals(Integer.valueOf(85), weatherSnapshotJson.getMain().getHumidity());
		assertEquals(Double.valueOf(287.832), weatherSnapshotJson.getMain().getTempMin());
		assertEquals(Double.valueOf(287.832), weatherSnapshotJson.getMain().getTempMax());
		assertEquals(Double.valueOf(1033.01), weatherSnapshotJson.getMain().getSeaLevel());
		assertEquals(Double.valueOf(1003.99), weatherSnapshotJson.getMain().getGrndLevel());

		
		assertEquals(Double.valueOf(0.91), weatherSnapshotJson.getWind().getSpeed());
		assertEquals(Double.valueOf(264.002), weatherSnapshotJson.getWind().getDeg());
		
		assertEquals(Integer.valueOf(0), weatherSnapshotJson.getClouds().getAll());
		
		assertEquals(Double.valueOf(0.0332), weatherSnapshotJson.getSys().getMessage());
		assertEquals("MD", weatherSnapshotJson.getSys().getCountry());
		assertEquals(new Date(1533610542L * 1000), weatherSnapshotJson.getSys().getSunrise());
		assertEquals(new Date(1533663475L * 1000), weatherSnapshotJson.getSys().getSunset());
	}

	@Test
	public void testDTOBinder() throws JsonParseException, JsonMappingException, IOException {
		Resource resource = new ClassPathResource("snapshot.test.json");
		File file = resource.getFile(); 
		ObjectMapper objectMapper = new ObjectMapper();
		WeatherSnapshotJson snapshotJson = objectMapper.readValue(file,  WeatherSnapshotJson.class);
		DTOBinder binder = DTOBinderFactory.getBinder();
		WeatherSnapshot snapshot = binder.bindFromBusinessObject(WeatherSnapshot.class, snapshotJson);
		logger.info("location. Lon(): {}, Lat(): {}, Country: {}", snapshot.getLon(), snapshot.getLat(), snapshot.getCountryCode());
//		assertEquals(snapshotJson.getId(), snapshot.getId());
		assertEquals(snapshotJson.getSys().getCountry(), snapshot.getCountryCode());
		assertEquals(snapshotJson.getName(), snapshot.getName());
		assertEquals(snapshotJson.getCoord().getLon(), snapshot.getLon());
		assertEquals(snapshotJson.getCoord().getLat(), snapshot.getLat());
	}
}
