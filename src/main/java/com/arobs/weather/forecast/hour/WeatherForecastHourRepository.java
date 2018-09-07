package com.arobs.weather.forecast.hour;

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

	List<WeatherForecastHour> find(Integer locationId);

	List<WeatherForecastHour> find(Integer locationId, long unixTime, long unixTime2);

	List<WeatherForecastHour> find(long unixTime);

}

