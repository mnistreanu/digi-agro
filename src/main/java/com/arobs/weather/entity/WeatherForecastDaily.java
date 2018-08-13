package com.arobs.weather.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.jdto.annotation.DTOCascade;
import org.jdto.annotation.DTOTransient;
import org.jdto.annotation.Source;

@Entity
@Table(name = "weather_forecast_daily")
public class WeatherForecastDaily {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name="id")
    @DTOTransient
    private Long id;
    @Column(name="code")
	@Source("cod")
    private String code;
    @Column(name="message")
	@Source("message")
    private Double message;
    @Column(name="cnt")
	@Source("cnt")
    private Integer cnt;
    @DTOCascade
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "forecastDaily")
	@Source("list")
    private List<ForecastDailyItem> forecastItems = null;
    @Column(name="city_id")
	@Source("city.id")
    private Integer cityId;
    @Column(name="name")
	@Source("city.name")
    private String name;
    @Column(name="country_code")
	@Source("city.country")
    private String countryCode;
    @Column(name="lon")
	@Source("city.coord.lon")
    private Double lon;
    @Column(name="lat")
	@Source("city.coord.lat")
    private Double lat;

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

	public List<ForecastDailyItem> getForecastItems() {
		return forecastItems;
	}

	public void setForecastItems(List<ForecastDailyItem> forecastItems) {
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
