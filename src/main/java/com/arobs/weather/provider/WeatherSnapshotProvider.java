package com.arobs.weather.provider;

import com.arobs.interfaces.HasRepository;
import com.arobs.model.WeatherModel;
import com.arobs.repository.WeatherSnapshotRepository;
import com.arobs.weather.entity.WeatherLocation;
import com.arobs.weather.entity.WeatherSnapshot;
import com.arobs.weather.location.WeatherLocationRepository;
import com.arobs.weather.snapshot.WeatherSnapshotJson;

import org.jdto.DTOBinder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class WeatherSnapshotProvider implements HasRepository<WeatherSnapshotRepository> {
	private static final Logger logger = LoggerFactory.getLogger(WeatherSnapshotProvider.class);
	
	@Value("${weather.snapshot.url}")
	private String weatherSnapshotUrl;
	@Value("${weather.snapshot.appid}")
	private String weatherSnapshotAppid;
    @Autowired
	private RestTemplate restTemplate;
	@Autowired
    private DTOBinder binder;
    @Autowired
    private WeatherLocationRepository weatherLocationRepository;
    @Autowired
    private WeatherSnapshotRepository weatherSnapshotRepository;

	
	public void synchronizeWeatherSnapshots() {
		List<WeatherLocation> locations = weatherLocationRepository.findAll();
		List<WeatherSnapshot> weatherSnapshots = new ArrayList<>();
		for (WeatherLocation location : locations) {
			String url = String.format(weatherSnapshotUrl, location.getId(), weatherSnapshotAppid);
			WeatherSnapshotJson weatherSnapshotJson = restTemplate.getForObject(url, WeatherSnapshotJson.class);
			WeatherSnapshot weatherSnapshot = binder.bindFromBusinessObject(WeatherSnapshot.class, weatherSnapshotJson);
			weatherSnapshots.add(weatherSnapshot);
			break; //TODO de eliminat
		}
		weatherSnapshotRepository.save(weatherSnapshots);
		logger.debug("Au fost inscrise in BD {} articole", weatherSnapshots.size());
	}

	@Override
    public WeatherSnapshotRepository getRepository() {
        return weatherSnapshotRepository;
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
        WeatherSnapshot weather = binder.bindFromBusinessObject(WeatherSnapshot.class, model);
        return getRepository().save(weather);
    }

//    @Transactional
//    public Weather save(List<WeatherModel> models) {
//        List<Weather> weather = dtoBinder.bindFromBusinessObjectList(Weather.class, models);
//        return getRepository().save(weather);
//    }
}
