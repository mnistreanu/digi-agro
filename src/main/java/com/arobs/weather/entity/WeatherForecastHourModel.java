package com.arobs.weather.entity;

import java.util.ArrayList;
import java.util.List;

public class WeatherForecastHourModel {
    private Long id;
    private String code;
    private Double message;
    private Integer cnt;
    private List<ForecastHourItem> forecastItems = new ArrayList<>();
    private Integer cityId;
    private String name;
    private String countryCode;
    private Double lon;
    private Double lat;

    public WeatherForecastHourModel(WeatherForecastHour weatherForecastHour) {
    	
    }
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

    public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Double getMessage() {
		return message;
	}

	public void setMessage(Double message) {
		this.message = message;
	}

	public Integer getCnt() {
		return cnt;
	}

	public void setCnt(Integer cnt) {
		this.cnt = cnt;
	}

	public List<ForecastHourItem> getForecastItems() {
		return forecastItems;
	}

	public void setForecastItems(List<ForecastHourItem> forecastItems) {
		this.forecastItems = forecastItems;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String country) {
		this.countryCode = country;
	}

	public Double getLon() {
		return lon;
	}

	public void setLon(Double lon) {
		this.lon = lon;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

}
