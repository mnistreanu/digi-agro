package com.arobs.weather.history;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.Transient;
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
		
		String newOpenweatherIdsSQL = "SELECT snapshot.openweatherId " + 
				"FROM WeatherSnapshot snapshot " + 
				"WHERE (snapshot.dayTimestamp, snapshot.openweatherId) NOT IN " + 
					"(SELECT history.dayTimestamp, history.openweatherId FROM WeatherHistory history)"; 
		Query query = entityManager.createQuery(newOpenweatherIdsSQL, Integer.class);
		@SuppressWarnings("unchecked")
		List<Integer> newIds = query.getResultList();
		int insertedHistory = 0;
		if (! newIds.isEmpty()) {
			String insertHistorySql = "INSERT INTO WeatherHistory (" + 
					"    dayTimestamp," + 
					"    openweatherId," + 
					"    parcelId," + 
					"    name," + 
					"    cod," +
					"    sourceId," + 
					"    countryCode," + 
					"    countyId," + 
					"    lat," + 
					"    lon," +
					"    sysId," + 
					"    sysType," + 
					"    message," + 
					"    sunrise," + 
					"    sunset" + 
					")" + 
					"SELECT DISTINCT " + 
					"    dayTimestamp," + 
					"    openweatherId," + 
					"    parcelId," + 
					"    name," + 
					"    cod," +
					"    sourceId," + 
					"    countryCode," + 
					"    countyId," + 
					"    lat," + 
					"    lon," +
					"    sysId," + 
					"    sysType," + 
					"    message," + 
					"    sunrise," + 
					"    sunset " + 
					"FROM WeatherSnapshot " + 
					"WHERE openweatherId IN :openweatherIds";
			query = entityManager.createQuery(insertHistorySql);
			query.setParameter("openweatherIds", newIds);
			insertedHistory = query.executeUpdate();
		}

		String selectSnapshotSql = "SELECT COUNT(id) " + 
				"FROM WeatherSnapshot " + 
				"WHERE YEAR(dt) = :year AND MONTH(dt) = :month AND DAY(dt) = :day";
		query = entityManager.createQuery(selectSnapshotSql);
		setParameters(query, calendar);
		Long toMove = (Long) query.getSingleResult();
		if (toMove == 0L) {
			logger.debug("Pentru data {} nu exista observatii meteo de transferat in istori", new SimpleDateFormat("dd.MM.YYYY").format(calendar.getTime().getTime()));
			return 0;
		}
		
		String deleteHistorySql = "DELETE FROM WeatherHistory " /*+ 
				"WHERE YEAR(dt) = :year AND MONTH(dt) = :month AND DAY(dt) = :day"*/;
		query = entityManager.createQuery(deleteHistorySql);
//		setParameters(query, calendar);
		query.executeUpdate();
		
		
		String insertHistoryItemsSql = "INSERT INTO WeatherHistoryItem (" + 
			    "    openweatherId, " +
			    "    dt, " +
			    "    base, " +
			    "    rain3h, " +
				"    weatherId, " +
				"    main, " +
				"    description, " +
				"    icon, " +
				"    temp, " +
				"    pressure, " +
				"    humidity, " +
				"    humidityAir, " +
				"    humiditySoil, " +
				"    tempMin, " +
				"    tempMax, " +
				"    seaLevel, " +
				"    grndLevel, " +
				"    speed, " +
				"    deg, " +
				"    clouds" +
				")" + 
				"SELECT" + 
			    "    openweatherId, " +
			    "    dt, " +
			    "    base, " +
			    "    rain3h, " +
				"    weatherId, " +
				"    main, " +
				"    description, " +
				"    icon, " +
				"    temp, " +
				"    pressure, " +
				"    humidity, " +
				"    humidityAir, " +
				"    humiditySoil, " +
				"    tempMin, " +
				"    tempMax, " +
				"    seaLevel, " +
				"    grndLevel, " +
				"    speed, " +
				"    deg, " +
				"    clouds " +
				"FROM WeatherSnapshot " + 
				"WHERE YEAR(dt) = :year AND MONTH(dt) = :month AND DAY(dt) = :day";
		query = entityManager.createQuery(insertHistoryItemsSql);
		setParameters(query, calendar);
		int insertedHistoryItems = query.executeUpdate();
		
		
		
//		String deleteSnapshotSql = "DELETE FROM WeatherSnapshot " + 
//				"WHERE YEAR(dt) = :year AND MONTH(dt) = :month AND DAY(dt) = :day";
//		query = entityManager.createQuery(deleteSnapshotSql);
//		setParameters(query, calendar);
//		int deletedSnapshots = query.executeUpdate();
		
		logger.debug("Articole inserate in History: {}, eliminate din Snapshot: {}", insertedHistory, 0/*deletedSnapshots*/);
		return 0; //insertedHistory;
	}

	private void setParameters(Query query, Calendar calendar) {
		query.setParameter("year", calendar.get(Calendar.YEAR));
		query.setParameter("month", calendar.get(Calendar.MONTH) + 1);
		query.setParameter("day", calendar.get(Calendar.DATE));
	}
}
