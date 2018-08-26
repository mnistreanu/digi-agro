package com.arobs.weather.forecast.daily;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.arobs.weather.entity.WeatherForecastDaily;

public interface WeatherForecastDailyRepository extends JpaRepository<WeatherForecastDaily, Long>, WeatherForecastDailyRepositoryCustom  {

    @Query("SELECT weatherForecastDaily FROM WeatherForecastDaily weatherForecastDaily " +
            "WHERE weatherForecastDaily.code = :code " +
            "ORDER BY weatherForecastDaily.code ")
    List<WeatherForecastDaily> find(@Param("code") String name);
}
