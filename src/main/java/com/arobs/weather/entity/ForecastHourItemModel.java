
package com.arobs.weather.entity;

import java.util.Date;
import java.util.List;

import com.arobs.weather.forecast.hour.Weather;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ForecastHourItemModel {
	private Long id;
	private Long forecastHourId;
	private Date dt;
	private String dtTxt;
	private Double temp;
	private Double tempMin;
	private Double tempMax;
	private Double pressure;
	private Double seaLevel;
	private Double grndLevel;
	private Integer humidity;
	private Integer tempKf;
	private List<Weather> weather;
	private Integer weatherId;
	private String main;
	private String description;
	private String icon;
	private Integer clouds;
	private Double speed;
	private Double deg;
	private Double rain3h;
	private String pod;

	public ForecastHourItemModel(ForecastHourItem forecastHourItem) {
		this.id = forecastHourItem.getId();
		this.forecastHourId = forecastHourItem.getForecastHourId();
		this.dt = forecastHourItem.getDt();
		this.dtTxt = forecastHourItem.getDtTxt();

		this.temp = forecastHourItem.getTemp();
		this.tempMin = forecastHourItem.getTempMin();
		this.tempMax = forecastHourItem.getTempMax();
		this.pressure = forecastHourItem.getPressure();
		this.seaLevel = forecastHourItem.getSeaLevel();
		this.grndLevel = forecastHourItem.getGrndLevel();
		this.humidity = forecastHourItem.getHumidity();
		this.tempKf = forecastHourItem.getTempKf();

		this.weather = forecastHourItem.getWeather();

		this.weatherId = forecastHourItem.getWeatherId();
		this.main = forecastHourItem.getMain();
		this.description = forecastHourItem.getDescription();
		this.icon = forecastHourItem.getIcon();

		this.clouds = forecastHourItem.getClouds();

		this.speed = forecastHourItem.getSpeed();
		this.deg = forecastHourItem.getDeg();

		this.rain3h = forecastHourItem.getRain3h();

		this.pod = forecastHourItem.getPod();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getForecastHourId() {
		return forecastHourId;
	}

	public void setForecastHourId(Long forecastHourId) {
		this.forecastHourId = forecastHourId;
	}

	public Date getDt() {
		return dt;
	}

	public void setDt(Date dt) {
		this.dt = dt;
	}

	public String getDtTxt() {
		return dtTxt;
	}

	public void setDtTxt(String dtTxt) {
		this.dtTxt = dtTxt;
	}

	public Double getTemp() {
		return temp;
	}

	public void setTemp(Double temp) {
		this.temp = temp;
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

	public Double getSeaLevel() {
		return seaLevel;
	}

	public void setSeaLevel(Double seaLevel) {
		this.seaLevel = seaLevel;
	}

	public Double getGrndLevel() {
		return grndLevel;
	}

	public void setGrndLevel(Double grndLevel) {
		this.grndLevel = grndLevel;
	}

	public Integer getHumidity() {
		return humidity;
	}

	public void setHumidity(Integer humidity) {
		this.humidity = humidity;
	}

	public Integer getTempKf() {
		return tempKf;
	}

	public void setTempKf(Integer tempKf) {
		this.tempKf = tempKf;
	}

	@JsonProperty("weather")
	public List<Weather> getWeather() {
		return weather;
	}

	@JsonProperty("weather")
	public void setWeather(java.util.List<Weather> weather) {
		this.weather = weather;
		Weather weatherItem = weather.get(0);
		this.weatherId = weatherItem.getId();
		this.main = weatherItem.getMain();
		this.description = weatherItem.getDescription();
		this.icon = weatherItem.getIcon();
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

	public Integer getClouds() {
		return clouds;
	}

	public void setClouds(Integer clouds) {
		this.clouds = clouds;
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

	public Double getRain3h() {
		return rain3h;
	}

	public void setRain3h(Double rain3h) {
		this.rain3h = rain3h;
	}

	public String getPod() {
		return pod;
	}

	public void setPod(String pod) {
		this.pod = pod;
	}
}
