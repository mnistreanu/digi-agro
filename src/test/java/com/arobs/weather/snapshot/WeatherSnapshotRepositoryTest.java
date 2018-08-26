package com.arobs.weather.snapshot;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.persistence.Query;

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

import com.arobs.weather.entity.WeatherSnapshot;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@AutoConfigureWebClient 
public class WeatherSnapshotRepositoryTest {
	private static final Logger logger = LoggerFactory.getLogger(WeatherSnapshotRepositoryTest.class); 

	@Autowired
	private TestEntityManager entityManager;

	@Test
	public void testEntityManager() {
		assertNotNull(entityManager);
	}

	@Test
	@Rollback(false)
	public void testListWeatherSnapshots() {
		Query query = entityManager.getEntityManager().createQuery("SELECT weatherSnapshot FROM WeatherSnapshot weatherSnapshot");
		@SuppressWarnings("unchecked")
		List<WeatherSnapshot> list = query.getResultList();
		assertNotNull(list);
		logger.info("Au fost extrase {} articole.", list.size());
	}
}
