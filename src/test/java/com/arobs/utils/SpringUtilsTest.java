package com.arobs.utils;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringUtilsTest {
	private static final Logger logger = LoggerFactory.getLogger(SpringUtilsTest.class);
	
	@Value("${openweather.url}")
	private String openWeatherUrl;
	@Value("${openweather.appid}")
	private String openWeatherAppId;
	
	@Test
	public void testApplicationProperties() {
		assertEquals("http://api.openweathermap.org/data/2.5/weather", openWeatherUrl);
		assertEquals("101db96830e0a1520f5274ed6c10b742", openWeatherAppId);
		logger.info("OpenWeather. Url: {}, AppId: {}", openWeatherUrl, openWeatherAppId);
	}
}
