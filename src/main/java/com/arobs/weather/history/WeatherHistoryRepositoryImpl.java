package com.arobs.weather.history;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;

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

	/**
	 * Selecteaza din snapshot toate inregistrarile meteo de pina la ziua de ieri, le agregheaza si inscrie rezultatul agregarii in tabelul histiry.
	 * Elimina din tabelul snapshot toate  inregistrarile moteo agregate si trasferate in history.
	 */
	@Override
    @Transactional
	public int synchronizeWeatherHistory() {
		Calendar referenceCalendar = Calendar.getInstance();
		referenceCalendar.roll(Calendar.DATE, false);
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(referenceCalendar.get(Calendar.YEAR), referenceCalendar.get(Calendar.MONTH), referenceCalendar.get(Calendar.DATE));
		Instant instant = Instant.ofEpochMilli(calendar.getTimeInMillis());
		long unixTimestanp = instant.getEpochSecond();

		String selectSnapshotSql = "SELECT COUNT(snapshot) " + 
				"FROM WeatherSnapshot snapshot " + 
				"WHERE snapshot.dayTimestamp <= :referenceDate";
		Query query = entityManager.createQuery(selectSnapshotSql);
		query.setParameter("referenceDate", unixTimestanp);
		Long toMove = (Long) query.getSingleResult();
		
		if (toMove ==  0L) {
			logger.debug("Pentru data {} nu exista inregistrari meteo de transferat in istori", new SimpleDateFormat("dd.MM.YYYY").format(calendar.getTime().getTime()));
			return 0;
		}
		int insertedHistory = 0;
		String insertHistorySql = "INSERT INTO WeatherHistory (" + 
				"    dayTimestamp," +
				"    parcelId," + 
				"    tempMin," + 
				"    tempMax," +
				"    pressure," +
				"    humidityAir," +
				"    humiditySoil," + 
				"    weatherId," + 
				"    main," + 
				"    description," + 
				"    speed," + 
				"    deg," + 
				"    clouds," + 
				"    rain, " +
				"    snow " +
				")" + 
				"SELECT snapshot.dayTimestamp AS dayTimestamp, " +
				"    snapshot.parcelId AS parcelId, " + 
				"    MIN(snapshot.tempMin) AS tempMin, " + 
				"    MAX(snapshot.tempMax) AS tempMax, " + 
				"    MAX(snapshot.pressure) AS pressure," +
				"    MAX(snapshot.humidity) AS humidityAir," +
				"    MAX(snapshot.humiditySoil) AS humiditySoil," +
				"    MAX(snapshot.weatherId) AS weatherId," + 
				"    MAX(snapshot.main) AS main," + 
				"    MAX(snapshot.description) AS description," + 
				"    MAX(snapshot.speed) AS speed," + 
				"    MAX(snapshot.deg) AS deg," + 
				"    MAX(snapshot.clouds) AS clouds," + 
				"    MAX(snapshot.rain) AS rain, " +
				"    MAX(snapshot.snow) AS snow " +
				"FROM WeatherSnapshot snapshot " +
				"WHERE snapshot.dayTimestamp <= :referenceDate " + 
				"GROUP BY snapshot.parcelId, snapshot.dayTimestamp";
		query = entityManager.createQuery(insertHistorySql);
		query.setParameter("referenceDate", unixTimestanp);
		insertedHistory = query.executeUpdate();

		String deleteHistorySql = "DELETE FROM WeatherSnapshot snapshot WHERE snapshot.dayTimestamp <= :referenceDate";
		query = entityManager.createQuery(deleteHistorySql);
		query.setParameter("referenceDate", unixTimestanp);
		int deletedSnapshots = query.executeUpdate();
		
		logger.debug("Articole inserate in History: {}, eliminate din Snapshot: {}", insertedHistory, deletedSnapshots);
		return insertedHistory;
	}
}
