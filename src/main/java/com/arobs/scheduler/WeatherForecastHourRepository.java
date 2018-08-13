package com.arobs.scheduler;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.arobs.weather.entity.WeatherForecastHour;

import java.util.List;

@Repository
public interface WeatherForecastHourRepository extends JpaRepository<WeatherForecastHour, Long> {

    @Query("SELECT weatherForecastHour FROM WeatherForecastHour weatherForecastHour " +
            "WHERE weatherForecastHour.name LIKE :name " +
            "ORDER BY weatherForecastHour.name ")
    List<WeatherForecastHour> find(@Param("name") String name);

//    @Modifying
//    @Query("delete from User u where u.active = false")
//    @Query("update User u set u.firstname = ?1, u.lastname = ?2 where u.id = ?3")
//    Integer saveAll(List<WeatherForecast> weatherForecast);

}

