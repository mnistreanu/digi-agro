package com.arobs.weather.history;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.arobs.weather.entity.WeatherHistory;

public interface WeatherHistoryRepository extends JpaRepository<WeatherHistory, Integer>, WeatherHistoryRepositoryCustom  {
	/**
	 * Returneaza inregistrari meteo in toate localitatile pentru data specificata
	 * @param dt - data
	 * @return lista de History
	 */
    @Query("SELECT weatherHistory FROM WeatherHistory weatherHistory WHERE weatherHistory.dt = :dt")
    List<WeatherHistory> find(@Param("dt") Date dt );

    /**
     * Returneaza toate inregistrarile meteo pentru localitatea specificat 
     * @param locationId - ID localitatii specificate
     * @return lista de History
     */
    @Query("SELECT weatherHistory " +
			"FROM WeatherHistory weatherHistory " +
			"JOIN FETCH weatherHistory.weatherLocation location " +
			"WHERE location.id = :locationId")
    List<WeatherHistory> find(@Param("locationId") Integer locationId);

	/**
	 * Returneaza inregistrari meteo in localitatea data pentru intervalul de timp specificat
	 * @param locationId - ID localitate
	 * @param dateFrom - inceput de interval
	 * @param dateTo - sfirsit de interval
	 * @return lista de History
	 */
	@Query("SELECT weatherHistory FROM WeatherHistory weatherHistory " +
			"JOIN FETCH weatherHistory.weatherLocation location " +
			"WHERE location.id = :locationId " +
			"AND weatherHistory.dt BETWEEN :dateFrom AND :dateTo")
	List<WeatherHistory> find(@Param("locationId") Integer locationId, @Param("dateFrom") Long dateFrom, @Param("dateTo") Long dateTo);

	/**
	 * Returneaza toate inregistrarile meteo pentru localitatea specificat
	 * @param countryCode - codul tarii specificate
	 * @param countyCode - codul regiunii specificate
	 * @return lista de History
	 */
	@Query("SELECT weatherHistory " +
			"FROM WeatherHistory weatherHistory " +
			"JOIN FETCH weatherHistory.weatherLocation location " +
			"WHERE location.countryCode = :countryCode AND location.countyCode = :countyCode")
	List<WeatherHistory> find(@Param("countryCode") String countryCode, @Param("countyCode") String countyCode);

	/**
	 * Returneaza toate inregistrarile meteo pentru localitatea specificat
	 * @param countryCode - codul tarii specificate
	 * @param countyCode - codul regiunii specificate
	 * @param dateFrom - inceput de interval
	 * @param dateTo - sfirsit de interval
	 * @return lista de History
	 */
	@Query("SELECT weatherHistory " +
			"FROM WeatherHistory weatherHistory " +
			"JOIN FETCH weatherHistory.weatherLocation location " +
			"WHERE location.countryCode = :countryCode AND location.countyCode = :countyCode " +
			"AND weatherHistory.dt BETWEEN :dateFrom AND :dateTo")
	List<WeatherHistory> find(@Param("countryCode") String countryCode, @Param("countyCode") String countyCode,
							  @Param("dateFrom") Long dateFrom, @Param("dateTo") Long dateTo);
}
