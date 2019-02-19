package com.arobs.weather.entity;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Date;

/**
 * Date istorice. Se transfera zilnic din tabelul "weather_snapshot"
 */
@Entity
@Table(name = "weather_history", uniqueConstraints = {
		@UniqueConstraint(name = "UQ_weather_history_id", columnNames = {"weather_location_id", "dt"})
	}
)
public class WeatherHistory implements Serializable {
	private static final long serialVersionUID = -8449688319806335527L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "open_weather_id")
	private Integer openWeatherId;

	@ManyToOne
	@JoinColumn(name = "weather_location_id")
	private WeatherLocation weatherLocation;

	@Column(name = "parcel_id")
	private Long parcelId;

	@Column(name = "dt")
	private Date dt;

	@Column(name = "temp_min")
	private Double tempMin;

	@Column(name = "temp_max")
	private Double tempMax;

	@Column(name = "pressure")
	private Double pressure;

	@Column(name = "humidity_air")
	private Integer humidityAir;

	@Column(name = "humidity_soil")
	private Integer humiditySoil;

	@Column(name = "main")
	private String main;

	@Column(name = "description")
	private String description;

	@Column(name = "icon")
	private String icon;

	@Column(name = "speed")
	private Double speed;

	@Column(name = "deg")
	private Double deg;

	@Column(name = "clouds")
	private Integer clouds;

	@Column(name = "rain")
	private Double rain;

	@Column(name = "snow")
	private Double snow;

	@Column(name = "uvi")
	private Double uvi;


	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getParcelId() {
		return parcelId;
	}

	public void setParcelId(Long parcelId) {
		this.parcelId = parcelId;
	}

	public WeatherLocation getWeatherLocation() {
		return weatherLocation;
	}

	public void setWeatherLocation(WeatherLocation weatherLocation) {
		this.weatherLocation = weatherLocation;
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

	public Integer getOpenWeatherId() {
		return openWeatherId;
	}

	public void setOpenWeatherId(Integer openWeatherId) {
		this.openWeatherId = openWeatherId;
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

	public Double getUvi() {
		return uvi;
	}

	public void setUvi(Double uvi) {
		this.uvi = uvi;
	}
}
