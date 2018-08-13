package com.arobs.scheduler;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.arobs.weather.entity.WeatherForecastDaily;

import java.util.List;

@Repository
public interface WeatherForecastDailyRepository extends JpaRepository<WeatherForecastDaily, Long> {

    @Query("SELECT weatherForecastDaily FROM WeatherForecastDaily weatherForecastDaily " +
            "WHERE weatherForecastDaily.code = :code " +
            "ORDER BY weatherForecastDaily.code ")
    List<WeatherForecastDaily> find(@Param("code") String name);

//    @Modifying
//    @Query("delete from User u where u.active = false")
//    @Query("update User u set u.firstname = ?1, u.lastname = ?2 where u.id = ?3")
//    Integer saveAll(List<WeatherForecast> weatherForecast);

}

