package com.arobs.weather.entity;

import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.List;

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

import com.arobs.weather.forecast.daily.WeatherForecastDailyRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@AutoConfigureWebClient 
public class WeatherForecastHourTest {
	private static final Logger logger = LoggerFactory.getLogger(WeatherForecastHourTest.class); 
	@Autowired
	private TestEntityManager entityManager;
	@Autowired
	WeatherForecastDailyRepository weatherForecastDailyRepository;

	@Test
	public void testEntityManager() {
		assertNotNull(entityManager);
		logger.info("Entity manager s-a instantiat");
	}

	@Test
	public void testFindAll() {
		List<WeatherForecastDaily> list = weatherForecastDailyRepository.findAll();
		logger.debug("Articole citite: {}", list.size());
	}
	
	@Test
	@Rollback(false)
	public void testInsertFromForecastHour() {
		@SuppressWarnings("deprecation")
		Date referenceDate = new Date(2018 - 1900, 8 - 1, 21);
		int list = weatherForecastDailyRepository.insertFromForecastHour(referenceDate);
		logger.debug("Articole citite: {}", list);
	}
	
}
