package com.arobs.weather.entity;

import javax.persistence.*;

import java.io.Serializable;

/**
 * Date istorice. Se transfera zilnic din tabelul "weather_snapshot"
 */
@Entity
@Table(name = "weather_history", uniqueConstraints = {
		@UniqueConstraint(name = "UQ_weather_history_openweather_id", columnNames = {"openweather_id", "day_timestamp"})
	}
)
public class WeatherHistory implements Serializable {
	private static final long serialVersionUID = -8449688319806335527L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "openweather_id")
	private Integer openweatherId;
	@Column(name = "parcel_id")
	private Long parcelId;
	@Column(name = "day_timestamp")
	private Long dayTimestamp;
	@Column(name = "day")
	private Double day;
	@Column(name = "temp_min")
	private Double tempMin;
	@Column(name = "temp_max")
	private Double tempMax;
	@Column(name = "night")
	private Double night;
	@Column(name = "evn")
	private Double evn;
	@Column(name = "morn")
	private Double morn;
	@Column(name = "pressure")
	private Double pressure;
	@Column(name = "humidity")
	private Integer humidity;
	@Column(name = "humidity_air")
	private Integer humidityAir;
	@Column(name = "humidity_soil")
	private Integer humiditySoil;
	@Column(name = "weather_id")
	private Integer weatherId;
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
	@Column(name = "rain_3h")
	private Double rain3h;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getOpenweatherId() {
		return openweatherId;
	}

	public void setOpenweatherId(Integer openweatherId) {
		this.openweatherId = openweatherId;
	}

	public Long getParcelId() {
        return parcelId;
    }

    public void setParcelId(Long parcelId) {
        this.parcelId = parcelId;
    }

	public Long getDayTimestamp() {
		return dayTimestamp;
	}

	public void setDayTimestamp(Long dayTimestamp) {
		this.dayTimestamp = dayTimestamp;
	}

	public Double getDay() {
		return day;
	}

	public void setDay(Double day) {
		this.day = day;
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

	public Double getNight() {
		return night;
	}

	public void setNight(Double night) {
		this.night = night;
	}

	public Double getEvn() {
		return evn;
	}

	public void setEvn(Double evn) {
		this.evn = evn;
	}

	public Double getMorn() {
		return morn;
	}

	public void setMorn(Double morn) {
		this.morn = morn;
	}

	public Double getPressure() {
		return pressure;
	}

	public void setPressure(Double pressure) {
		this.pressure = pressure;
	}

	public Integer getHumidity() {
		return humidity;
	}

	public void setHumidity(Integer humidity) {
		this.humidity = humidity;
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

	public Double getRain3h() {
		return rain3h;
	}

	public void setRain3h(Double rain3h) {
		this.rain3h = rain3h;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
