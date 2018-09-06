package com.arobs.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

import org.jdto.DTOBinder;
import org.jdto.DTOBinderFactory;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.scheduling.support.SimpleTriggerContext;

public class UtilsTest {
	private static final Logger logger = LoggerFactory.getLogger(UtilsTest.class);
	
	@Test
	public void testDTProperty() {
		Long unixTimestamp = 1533508200L;
		Date timestampDate =  new Date(unixTimestamp * 1000);
		
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(2018, 8-1, 6, 1, 30, 0);
		Date calendarDate = calendar.getTime();
		
		logger.debug("DT attribute: {}", timestampDate);
		assertEquals(calendarDate, timestampDate);
	}

	@Test
	public void testDTOBinder() {
		 DTOBinder binder = DTOBinderFactory.getBinder();
		 assertNotNull(binder);
	}

	@Test
	public void testCron1() {
		 CronTrigger t = new CronTrigger("0 0 0 1 1 ?");
		 SimpleTriggerContext tc = new SimpleTriggerContext();
		 logger.debug("Next 1 Execution Time: {}", t.nextExecutionTime(tc));
		 tc.update(null, null, t.nextExecutionTime(tc));
		 logger.debug("Next 2 Execution Time: {}", t.nextExecutionTime(tc));
		 tc.update(null, null, t.nextExecutionTime(tc));
		 logger.debug("Next 1 Execution Time: {}", t.nextExecutionTime(tc));
	}

	@Test
	public void testCron2() {
		 CronTrigger t = new CronTrigger("0 0/30 * * * ?");
		 SimpleTriggerContext tc = new SimpleTriggerContext();
		 logger.debug("Next 1 Execution Time: {}", t.nextExecutionTime(tc));
		 tc.update(null, null, t.nextExecutionTime(tc));
		 logger.debug("Next 2 Execution Time: {}", t.nextExecutionTime(tc));
		 tc.update(null, null, t.nextExecutionTime(tc));
		 logger.debug("Next 1 Execution Time: {}", t.nextExecutionTime(tc));
	}

	@Test
	public void testUnixTime() {
		long ut1 = Instant.now().getEpochSecond();
		logger.debug("Epoch Second: {}", ut1);

		long ut2 = System.currentTimeMillis() / 1000L;
		logger.debug("Current Time: {}", ut2);

		Date now = new Date();
		long ut3 = now.getTime() / 1000L;
		logger.debug("Get Time: {}", ut3);
		
		Calendar nowCalendar = Calendar.getInstance();
		int year = nowCalendar.get(Calendar.YEAR);
		int month = nowCalendar.get(Calendar.MONTH);
		int day = nowCalendar.get(Calendar.DATE);
		nowCalendar.clear();
		nowCalendar.set(year, month, day);
		Instant inst = Instant.ofEpochMilli(nowCalendar.getTime().getTime());
		logger.debug("Epoch Milli: {}", inst.getEpochSecond());
		
		Instant inst2 = Instant.ofEpochMilli(nowCalendar.getTimeInMillis());
		logger.debug("Epoch Milli 2: {}", inst2.getEpochSecond());
	}
	
	@Test
	public void testConvertUnixTime() {
		Long t1 = 1489471200L;
		Date timestampDate1 =  new Date(t1 * 1000);
		Long t2 = 1489557600L;
		Date timestampDate2 =  new Date(t2 * 1000);
		Long t3 = 1489465075L;
		Date timestampDate3 =  new Date(t3 * 1000);
		Long t4 = 1535749200L;
		Date timestampDate4 =  new Date(t4 * 1000);
		logger.debug("Timestamp: {}, {}, {}, {}", timestampDate1, timestampDate2, timestampDate3, timestampDate4);
	}
	
	
}
