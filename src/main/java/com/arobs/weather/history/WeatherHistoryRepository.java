package com.arobs.weather.history;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arobs.weather.entity.WeatherHistory;

public interface WeatherHistoryRepository extends JpaRepository<WeatherHistory, Integer>, WeatherHistoryRepositoryCustom  {

}
