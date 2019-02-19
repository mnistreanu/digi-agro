package com.arobs.weather.entity;

import java.util.Date;

/**
 * Date istorice. Se transfera zilnic din tabelul "weather_snapshot"
 */
public class WeatherHistoryModel {
	private Long id;
	private String countyId;
	private Long parcelId;
	private Date dt;
	private Double tempMin;
	private Double tempMax;
	private Double pressure;
	private Integer humidityAir;
	private Integer humiditySoil;
	private Integer weatherId;
	private String main;
	private String description;
	private String icon;
	private Double speed;
	private Double deg;
	private Integer clouds;
	private Double rain;
	private Double snow;

	public WeatherHistoryModel(WeatherHistory weatherHistory) {
		super();
		this.id = weatherHistory.getId();
		if (weatherHistory.getWeatherLocation() != null) {
			this.countyId = weatherHistory.getWeatherLocation().getCountyCode();
		}
		this.parcelId = weatherHistory.getParcelId();
		this.dt = weatherHistory.getDt();
		this.tempMin = weatherHistory.getTempMin();
		this.tempMax = weatherHistory.getTempMax();
		this.pressure = weatherHistory.getPressure();
		this.humidityAir = weatherHistory.getHumidityAir();
		this.humiditySoil = weatherHistory.getHumiditySoil();
		this.weatherId = weatherHistory.getOpenWeatherId();
		this.main = weatherHistory.getMain();
		this.description = weatherHistory.getDescription();
		this.icon = weatherHistory.getIcon();
		this.speed = weatherHistory.getSpeed();
		this.deg = weatherHistory.getDeg();
		this.clouds = weatherHistory.getClouds();
		this.rain = weatherHistory.getRain();
		this.snow = weatherHistory.getSnow();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCountyId() {
		return countyId;
	}

	public void setCountyId(String countyId) {
		this.countyId = countyId;
	}

	public Long getParcelId() {
		return parcelId;
	}

	public void setParcelId(Long parcelId) {
		this.parcelId = parcelId;
	}

	public Date getDt() {
		return dt;
	}

	public void setDt(Date dt) {
		this.dt = dt;
	}

	public Double getTempMin() {
		return tempMin;
	}

	public void setTempMin(Double tempMin) {
		this.tempMin = tempMin;
	}

	public Double getTempMax() {
		return tempMax;
	}

	public void setTempMax(Double tempMax) {
		this.tempMax = tempMax;
	}

	public Double getPressure() {
		return pressure;
	}

	public void setPressure(Double pressure) {
		this.pressure = pressure;
	}

	public Integer getHumidityAir() {
		return humidityAir;
	}

	public void setHumidityAir(Integer humidityAir) {
		this.humidityAir = humidityAir;
	}

	public Integer getHumiditySoil() {
		return humiditySoil;
	}

	public void setHumiditySoil(Integer humiditySoil) {
		this.humiditySoil = humiditySoil;
	}

	public Integer getWeatherId() {
		return weatherId;
	}

	public void setWeatherId(Integer weatherId) {
		this.weatherId = weatherId;
	}

	public String getMain() {
		return main;
	}

	public void setMain(String main) {
		this.main = main;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Double getSpeed() {
		return speed;
	}

	public void setSpeed(Double speed) {
		this.speed = speed;
	}

	public Double getDeg() {
		return deg;
	}

	public void setDeg(Double deg) {
		this.deg = deg;
	}

	public Integer getClouds() {
		return clouds;
	}

	public void setClouds(Integer clouds) {
		this.clouds = clouds;
	}

	public Double getRain() {
		return rain;
	}

	public void setRain(Double rain) {
		this.rain = rain;
	}

	public Double getSnow() {
		return snow;
	}

	public void setSnow(Double snow) {
		this.snow = snow;
	}
}

