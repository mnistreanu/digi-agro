package com.arobs.repository;

import com.arobs.entity.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface WeatherRepository extends JpaRepository<Weather, Long> {

    @Query("SELECT w FROM Weather w " +
            "WHERE w.parcelId = :parcelId " +
            "AND w.dt >= :dateFrom " +
            "ORDER BY w.dt ")
    List<Weather> find(@Param("parcelId") Long parcelId,
                       @Param("dateFrom") Date dateFrom);

    @Query("SELECT w FROM Weather w " +
            "WHERE w.parcelId = :parcelId " +
            "AND w.dt >= :dateFrom AND w.dt <= :dateTo " +
            "ORDER BY w.dt ")
    List<Weather> find(@Param("parcelId") Long parcelId,
                       @Param("dateFrom") Date dateFrom,
                       @Param("dateTo") Date dateTo);

    @Query("SELECT w FROM Weather w " +
            "WHERE w.countryId = :countryId " +
            "AND w.countyId = :countyId " +
            "AND w.dt >= :dateFrom AND w.dt <= :dateTo " +
            "ORDER BY w.dt ")
    List<Weather> find(@Param("countryId") String country,
                       @Param("countyId") String county,
                       @Param("dateFrom") Date dateFrom,
                       @Param("dateTo") Date dateTo);
}

