package com.arobs.weather.location;

import com.arobs.weather.entity.WeatherLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeatherLocationRepository extends JpaRepository<WeatherLocation, Long> {

    @Query("SELECT weatherLocation FROM WeatherLocation weatherLocation " +
            "WHERE weatherLocation.countryCode = :countryCode " +
            "AND weatherLocation.name LIKE :name " +
            "ORDER BY weatherLocation.name ")
    List<WeatherLocation> find(@Param("countryCode") String countryCode, @Param("name") String name);

    @Query("SELECT weatherLocation FROM WeatherLocation weatherLocation " +
            "WHERE (weatherLocation.countryCode = 'MD' AND (weatherLocation.name LIKE 'Raion%' OR weatherLocation.name LIKE 'Municipiu%')) OR " +
            "(weatherLocation.countryCode = 'RO' AND weatherLocation.name LIKE 'Judeţ%')" +
            "ORDER BY weatherLocation.name ")
    List<WeatherLocation> findAllMdRo();

}

