package com.arobs.weather.json;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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

import com.arobs.weather.entity.ForecastHourItem;
import com.arobs.weather.entity.WeatherForecastHour;
import com.arobs.weather.forecast.WeatherForecastRepositoryTest;
import com.arobs.weather.forecast.hour.ForecastItemJson;
import com.arobs.weather.forecast.hour.WeatherForecastHourJson;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class WeatherForecastHourTest {
	private static final String FORECAST_HOUR_JSON = "forecast.hour.json";
	private static final Logger logger = LoggerFactory.getLogger(WeatherForecastRepositoryTest.class); 
	private DTOBinder binder = DTOBinderFactory.getBinder();

	@Test
	public void testJsonObject() throws JsonParseException, JsonMappingException, IOException {
		WeatherForecastHourJson forecastHourJson = getJsonObject();
		logger.info("Au fost citite {} articole", forecastHourJson.getCnt());
		assertEquals(forecastHourJson.getCnt().intValue(), forecastHourJson.getList().size());
		
		assertEquals("200", forecastHourJson.getCod());
		assertEquals(Double.valueOf(0.0022), forecastHourJson.getMessage());
		assertEquals(Integer.valueOf(2), forecastHourJson.getCnt());
		
		ForecastItemJson forcastItem = forecastHourJson.getList().get(0);
		
		assertEquals(new Date(1534442400 * 1000L), forcastItem.getDt());
		assertEquals("2018-08-16 18:00:00", forcastItem.getDtTxt());
		assertEquals(Double.valueOf(296.105), forcastItem.getMain().getTemp());
		assertEquals(Double.valueOf(296.105), forcastItem.getMain().getTempMin());
		assertEquals(Double.valueOf(296.105), forcastItem.getMain().getTempMax());
		assertEquals(Double.valueOf(1009.78), forcastItem.getMain().getPressure());
		assertEquals(Double.valueOf(1029.11), forcastItem.getMain().getSeaLevel());
		assertEquals(Double.valueOf(1009.78), forcastItem.getMain().getGrndLevel());
		assertEquals(Integer.valueOf(54), forcastItem.getMain().getHumidity());
		assertEquals(Integer.valueOf(0), forcastItem.getMain().getTempKf());
		
		assertEquals(Integer.valueOf(500), forcastItem.getWeather().get(0).getId());
		assertEquals("Rain", forcastItem.getWeather().get(0).getMain());
		assertEquals("light rain", forcastItem.getWeather().get(0).getDescription());
		assertEquals("10n", forcastItem.getWeather().get(0).getIcon());

		assertEquals(Integer.valueOf(76), forcastItem.getClouds().getAll());
		
		assertEquals(Double.valueOf(3.06), forcastItem.getWind().getSpeed());
		assertEquals(Double.valueOf(278.508), forcastItem.getWind().getDeg());
		assertEquals(Double.valueOf(0.09), forcastItem.getRain().get3h());
		assertEquals("n", forcastItem.getSys().getPod());
		
		assertEquals(Integer.valueOf(524901), forecastHourJson.getCity().getId());
		assertEquals("Moscow", forecastHourJson.getCity().getName());
		assertEquals(Double.valueOf(55.7522), forecastHourJson.getCity().getCoord().getLat());
		assertEquals(Double.valueOf(37.6156), forecastHourJson.getCity().getCoord().getLon());
		assertEquals("RU", forecastHourJson.getCity().getCountry());
	}

	@Test
	public void testBinding() throws JsonParseException, JsonMappingException, IOException {
		WeatherForecastHourJson forecastHourJson = getJsonObject();
		WeatherForecastHour forecastHourEntity = binder.bindFromBusinessObject(WeatherForecastHour.class, forecastHourJson);
		assertNotNull(forecastHourEntity);
		
		assertEquals(forecastHourJson.getCod(), forecastHourEntity.getCode());
		assertEquals(forecastHourJson.getMessage(), forecastHourEntity.getMessage());
		assertEquals(forecastHourJson.getCnt(), forecastHourEntity.getCnt());
		for (int i=0; i<forecastHourJson.getList().size(); i++) {
			ForecastHourItem forecastHourItem = forecastHourEntity.getForecastItems().get(i);
			ForecastItemJson forecastJsonItem = forecastHourJson.getList().get(i);

			assertEquals(forecastJsonItem.getDt(), forecastHourItem.getDt());
			assertEquals(forecastJsonItem.getDtTxt(), forecastHourItem.getDtTxt());
			
			assertEquals(forecastJsonItem.getMain().getTemp(), forecastHourItem.getTemp());
			assertEquals(forecastJsonItem.getMain().getTempMin(), forecastHourItem.getTempMin());
			assertEquals(forecastJsonItem.getMain().getTempMax(), forecastHourItem.getTempMax());
			assertEquals(forecastJsonItem.getMain().getPressure(), forecastHourItem.getPressure());
			assertEquals(forecastJsonItem.getMain().getSeaLevel(), forecastHourItem.getSeaLevel());
			assertEquals(forecastJsonItem.getMain().getGrndLevel(), forecastHourItem.getGrndLevel());
			assertEquals(forecastJsonItem.getMain().getHumidity(), forecastHourItem.getHumidity());
			assertEquals(forecastJsonItem.getMain().getTempKf(), forecastHourItem.getTempKf());

			assertEquals(forecastJsonItem.getWeather().get(0).getId(), forecastHourItem.getWeatherId());
			assertEquals(forecastJsonItem.getWeather().get(0).getMain(), forecastHourItem.getMain());
			assertEquals(forecastJsonItem.getWeather().get(0).getDescription(), forecastHourItem.getDescription());
			assertEquals(forecastJsonItem.getWeather().get(0).getIcon(), forecastHourItem.getIcon());

			assertEquals(forecastJsonItem.getClouds().getAll(), forecastHourItem.getClouds());
			
			assertEquals(forecastJsonItem.getWind().getSpeed(), forecastHourItem.getSpeed());
			assertEquals(forecastJsonItem.getWind().getDeg(), forecastHourItem.getDeg());
			
			assertEquals(forecastJsonItem.getRain().get3h(), forecastHourItem.getRain3h());
			assertEquals(forecastJsonItem.getSys().getPod(), forecastHourItem.getPod());
		}
		assertEquals(forecastHourJson.getCity().getId(), forecastHourEntity.getCityId());
		assertEquals(forecastHourJson.getCity().getName(), forecastHourEntity.getName());
		assertEquals(forecastHourJson.getCity().getCoord().getLat(), forecastHourEntity.getLat());
		assertEquals(forecastHourJson.getCity().getCoord().getLon(), forecastHourEntity.getLon());
		assertEquals(forecastHourJson.getCity().getCountry(), forecastHourEntity.getCountryCode());
		
		logger.info("Au fost salvate {} articole", forecastHourEntity);
	}

	private WeatherForecastHourJson getJsonObject() throws IOException, JsonParseException, JsonMappingException {
		Resource resource = new ClassPathResource(FORECAST_HOUR_JSON);
		File file = resource.getFile(); 
		ObjectMapper objectMapper = new ObjectMapper();
		
		WeatherForecastHourJson weatherHourForecast = objectMapper.readValue(file,  WeatherForecastHourJson.class);
		return weatherHourForecast;
	}
	
}
