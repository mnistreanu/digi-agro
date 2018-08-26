package com.arobs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.arobs.weather.entity.WeatherSnapshot;

import java.util.Date;
import java.util.List;

@Repository
public interface WeatherSnapshotRepository extends JpaRepository<WeatherSnapshot, Long> {

    @Query("SELECT w FROM WeatherSnapshot w " +
            "WHERE w.parcelId = :parcelId " +
            "AND w.dt >= :dateFrom " +
            "ORDER BY w.dt ")
    List<WeatherSnapshot> find(@Param("parcelId") Long parcelId,
                       @Param("dateFrom") Date dateFrom);

    @Query("SELECT w FROM WeatherSnapshot w " +
            "WHERE w.parcelId = :parcelId " +
            "AND w.dt >= :dateFrom AND w.dt <= :dateTo " +
            "ORDER BY w.dt ")
    List<WeatherSnapshot> find(@Param("parcelId") Long parcelId,
                       @Param("dateFrom") Date dateFrom,
                       @Param("dateTo") Date dateTo);

    @Query("SELECT w FROM WeatherSnapshot w " +
            "WHERE w.countryCode = :countryCode " +
            "AND w.countyId = :countyId " +
            "AND w.dt >= :dateFrom AND w.dt <= :dateTo " +
            "ORDER BY w.dt ")
    List<WeatherSnapshot> find(@Param("countryCode") String country,
                       @Param("countyId") String county,
                       @Param("dateFrom") Date dateFrom,
                       @Param("dateTo") Date dateTo);
}

