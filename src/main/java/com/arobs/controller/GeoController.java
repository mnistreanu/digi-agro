package com.arobs.controller;

import com.arobs.entity.City;
import com.arobs.entity.Country;
import com.arobs.entity.County;
import com.arobs.model.geo.GeoModel;
import com.arobs.service.geo.CityService;
import com.arobs.service.geo.CountryService;
import com.arobs.service.geo.CountyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/geo")
public class GeoController {

    @Autowired
    private CountryService countryService;
    @Autowired
    private CountyService countyService;
    @Autowired
    private CityService cityService;

    @RequestMapping(value = "/country", method = RequestMethod.GET)
    public ResponseEntity<List<GeoModel>> getCountries() {
        List<Country> items = countryService.findAll();
        List<GeoModel> models = items.stream().map(c -> new GeoModel(c.getId(), c.getNameRo(), c.getNameRu())).collect(Collectors.toList());
        return ResponseEntity.ok(models);
    }

    @RequestMapping(value = "/county", method = RequestMethod.GET)
    public ResponseEntity<List<GeoModel>> getCounties(@RequestParam("country") String country) {
        List<County> items = countyService.find(country);
        List<GeoModel> models = items.stream().map(c -> new GeoModel(c.getId(), c.getNameRo(), c.getNameRu())).collect(Collectors.toList());
        return ResponseEntity.ok(models);
    }

    @RequestMapping(value = "/city", method = RequestMethod.GET)
    public ResponseEntity<List<GeoModel>> getCities(@RequestParam("country") String country,
                                                    @RequestParam("county") String county) {
        List<City> items = cityService.find(country, county);
        List<GeoModel> models = items.stream().map(c -> new GeoModel(c.getId(), c.getNameRo(), c.getNameRu())).collect(Collectors.toList());
        return ResponseEntity.ok(models);
    }


}
