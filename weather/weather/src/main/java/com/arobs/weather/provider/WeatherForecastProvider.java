package com.arobs.weather.provider;


import com.arobs.weather.HasRepository;
import com.arobs.weather.entity.ForecastDailyItem;
import com.arobs.weather.entity.ForecastHourItem;
import com.arobs.weather.entity.WeatherForecastDaily;
import com.arobs.weather.entity.WeatherForecastHour;
import com.arobs.weather.entity.WeatherLocation;
import com.arobs.weather.forecast.daily.WeatherForecastDailyJson;
import com.arobs.weather.forecast.daily.WeatherForecastDailyRepository;
import com.arobs.weather.forecast.hour.WeatherForecastHourJson;
import com.arobs.weather.forecast.hour.WeatherForecastHourRepository;
import com.arobs.weather.location.WeatherLocationRepository;

import org.jdto.DTOBinder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class WeatherForecastProvider implements HasRepository<WeatherLocationRepository> {
	private static final Logger logger = LoggerFactory.getLogger(WeatherForecastProvider.class);
	@Value("${weather.forecast.hour.url}")
	private String weatherForecastHourUrl;
	@Value("${weather.forecast.hour.appid}")
	private String weatherForecastHourAppid;
	
	@Value("${weather.forecast.daily.url}")
	private String weatherForecastDailyUrl;
	@Value("${weather.forecast.daily.appid}")
	private String weatherForecastDailyAppid;
	
    @Autowired
	private RestTemplate restTemplate;
	@Autowired
    private DTOBinder binder;
    @Autowired
    private WeatherLocationRepository weatherLocationRepository;
    @Autowired
    private WeatherForecastHourRepository forecastHourRepository;
    @Autowired
    private WeatherForecastDailyRepository forecastDailyRepository;
    
	public void synchronizeWeatherForecastsHour() {
		List<WeatherLocation> locations = weatherLocationRepository.findAll();
		List<WeatherForecastHour> weatherForecastsHour = new ArrayList<>();
		for (WeatherLocation location : locations) {
			String url = String.format(weatherForecastHourUrl, location.getId(), weatherForecastHourAppid);
			WeatherForecastHourJson weatherForecastHourJson = restTemplate.getForObject(url, WeatherForecastHourJson.class);
			WeatherForecastHour weatherForecastHour = binder.bindFromBusinessObject(WeatherForecastHour.class, weatherForecastHourJson);
			for (ForecastHourItem item : weatherForecastHour.getForecastItems()) {
				item.setForecastHour(weatherForecastHour);
			}
			weatherForecastsHour.add(weatherForecastHour);
			break; //TODO de eliminat
		}
		forecastHourRepository.save(weatherForecastsHour);
		logger.debug("Au fost inscrise in BD {} articole", weatherForecastsHour.size());
	}

	public void synchronizeWeatherForecastsDaily() {
		List<WeatherLocation> locations = weatherLocationRepository.findAll();
		List<WeatherForecastDaily> weatherForecastsDaily = new ArrayList<>();
		for (WeatherLocation location : locations) {
			String url = String.format(weatherForecastDailyUrl, location.getId(), weatherForecastDailyAppid);
			WeatherForecastDailyJson weatherForecastDailyJson = restTemplate.getForObject(url, WeatherForecastDailyJson.class);
			WeatherForecastDaily weatherForecastDaily = binder.bindFromBusinessObject(WeatherForecastDaily.class, weatherForecastDailyJson);
			if (weatherForecastDaily.getForecastItems() == null) {
				continue;
			}
			for (ForecastDailyItem item : weatherForecastDaily.getForecastItems()) {
				item.setForecastDaily(weatherForecastDaily);
			}
			weatherForecastsDaily.add(weatherForecastDaily);
			break; //TODO de eliminat
		}
		forecastDailyRepository.save(weatherForecastsDaily);
		logger.debug("Au fost inscrise in BD {} articole", weatherForecastsDaily.size());
	}

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
