package com.arobs;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.arobs.scheduler.WeatherForecastService;
import com.arobs.weather.LocationFileDownloader;
import com.arobs.weather.location.WeatherLocationService;
import com.arobs.weather.snapshot.WeatherSnapshotService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {
	@Autowired
	private LocationFileDownloader downloader;
	
	@Autowired
	private WeatherLocationService weatherLocationService;

	@Autowired
	private WeatherSnapshotService weatherSnapshotService;

	@Autowired
	private WeatherForecastService weatherForecastService;

	@Test
	public void testDownloader() {
		assertNotNull(downloader);
	}

	@Test
	public void testWeatherLocationService() {
		assertNotNull(weatherLocationService);
	}

	@Test
	@Rollback(false)
	public void testSynchronizeLocations() throws IOException {
		weatherLocationService.synchronizeLocations();
	}
	
	@Test
	@Rollback(false)
	public void testSynchronizeWeatherSnapshots() throws IOException {
		weatherSnapshotService.synchronizeWeatherSnapshots();
	}
	
	@Test
	@Rollback(false)
	public void testSynchronizWeatherForecastsHour() throws IOException {
		weatherForecastService.synchronizeWeatherForecastsHour();
	}
	
	@Test
	@Rollback(false)
	public void testSynchronizWeatherForecastsDaily() throws IOException {
		weatherForecastService.synchronizeWeatherForecastsDaily();
	}
}
