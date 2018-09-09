package com.arobs.weather.entity;

/**
 * Date istorice. Se transfera zilnic din tabelul "weather_snapshot"
 */
public class WeatherHistoryModel {
	private Long id;
	private Integer openweatherId;
	private Integer locationId;
	private Long parcelId;
	private Long dayTimestamp;
	private Double day;
	private Double tempMin;
	private Double tempMax;
	private Double night;
	private Double evn;
	private Double morn;
	private Double pressure;
	private Integer humidity;
	private Integer humidityAir;
	private Integer humiditySoil;
	private Integer weatherId;
	private String main;
	private String description;
	private String icon;
	private Double speed;
	private Double deg;
	private Integer clouds;
	private Double rain3h;
	
	public WeatherHistoryModel(WeatherHistory weatherHistory) {
		super();
		this.id = weatherHistory.getId();
		this.openweatherId = weatherHistory.getOpenweatherId();
		this.parcelId = weatherHistory.getParcelId();
		this.dayTimestamp = weatherHistory.getDayTimestamp();
		this.day = weatherHistory.getDay();
		this.tempMin = weatherHistory.getTempMin();
		this.tempMax = weatherHistory.getTempMax();
		this.night = weatherHistory.getNight();
		this.evn = weatherHistory.getEvn();
		this.morn = weatherHistory.getMorn();
		this.pressure = weatherHistory.getPressure();
		this.humidity = weatherHistory.getHumidity();
		this.humidityAir = weatherHistory.getHumidityAir();
		this.humiditySoil = weatherHistory.getHumiditySoil();
		this.weatherId = weatherHistory.getWeatherId();
		this.main = weatherHistory.getMain();
		this.description = weatherHistory.getDescription();
		this.icon = weatherHistory.getIcon();
		this.speed = weatherHistory.getSpeed();
		this.deg = weatherHistory.getDeg();
		this.clouds = weatherHistory.getClouds();
		this.rain3h = weatherHistory.getRain3h();
	}

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
}

