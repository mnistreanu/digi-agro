package com.arobs.service;

import com.arobs.interfaces.HasRepository;
import com.arobs.model.WeatherModel;
import com.arobs.repository.WeatherRepository;
import com.arobs.weather.entity.WeatherSnapshot;

import org.jdto.DTOBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class WeatherService implements HasRepository<WeatherRepository> {

    @Autowired
    private WeatherRepository weatherRepository;

	@Autowired
    DTOBinder dtoBinder;
	
    @Override
    public WeatherRepository getRepository() {
        return weatherRepository;
    }

    public List<WeatherSnapshot> find(Long parcelId, Date dateFrom, Date dateTo) {
        return getRepository().find(parcelId, dateFrom, dateTo);
    }

    public List<WeatherSnapshot> find(String countryId, String countyId, Date dateFrom, Date dateTo) {
        return getRepository().find(countryId, countyId, dateFrom, dateTo);
    }

    public WeatherSnapshot findOne(Long id) {
        return getRepository().findOne(id);
    }

    @Transactional
    public WeatherSnapshot save(WeatherModel model) {
        WeatherSnapshot weather = dtoBinder.bindFromBusinessObject(WeatherSnapshot.class, model);
        return getRepository().save(weather);
    }

//    @Transactional
//    public Weather save(List<WeatherModel> models) {
//        List<Weather> weather = dtoBinder.bindFromBusinessObjectList(Weather.class, models);
//        return getRepository().save(weather);
//    }
}
