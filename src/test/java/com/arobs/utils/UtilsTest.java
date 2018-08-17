package com.arobs.utils;

import static org.junit.Assert.assertNotNull;

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
		Long unixTimestamp = //1533161231L;
		                     1533508200L;
		Date date =  new Date(unixTimestamp * 1000);
		logger.info("DT attribute: {}", date);
	}

	@Test
	public void testDTOBinder() {
		 DTOBinder binder = DTOBinderFactory.getBinder();
		 assertNotNull(binder);
	}
}
