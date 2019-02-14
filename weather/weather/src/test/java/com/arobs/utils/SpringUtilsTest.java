package com.arobs.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@AutoConfigureWebClient
public class SpringUtilsTest {
	private static final Logger logger = LoggerFactory.getLogger(SpringUtilsTest.class);
	
	@Value("${openweather.url}")
	private String openWeatherUrl;
	@Value("${openweather.appid}")
	private String openWeatherAppId;
	
	@Test
	public void testDTProperty() {
		logger.info("OpenWeather. Url: {}, AppId: {}", openWeatherUrl, openWeatherAppId);
	}
}
