package com.arobs.service;

import com.arobs.entity.Weather;
import com.arobs.interfaces.HasRepository;
import com.arobs.model.WeatherModel;
import com.arobs.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class WeatherService implements HasRepository<WeatherRepository> {

    @Autowired
    private AuthService authService;
    @Autowired
    private WeatherRepository weatherRepository;

    @Override
    public WeatherRepository getRepository() {
        return weatherRepository;
    }

    public List<Weather> find(Long parcelId, Date dateFrom, Date dateTo) {
        return getRepository().find(parcelId, dateFrom, dateTo);
    }

    public List<Weather> find(String countryId, String countyId, Date dateFrom, Date dateTo) {
        return getRepository().find(countryId, countyId, dateFrom, dateTo);
    }

    public Weather findOne(Long id) {
        return getRepository().findOne(id);
    }

    @Transactional
    public Weather save(WeatherModel model) {
        Weather weather = new Weather();
        this.copyValues(weather, model);
        return getRepository().save(weather);
    }

    private void copyValues(Weather entity, WeatherModel model) {
//        entity.setName(model.getName());
//        entity.setDescription(model.getDescription());
//        entity.setFiscalCode(model.getFiscalCode());
//        entity.setCountry(model.getCountry());
//        entity.setCounty(model.getCounty());
////        entity.setCity(model.getCity());
//        entity.setAddress(model.getAddress());
//        entity.setPhones(model.getPhones());
    }

}
