package com.arobs.scheduler;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.arobs.weather.entity.WeatherLocation;

import java.util.List;

@Repository
public interface WeatherLocationRepository extends JpaRepository<WeatherLocation, Integer> {

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
    
//    @Modifying
//    @Query("delete from User u where u.active = false")
//    @Query("update User u set u.firstname = ?1, u.lastname = ?2 where u.id = ?3")
//    Integer saveAll(List<WeatherLocation> weatherLocation);

}

