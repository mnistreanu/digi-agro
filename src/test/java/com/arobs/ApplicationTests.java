package com.arobs;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.arobs.weather.history.WeatherHistoryRepository;
import com.arobs.weather.provider.WeatherForecastProvider;
import com.arobs.weather.provider.WeatherLocationProvider;
import com.arobs.weather.provider.WeatherSnapshotProvider;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {
	@Autowired
	private WeatherLocationProvider weatherLocationProvider;

	@Autowired
	private WeatherSnapshotProvider weatherSnapshotProvider;

	@Autowired
	private WeatherForecastProvider weatherForecastProvider;
	
	@Autowired
	private WeatherHistoryRepository weatherHistoryRepository;

	@Test
	public void testWeatherLocationService() {
		assertNotNull(weatherLocationProvider);
	}

	@Test
	@Rollback(false)
	public void testSynchronizeWeatherLocations() throws IOException {
		weatherLocationProvider.synchronizeLocations();
	}
	
	@Test
	@Rollback(false)
	public void testSynchronizeWeatherSnapshots() throws IOException {
		weatherSnapshotProvider.synchronizeWeatherSnapshotsByCity();
	}
	
	@Test
	@Rollback(false)
	public void testSynchronizeWeatherForecastsHour() throws IOException {
		weatherForecastProvider.synchronizeWeatherForecastsHour();
	}
	
	@Test
	@Rollback(false)
	public void testSynchronizWeatherForecastsDaily() throws IOException {
		weatherForecastProvider.synchronizeWeatherForecastsDaily();
	}

	@Test
	@Rollback(false)
	public void testSynchronizeWeatherHistory() {
		int insertedItems = weatherHistoryRepository.synchronizeWeatherHistory();
		assertNotNull(insertedItems > 0);
	}
}
