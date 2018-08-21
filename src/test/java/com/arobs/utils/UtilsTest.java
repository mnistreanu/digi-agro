package com.arobs.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Calendar;
import java.util.Date;

import org.jdto.DTOBinder;
import org.jdto.DTOBinderFactory;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UtilsTest {
	private static final Logger logger = LoggerFactory.getLogger(UtilsTest.class);
	
	@Test
	public void testDTProperty() {
		Long unixTimestamp = 1533508200L;
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(2018, 8-1, 6, 1, 30, 0);
		Date calendarDate = calendar.getTime();   
		Date timestampDate =  new Date(unixTimestamp * 1000);
		assertEquals(calendarDate, timestampDate);
		logger.info("DT attribute: {}", timestampDate);
		logger.debug("DT attribute: {}", timestampDate);
	}

	@Test
	public void testDTOBinder() {
		 DTOBinder binder = DTOBinderFactory.getBinder();
		 assertNotNull(binder);
	}
}
