package com.arobs.weather.snapshot;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.arobs.weather.history.WeatherHistoryRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WeatherHistoryRepositoryTest {
	private static final Logger logger = LoggerFactory.getLogger(WeatherHistoryRepositoryTest.class); 

	@Autowired
	private WeatherHistoryRepository historyRepository;

	@Test
	public void testHistoryRepository() {
		assertNotNull(historyRepository);
	}

	@Test
	@Rollback(false)
	public void testSynchronizeWeatherHistory() {
		int insertedItems = historyRepository.synchronizeWeatherHistory();
		assertNotNull(insertedItems > 0);
		logger.info("Au fost extrase {} articole.", insertedItems);
	}
}
