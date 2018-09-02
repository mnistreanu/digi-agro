package com.arobs.weather.provider;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.arobs.weather.GZipFile;
import com.arobs.weather.provider.WeatherLocationProvider;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DownloadGZipTest {

	@Autowired
	public WeatherLocationProvider weatherLocationProvider;
	@Autowired
	public GZipFile zipFile; 
	
	@Test
	public void downloadFile() throws IOException {
		String savedFile = weatherLocationProvider.downloadFile(null, null);
		assertEquals(WeatherLocationProvider.LOCAL_FILE, savedFile);
	}
	
	@Test
	public void zipFile() throws IOException {
		zipFile.unzip(null, null);
	}
}
