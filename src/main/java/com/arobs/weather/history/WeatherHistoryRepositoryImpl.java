package com.arobs.weather.history;

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
public class WeatherHistoryRepositoryImpl implements WeatherHistoryRepositoryCustom {
	private static final Logger logger = LoggerFactory.getLogger(WeatherHistoryRepositoryImpl.class);
	
	@PersistenceContext
    EntityManager entityManager;

	@Override
    @Transactional
	public int insertFromSnapshot(Date referenceDate) {
		Calendar referenceCalendar = Calendar.getInstance();
		referenceCalendar.setTime(referenceDate);
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(referenceCalendar.get(Calendar.YEAR), referenceCalendar.get(Calendar.MONTH), referenceCalendar.get(Calendar.DATE));

		String selectSnapshotSql = "SELECT COUNT(id) " + 
				"FROM WeatherSnapshot " + 
				"WHERE YEAR(dt) = :year AND MONTH(dt) = :month AND DAY(dt) = :day";
		Query query = entityManager.createQuery(selectSnapshotSql);
		setParameters(query, calendar);
		Long toMove = (Long) query.getSingleResult();
		if (toMove == 0L) {
			logger.debug("Pentru data {} nu exista observatii meteo de transferat in istori", new SimpleDateFormat("dd.MM.YYYY").format(calendar.getTime().getTime()));
			return 0;
		}
		
		String deleteHistorySql = "DELETE FROM WeatherHistory " + 
				"WHERE YEAR(dt) = :year AND MONTH(dt) = :month AND DAY(dt) = :day";
		query = entityManager.createQuery(deleteHistorySql);
		setParameters(query, calendar);
		query.executeUpdate();
		
		String insertSql = "insert into WeatherHistory (" + 
				"    openweatherId," + 
				"    parcelId," + 
				"    name," + 
				"    cod," + 
				"    dt," + 
				"    base," + 
				"    rain3h," + 
				"    sourceId," + 
				"    countyId," + 
				"    lat," + 
				"    lon," + 
				"    weatherId," + 
				"    main," + 
				"    description," + 
				"    icon," + 
				"    temp," + 
				"    pressure," + 
				"    humidity," + 
				"    humidityAir," + 
				"    humiditySoil," + 
				"    tempMin," + 
				"    tempMax," + 
				"    seaLevel," + 
				"    grndLevel," + 
				"    speed," + 
				"    deg," + 
				"    clouds," + 
				"    sysId," + 
				"    sysType," + 
				"    message," + 
				"    countryCode," + 
				"    sunrise," + 
				"    sunset" + 
				")" + 
				"select" + 
				"    openweatherId," + 
				"    parcelId," + 
				"    name," + 
				"    cod," + 
				"    dt," + 
				"    base," + 
				"    rain3h," + 
				"    sourceId," + 
				"    countyId," + 
				"    lat," + 
				"    lon," + 
				"    weatherId," + 
				"    main," + 
				"    description," + 
				"    icon," + 
				"    temp," + 
				"    pressure," + 
				"    humidity," + 
				"    humidityAir," + 
				"    humiditySoil," + 
				"    tempMin," + 
				"    tempMax," + 
				"    seaLevel," + 
				"    grndLevel," + 
				"    speed," + 
				"    deg," + 
				"    clouds," + 
				"    sysId," + 
				"    sysType," + 
				"    message," + 
				"    countryCode," + 
				"    sunrise," + 
				"    sunset " + 
				"FROM WeatherSnapshot " + 
				"WHERE YEAR(dt) = :year AND MONTH(dt) = :month AND DAY(dt) = :day";
		query = entityManager.createQuery(insertSql);
		setParameters(query, calendar);
		int inserted = query.executeUpdate();
		
		String deleteSnapshotSql = "DELETE FROM WeatherSnapshot " + 
				"WHERE YEAR(dt) = :year AND MONTH(dt) = :month AND DAY(dt) = :day";
		query = entityManager.createQuery(deleteSnapshotSql);
		setParameters(query, calendar);
		int deletedSnapshots = query.executeUpdate();
		
		logger.debug("Articole inserate in History: {}, eliminate din Snapshot: {}", inserted, deletedSnapshots);
		return inserted;
	}

	private void setParameters(Query query, Calendar calendar) {
		query.setParameter("year", calendar.get(Calendar.YEAR));
		query.setParameter("month", calendar.get(Calendar.MONTH) + 1);
		query.setParameter("day", calendar.get(Calendar.DATE));
	}
}
