package com.arobs.weather.forecast.daily;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class WeatherForecastDailyRepositoryImpl implements WeatherForecastDailyRepositoryCustom {
	private static final Logger logger = LoggerFactory.getLogger(WeatherForecastDailyRepositoryImpl.class);
	
	@PersistenceContext
    EntityManager entityManager;

	@Override
    @Transactional
	public int insertFromForecastHour(Date referenceDate) {
		Calendar referenceCalendar = Calendar.getInstance();
		referenceCalendar.setTime(referenceDate);
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(referenceCalendar.get(Calendar.YEAR), referenceCalendar.get(Calendar.MONTH), referenceCalendar.get(Calendar.DATE));

		String selectForecastHourSql = "SELECT COUNT(id) FROM WeatherForecastHour";
		Query query = entityManager.createQuery(selectForecastHourSql);
		Long toMove = (Long) query.getSingleResult();
		if (toMove == 0L) {
			logger.debug("Pentru data {} nu exista observatii meteo de transferat in istori", new SimpleDateFormat("dd.MM.YYYY").format(calendar.getTime().getTime()));
			return 0;
		}
		
		String insertSql = "INSERT INTO WeatherForecastDaily (" + 
				"    code," + 
				"    message," + 
				"    cnt," + 
				"    cityId," + 
				"    name," + 
				"    countryCode," + 
				"    lon," + 
				"    lat" + 
				") " + 
				"SELECT" + 
				"    code," + 
				"    message," + 
				"    cnt," + 
				"    cityId," + 
				"    name," + 
				"    countryCode," + 
				"    lon," + 
				"    lat " + 
				"FROM WeatherForecastHour"; 
		query = entityManager.createQuery(insertSql);
		int inserted = query.executeUpdate();
		logger.debug("Articole inserate in ForecastDaily: {}", inserted);
		return inserted;
	}
}
