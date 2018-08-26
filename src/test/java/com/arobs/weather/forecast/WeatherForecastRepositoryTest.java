package com.arobs.weather.forecast;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.List;

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

import com.arobs.weather.entity.ForecastDailyItem;
import com.arobs.weather.entity.ForecastHourItem;
import com.arobs.weather.entity.WeatherForecastDaily;
import com.arobs.weather.entity.WeatherForecastHour;
import com.arobs.weather.forecast.daily.WeatherForecastDailyJson;
import com.arobs.weather.forecast.daily.WeatherForecastDailyRepository;
import com.arobs.weather.forecast.hour.WeatherForecastHourJson;
import com.arobs.weather.forecast.hour.WeatherForecastHourRepository;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@AutoConfigureWebClient 
public class WeatherForecastRepositoryTest {
	private static final Logger logger = LoggerFactory.getLogger(WeatherForecastRepositoryTest.class); 

	@Autowired
	private TestEntityManager entityManager;
	@Autowired
	public WeatherForecastHourRepository hourRepository;
	@Autowired
	public WeatherForecastDailyRepository dailyRepository;
	
	@Test
	public void testEntityManager() {
		assertNotNull(entityManager);
	}
	
	@Test
	public void testFindAll() {
		List<WeatherForecastDaily> weatherForecasts = dailyRepository.findAll();
		assertNotNull(weatherForecasts);
	}
	
	@Test
	public void testFindOne() {
		List<WeatherForecastDaily> weatherForecasts = dailyRepository.findAll();
		assertNotNull(weatherForecasts);
		assertTrue(weatherForecasts.size() > 0);
		Long id = weatherForecasts.get(0).getId();
		WeatherForecastDaily forecast = dailyRepository.findOne(id);
		assertEquals("Moscow", forecast.getName());
	}
	
	@Test
	@Rollback(false)
	public void testWeatherHourForecast() throws JsonParseException, JsonMappingException, IOException {
		Resource resource = new ClassPathResource("forecast.hour.json");
		File file = resource.getFile(); 
		ObjectMapper objectMapper = new ObjectMapper();
		WeatherForecastHourJson weatherHourForecasts = objectMapper.readValue(file,  WeatherForecastHourJson.class);
		DTOBinder binder = DTOBinderFactory.getBinder();
		WeatherForecastHour hourForecast = binder.bindFromBusinessObject(WeatherForecastHour.class, weatherHourForecasts);
		assertNotNull(hourForecast);
		for (ForecastHourItem forecastHourItem : hourForecast.getForecastItems()) {
			forecastHourItem.setForecastHour(hourForecast);
		}
		WeatherForecastHour savedHourForecast = hourRepository.save(hourForecast);
		assertNotNull(savedHourForecast.getId());
		logger.debug("ID prognoza salvata: {}", savedHourForecast.getId());
	}
	
	@Test
	@Rollback(false)
	public void testWeatherDailyForecast() throws JsonParseException, JsonMappingException, IOException {
		Resource resource = new ClassPathResource("forecast.daily.json");
		File file = resource.getFile(); 
		ObjectMapper objectMapper = new ObjectMapper();
		WeatherForecastDailyJson weatherDailyForecast = objectMapper.readValue(file,  WeatherForecastDailyJson.class);
		DTOBinder binder = DTOBinderFactory.getBinder();
		WeatherForecastDaily dailyForecast = binder.bindFromBusinessObject(WeatherForecastDaily.class, weatherDailyForecast);
		assertNotNull(dailyForecast);
		for (ForecastDailyItem forecastDailyItem : dailyForecast.getForecastItems()) {
			forecastDailyItem.setForecastDaily(dailyForecast);
		}
		WeatherForecastDaily savedDailyForecast = dailyRepository.save(dailyForecast);
		assertNotNull(savedDailyForecast.getId());
		logger.debug("ID prognoza salvata: {}", savedDailyForecast.getId());
	}
	
}
