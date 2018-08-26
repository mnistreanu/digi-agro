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

import com.arobs.weather.history.WeatherHistoryRepository;
import com.arobs.weather.snapshot.WeatherSnapshotRepositoryTest;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@AutoConfigureWebClient 
public class WeatherHistoryTest {
	private static final Logger logger = LoggerFactory.getLogger(WeatherSnapshotRepositoryTest.class); 

	@Autowired
	private TestEntityManager entityManager;
	@Autowired
	private WeatherHistoryRepository weatherHistoryRepository;

	@Test
	public void testEntityManager() {
		assertNotNull(entityManager);
		logger.info("Entity manager s-a instantiat");
	}

	@Test
	@Rollback(false)
	public void testFindAll() {
		List<WeatherHistory> list = weatherHistoryRepository.findAll();
		logger.debug("Articole citite: {}", list.size());
	}
	
	@Test
	@Rollback(false)
	public void testInsertFromSelect() {
		@SuppressWarnings("deprecation")
		Date referenceDate = new Date(2018 - 1900, 8 - 1, 21);
		int o = weatherHistoryRepository.insertFromSnapshot(referenceDate);
		logger.debug("Articole inserate: {}", o);
	}
}
