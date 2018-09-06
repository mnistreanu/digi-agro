package com.arobs.weather.provider;

import com.arobs.interfaces.HasRepository;
import com.arobs.weather.WeatherUtils;
import com.arobs.weather.entity.WeatherLocation;
import com.arobs.weather.entity.WeatherSnapshot;
import com.arobs.weather.entity.WeatherSnapshotModel;
import com.arobs.weather.location.WeatherLocationRepository;
import com.arobs.weather.snapshot.WeatherSnapshotJson;
import com.arobs.weather.snapshot.WeatherSnapshotRepository;

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
	
	@Value("${weather.snapshot.city.url}")
	private String weatherSnapshotCityUrl;
	@Value("${weather.snapshot.coord.url}")
	private String weatherSnapshotCoordUrl;
	@Value("${weather.snapshot.appid}")
	private String weatherSnapshotAppid;
    @Autowired
	private RestTemplate restConnector;
	@Autowired
    private DTOBinder binder;
    @Autowired
    private WeatherLocationRepository weatherLocationRepository;
    @Autowired
    private WeatherSnapshotRepository weatherSnapshotRepository;
	
	/**
	 * Pentru fiecare localitate din tabelul "location" executa operatiile:
	 * <ol> 
	 * <li>Citeste de pe Open Weather info curenta om format JSON.</li>
	 * <li>Transforma JSON in instanta Entity.</li>
	 * <li>Inscrie toate instantele in BD.</li>
	 * </ol>
	 * @return Numarul de articole inserate in baza de date.
	 */
    public int synchronizeWeatherSnapshotsByCity() {
    	long dayTimestamp = WeatherUtils.getUnixTime();
		List<WeatherLocation> locations = weatherLocationRepository.findAll();
		List<WeatherSnapshot> weatherSnapshots = new ArrayList<>();
		for (WeatherLocation location : locations) {
			String url = String.format(weatherSnapshotCityUrl, location.getId(), weatherSnapshotAppid);
			WeatherSnapshotJson weatherSnapshotJson = restConnector.getForObject(url, WeatherSnapshotJson.class);
			WeatherSnapshot weatherSnapshot = binder.bindFromBusinessObject(WeatherSnapshot.class, weatherSnapshotJson);
			weatherSnapshot.setDayTimestamp(dayTimestamp);
			weatherSnapshots.add(weatherSnapshot);
		}
		weatherSnapshotRepository.save(weatherSnapshots);
		logger.debug("Au fost inscrise in BD {} articole", weatherSnapshots.size());
		return weatherSnapshots.size();
	}

	public int synchronizeWeatherSnapshotsByCoord() {
		List<WeatherLocation> locations = weatherLocationRepository.findAll();
		List<WeatherSnapshot> weatherSnapshots = new ArrayList<>();
		for (WeatherLocation location : locations) {
			String url = String.format(weatherSnapshotCoordUrl, location.getLat(), location.getLon(), weatherSnapshotAppid);
			WeatherSnapshotJson weatherSnapshotJson = restConnector.getForObject(url, WeatherSnapshotJson.class);
			WeatherSnapshot weatherSnapshot = binder.bindFromBusinessObject(WeatherSnapshot.class, weatherSnapshotJson);
			weatherSnapshots.add(weatherSnapshot);
		}
		weatherSnapshotRepository.save(weatherSnapshots);
		logger.debug("Au fost inscrise in BD {} articole", weatherSnapshots.size());
		return weatherSnapshots.size();
	}

	public WeatherSnapshot getByCoord(Double lat, Double lon) {
		List<WeatherSnapshot> weatherSnapshots = new ArrayList<>();
		String url = String.format(weatherSnapshotCoordUrl, lat, lon, weatherSnapshotAppid);
		WeatherSnapshotJson weatherSnapshotJson = restConnector.getForObject(url, WeatherSnapshotJson.class);
		WeatherSnapshot weatherSnapshot = binder.bindFromBusinessObject(WeatherSnapshot.class, weatherSnapshotJson);
		weatherSnapshots.add(weatherSnapshot);
		return weatherSnapshot;
	}

	@Override
    public WeatherSnapshotRepository getRepository() {
        return weatherSnapshotRepository;
    }

    public List<WeatherSnapshot> find(Integer locationlId, Date dateFrom, Date dateTo) {
        return getRepository().find(locationlId, dateFrom, dateTo);
    }

    public List<WeatherSnapshot> find(String countryId, String countyId, Date dateFrom, Date dateTo) {
        return getRepository().find(countryId, countyId, dateFrom, dateTo);
    }


	public List<WeatherSnapshot> find(Integer locationId) {
        return getRepository().find(locationId);
	}
    public WeatherSnapshot findOne(Long id) {
        return getRepository().findOne(id);
    }

	public List<WeatherSnapshot> findAll() {
		return getRepository().findAll();
	}
	
    @Transactional
    public WeatherSnapshot save(WeatherSnapshotModel model) {
        WeatherSnapshot weather = binder.bindFromBusinessObject(WeatherSnapshot.class, model);
        return getRepository().save(weather);
    }
}
