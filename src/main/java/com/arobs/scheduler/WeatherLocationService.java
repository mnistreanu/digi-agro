package com.arobs.scheduler;

import com.arobs.entity.WeatherLocation;
import com.arobs.interfaces.HasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeatherLocationService implements HasRepository<WeatherLocationRepository> {

    @Autowired
    private WeatherLocationRepository weatherLocationRepository;

    public List<WeatherLocation> find(String countyCode, String name) {
        return getRepository().find(countyCode, '%' + name + '%');
    }

    public List<WeatherLocation> findAllMdRo() {
        return getRepository().findAllMdRo();
    }

    public List<WeatherLocation> Sace(List<WeatherLocation> weatherLocations) {
        return getRepository().findAllMdRo();
    }

    @Override
    public WeatherLocationRepository getRepository() {
        return weatherLocationRepository;
    }
}
