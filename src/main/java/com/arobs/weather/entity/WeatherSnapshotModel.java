package com.arobs.weather.entity;

import java.util.Date;

public class WeatherSnapshotModel {
    private Long id;
    private Integer openweatherId;
    private Long parcelId;
    private String name;
    private Integer cod;
    private Date dt;
    private String base;
    private Double rain3h;
    private Integer sourceId;
    private String countyId;
    private Double lat;
    private Double lon;
    private Integer weatherId;
    private String main;
    private String description;
    private String icon;
    private Double temp;
    private Double pressure;
    private Integer humidity;
    private Integer humidityAir;
    private Integer humiditySoil;
	private Double tempMin;
	private Double tempMax;
	private Double seaLevel;
	private Double grndLevel;
    private Double speed;
    private Double deg;
    private Integer clouds;
	private Integer sysId;
	private Integer sysType;
	private Double message;
	private String countryCode;
	private Date sunrise;
	private Date sunset;
	
	
    
    public WeatherSnapshotModel(WeatherSnapshot weatherSnapshot) {
		super();
		this.id = weatherSnapshot.getId();
		this.openweatherId = weatherSnapshot.getOpenweatherId();
		this.parcelId = weatherSnapshot.getParcelId();
		this.name = weatherSnapshot.getName();
		this.cod = weatherSnapshot.getCod();
		this.dt = weatherSnapshot.getDt();
		this.base = weatherSnapshot.getBase();
		this.rain3h = weatherSnapshot.getRain3h();
		this.sourceId = weatherSnapshot.getSourceId();
		this.countyId = weatherSnapshot.getCountyId();
		this.lat = weatherSnapshot.getLat();
		this.lon = weatherSnapshot.getLon();
		this.weatherId = weatherSnapshot.getWeatherId();
		this.main = weatherSnapshot.getMain();
		this.description = weatherSnapshot.getDescription();
		this.icon = weatherSnapshot.getIcon();
		this.temp = weatherSnapshot.getTemp();
		this.pressure = weatherSnapshot.getPressure();
		this.humidity = weatherSnapshot.getHumidity();
		this.humidityAir = weatherSnapshot.getHumidityAir();
		this.humiditySoil = weatherSnapshot.getHumiditySoil();
		this.tempMin = weatherSnapshot.getTempMin();
		this.tempMax = weatherSnapshot.getTempMax();
		this.seaLevel = weatherSnapshot.getSeaLevel();
		this.grndLevel = weatherSnapshot.getGrndLevel();
		this.speed = weatherSnapshot.getSpeed();
		this.deg = weatherSnapshot.getDeg();
		this.clouds = weatherSnapshot.getClouds();
		this.sysId = weatherSnapshot.getSysId();
		this.sysType = weatherSnapshot.getSysType();
		this.message = weatherSnapshot.getMessage();
		this.countryCode = weatherSnapshot.getCountryCode();
		this.sunrise = weatherSnapshot.getSunrise();
		this.sunset = weatherSnapshot.getSunset();
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public Integer getCod() {
		return cod;
	}

	public void setCod(Integer cod) {
		this.cod = cod;
	}

    public Date getDt() {
        return dt;
    }

    public void setDt(Date dt) {
        this.dt = dt;
    }

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}

	public Double getRain3h() {
        return rain3h;
    }

    public void setRain3h(Double rain3h) {
        this.rain3h = rain3h;
    }

	public Integer getSourceId() {
        return sourceId;
    }

    public void setSourceId(Integer sourceId) {
        this.sourceId = sourceId;
    }

    public String getCountyId() {
        return countyId;
    }

    public void setCountyId(String countyId) {
        this.countyId = countyId;
    }

	public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
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

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
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

    public Integer getSysId() {
		return sysId;
	}

	public void setSysId(Integer sysId) {
		this.sysId = sysId;
	}

	public Integer getSysType() {
		return sysType;
	}

	public void setSysType(Integer sysType) {
		this.sysType = sysType;
	}

	public Double getMessage() {
		return message;
	}

	public void setMessage(Double message) {
		this.message = message;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public Date getSunrise() {
		return sunrise;
	}

	public void setSunrise(Date sunrise) {
		this.sunrise = sunrise;
	}

	public Date getSunset() {
		return sunset;
	}

	public void setSunset(Date sunset) {
		this.sunset = sunset;
	}
}

