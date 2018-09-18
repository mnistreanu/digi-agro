package com.arobs.weather;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WeatherSchedulerTest {
	@Autowired
	private WeatherScheduler weatherScheduler;
	
	@Test
	public void testWeatherScheduler() {
		assertNotNull(weatherScheduler);
	}
	
	@Test
	public void testReadWeatherSnapshotsByCity() {
		weatherScheduler.synchronizeWeatherSnapshotsByCity();
	}
	
	@Test
	public void testReadWeatherCoord() {
		weatherScheduler.synchronizeWeatherSnapshotsByCoord();
	}
	
	@Test
	public void testReadWeatherHistory() {
		weatherScheduler.synchronizeWeatherHistory();
	}
	
	@Test
	public void testReadWeatherForecastsHour() {
		weatherScheduler.synchronizeWeatherForecastsHour();
	}
	
	@Test
	public void testReadForecastsDaily() {
		weatherScheduler.synchronizeWeatherForecastsDaily();
	}
}
