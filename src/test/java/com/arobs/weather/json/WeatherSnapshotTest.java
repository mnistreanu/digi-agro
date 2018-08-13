package com.arobs.weather.json;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.jdto.DTOBinder;
import org.jdto.DTOBinderFactory;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.arobs.weather.entity.WeatherSnapshot;
import com.arobs.weather.snapshot.WeatherSnapshotJson;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class WeatherSnapshotTest {
	private static final String SNAPSHOT_JSON = "snapshot.json";
	private static final Logger logger = LoggerFactory.getLogger(WeatherSnapshotTest.class); 

	@Test
	public void testJsonObject() throws JsonParseException, JsonMappingException, IOException {
		WeatherSnapshotJson weatherSnapshotJson = getJsonObject(SNAPSHOT_JSON, WeatherSnapshotJson.class);
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
	public void testBinding() throws JsonParseException, JsonMappingException, IOException {
		WeatherSnapshotJson snapshotJson = getJsonObject(SNAPSHOT_JSON, WeatherSnapshotJson.class);
		DTOBinder binder = DTOBinderFactory.getBinder();
		WeatherSnapshot snapshotEntity = binder.bindFromBusinessObject(WeatherSnapshot.class, snapshotJson);
		logger.info("location. Lon(): {}, Lat(): {}, Main: {}", snapshotEntity.getLon(), snapshotEntity.getLat(), snapshotEntity.getMain());
		assertEquals(snapshotJson.getId(), snapshotEntity.getOpenweatherId());
		assertEquals(snapshotJson.getName(), snapshotEntity.getName());
		assertEquals(snapshotJson.getCod(), snapshotEntity.getCod());
		assertEquals(snapshotJson.getDt(), snapshotEntity.getDt());
		assertEquals(snapshotJson.getBase(), snapshotEntity.getBase());
		assertEquals(snapshotJson.getRain().get3h(), snapshotEntity.getRain3h());
		assertEquals(snapshotJson.getCoord().getLon(), snapshotEntity.getLon());
		assertEquals(snapshotJson.getCoord().getLat(), snapshotEntity.getLat());
		assertEquals(snapshotJson.getWeather().get(0).getId(), snapshotEntity.getWeatherId());
		assertEquals(snapshotJson.getWeather().get(0).getMain(), snapshotEntity.getMain());
		assertEquals(snapshotJson.getWeather().get(0).getDescription(), snapshotEntity.getDescription());
		assertEquals(snapshotJson.getWeather().get(0).getIcon(), snapshotEntity.getIcon());
		assertEquals(snapshotJson.getMain().getTemp(), snapshotEntity.getTemp());
		assertEquals(snapshotJson.getMain().getPressure(), snapshotEntity.getPressure());
		assertEquals(snapshotJson.getMain().getHumidity(), snapshotEntity.getHumidity());
		assertEquals(snapshotJson.getMain().getTempMin(), snapshotEntity.getTempMin());
		assertEquals(snapshotJson.getMain().getTempMax(), snapshotEntity.getTempMax());
		assertEquals(snapshotJson.getMain().getSeaLevel(), snapshotEntity.getSeaLevel());
		assertEquals(snapshotJson.getMain().getGrndLevel(), snapshotEntity.getGrndLevel());
		assertEquals(snapshotJson.getWind().getSpeed(), snapshotEntity.getSpeed());
		assertEquals(snapshotJson.getWind().getDeg(), snapshotEntity.getDeg());
		assertEquals(snapshotJson.getClouds().getAll(), snapshotEntity.getClouds());
		assertEquals(snapshotJson.getSys().getMessage(), snapshotEntity.getMessage());
		assertEquals(snapshotJson.getSys().getCountry(), snapshotEntity.getCountryCode());
		assertEquals(snapshotJson.getSys().getSunrise(), snapshotEntity.getSunrise());
		assertEquals(snapshotJson.getSys().getSunset(), snapshotEntity.getSunset());
	}
	
	private <T> T getJsonObject(String jsonFileName, Class<T> clazz) throws IOException, JsonParseException, JsonMappingException {
		Resource resource = new ClassPathResource(jsonFileName);
		File file = resource.getFile(); 
		ObjectMapper objectMapper = new ObjectMapper();
		T weatherSnapshotJson = objectMapper.readValue(file,  clazz);
		return weatherSnapshotJson;
	}
}
