package com.arobs.weather.location;

import com.arobs.weather.entity.WeatherLocation;
import com.arobs.weather.provider.WeatherLocationProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/weather")
public class WeatherLocationController {

    @Autowired
    private WeatherLocationProvider locationProvider;

    @RequestMapping(value = "/locations", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<WeatherLocation>> getLocations() {
        List<WeatherLocation> locations = locationProvider.findAllMdRo();
        return ResponseEntity.ok(locations);
    }

}
