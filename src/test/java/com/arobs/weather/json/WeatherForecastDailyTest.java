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

import com.arobs.weather.entity.ForecastDailyItem;
import com.arobs.weather.entity.WeatherForecastDaily;
import com.arobs.weather.forecast.daily.ForecastItemJson;
import com.arobs.weather.forecast.daily.WeatherForecastDailyJson;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class WeatherForecastDailyTest {
	private static final Logger logger = LoggerFactory.getLogger(WeatherForecastDailyTest.class); 
	private static final String FORECAST_DAILY_JSON = "forecast.daily.json";
	private DTOBinder binder = DTOBinderFactory.getBinder();

	@Test
	public void testJsonObject() throws JsonParseException, JsonMappingException, IOException {
		WeatherForecastDailyJson forecastDailyJson = getJsonObject();
		logger.info("Au fost salvate {} articole", forecastDailyJson);
		assertEquals(forecastDailyJson.getCnt().intValue(), forecastDailyJson.getList().size());
		
		assertEquals("200", forecastDailyJson.getCod());
		assertEquals(Double.valueOf(0.0032), forecastDailyJson.getMessage());
		assertEquals(Integer.valueOf(2), forecastDailyJson.getCnt());
		assertEquals(new Date(1406080800 * 1000L), forecastDailyJson.getList().get(0).getDt());
		assertEquals(Double.valueOf(925.04), forecastDailyJson.getList().get(0).getPressure());
		assertEquals(Integer.valueOf(76), forecastDailyJson.getList().get(0).getHumidity());
		assertEquals(Double.valueOf(297.77), forecastDailyJson.getList().get(0).getTemp().getDay());
		assertEquals(Double.valueOf(293.52), forecastDailyJson.getList().get(0).getTemp().getMin());
		assertEquals(Double.valueOf(297.77), forecastDailyJson.getList().get(0).getTemp().getMax());
		assertEquals(Double.valueOf(293.52), forecastDailyJson.getList().get(0).getTemp().getNight());
		assertEquals(Double.valueOf(297.77), forecastDailyJson.getList().get(0).getTemp().getEve());
		assertEquals(Double.valueOf(297.77), forecastDailyJson.getList().get(0).getTemp().getMorn());
		assertEquals(Integer.valueOf(803), forecastDailyJson.getList().get(0).getWeather().get(0).getId());
		assertEquals("Clouds", forecastDailyJson.getList().get(0).getWeather().get(0).getMain());
		assertEquals("broken clouds", forecastDailyJson.getList().get(0).getWeather().get(0).getDescription());
		assertEquals("04d", forecastDailyJson.getList().get(0).getWeather().get(0).getIcon());
		assertEquals(Integer.valueOf(1851632), forecastDailyJson.getCity().getId());
		assertEquals("Shuzenji", forecastDailyJson.getCity().getName());
		assertEquals("JP", forecastDailyJson.getCity().getCountry());
		assertEquals(Double.valueOf(138.933334), forecastDailyJson.getCity().getCoord().getLon());
		assertEquals(Double.valueOf(34.966671), forecastDailyJson.getCity().getCoord().getLat());
	}

	@Test
	public void testBinding() throws JsonParseException, JsonMappingException, IOException {
		WeatherForecastDailyJson forecastDailyJson = getJsonObject();
		WeatherForecastDaily forecastDailyEntity = binder.bindFromBusinessObject(WeatherForecastDaily.class, forecastDailyJson);
		assertNotNull(forecastDailyEntity);
		
		assertEquals(forecastDailyJson.getCod(), forecastDailyEntity.getCode());
		assertEquals(forecastDailyJson.getMessage(), forecastDailyEntity.getMessage());
		assertEquals(forecastDailyJson.getCnt(), forecastDailyEntity.getCnt());
		for (int i=0; i<forecastDailyJson.getList().size(); i++) {
			ForecastDailyItem forecastDailyItem = forecastDailyEntity.getForecastItems().get(i);
			ForecastItemJson forecastJsonItem = forecastDailyJson.getList().get(i);
			assertEquals(forecastJsonItem.getDt(), forecastDailyItem.getDt());
			assertEquals(forecastJsonItem.getPressure(), forecastDailyItem.getPressure());
			assertEquals(forecastJsonItem.getHumidity(), forecastDailyItem.getHumidity());
			assertEquals(forecastJsonItem.getTemp().getDay(), forecastDailyItem.getDay());
			assertEquals(forecastJsonItem.getTemp().getMin(), forecastDailyItem.getMin());
			assertEquals(forecastJsonItem.getTemp().getMax(), forecastDailyItem.getMax());
			assertEquals(forecastJsonItem.getTemp().getNight(), forecastDailyItem.getNight());
			assertEquals(forecastJsonItem.getTemp().getEve(), forecastDailyItem.getEve());
			assertEquals(forecastJsonItem.getTemp().getMorn(), forecastDailyItem.getMorn());
			assertEquals(forecastJsonItem.getWeather().get(0).getId(), forecastDailyItem.getWeatherId());
			assertEquals(forecastJsonItem.getWeather().get(0).getMain(), forecastDailyItem.getMain());
			assertEquals(forecastJsonItem.getWeather().get(0).getDescription(), forecastDailyItem.getDescription());
			assertEquals(forecastJsonItem.getWeather().get(0).getIcon(), forecastDailyItem.getIcon());
		}
		assertEquals(forecastDailyJson.getCity().getId(), forecastDailyEntity.getCityId());
		assertEquals(forecastDailyJson.getCity().getName(), forecastDailyEntity.getName());
		assertEquals(forecastDailyJson.getCity().getCountry(), forecastDailyEntity.getCountryCode());
		assertEquals(forecastDailyJson.getCity().getCoord().getLon(), forecastDailyEntity.getLon());
		assertEquals(forecastDailyJson.getCity().getCoord().getLat(), forecastDailyEntity.getLat());
		
		logger.info("Au fost salvate {} articole", forecastDailyEntity);
	}

	private WeatherForecastDailyJson getJsonObject() throws IOException, JsonParseException, JsonMappingException {
		Resource resource = new ClassPathResource(FORECAST_DAILY_JSON);
		File file = resource.getFile(); 
		ObjectMapper objectMapper = new ObjectMapper();
		
		WeatherForecastDailyJson weatherDailyForecast = objectMapper.readValue(file,  WeatherForecastDailyJson.class);
		return weatherDailyForecast;
	}
	
}
