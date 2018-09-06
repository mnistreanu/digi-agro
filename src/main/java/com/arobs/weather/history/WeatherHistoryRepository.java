package com.arobs.weather.history;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.arobs.weather.entity.WeatherHistory;

public interface WeatherHistoryRepository extends JpaRepository<WeatherHistory, Integer>, WeatherHistoryRepositoryCustom  {
	/**
	 * Returneaza inregistrari meteo in toate localitatile pentru data specificata
	 * @param dayTimestamp - data specificata in format Linux
	 * @return lista de History
	 */
    @Query("SELECT weatherHistory FROM WeatherHistory weatherHistory WHERE weatherHistory.dayTimestamp = :dayTimestamp")
    List<WeatherHistory> find(@Param("dayTimestamp") Long dayTimestamp );

    /**
     * Returneaza toate inregistrarile meteo pentru localitatea specificat 
     * @param locationId - ID localitatii specificate
     * @return lista de History
     */
    @Query("SELECT weatherHistory FROM WeatherHistory weatherHistory WHERE weatherHistory.openweatherId = :locationId")
    List<WeatherHistory> find(@Param("locationId") Integer locationId);

	/**
	 * Returneaza inregistrari meteo in localitatea data pentru intervalul de timp specificat
	 * @param locationId - ID localitate
	 * @param dateFrom - inceput de interval
	 * @param dateTo - sfirsit de interval
	 * @return lista de History
	 */
    @Query("SELECT weatherHistory FROM WeatherHistory weatherHistory " + 
			"WHERE weatherHistory.openweatherId = :locationId AND weatherHistory.dayTimestamp BETWEEN :dateFrom AND :dateTo")
    List<WeatherHistory> find(@Param("locationId") Integer locationId, @Param("dateFrom") Long dateFrom, @Param("dateTo") Long dateTo);
}
