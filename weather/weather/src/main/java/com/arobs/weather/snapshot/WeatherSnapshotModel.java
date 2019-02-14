package com.arobs.weather.snapshot;


import com.arobs.weather.entity.WeatherSnapshot;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class WeatherSnapshotModel implements Serializable {
	private static final long serialVersionUID = -7674789177108074899L;

	private Long id;
    private Long parcelId;
    private BigDecimal lat;
    private BigDecimal lon;
    private Integer weatherId;
    private String main;
    private String description;
    private String icon;
    private Double temp;
    private Double pressure;
    private Integer humidity;
    private Double tempMin;
    private Double tempMax;
    private Double seaLevel;
    private Double grndLevel;
    private BigDecimal windSpeed;
    private BigDecimal windDeg;
    /** Rain during last 3 hours */
    private BigDecimal rain3h;
    private Integer clouds;
    private BigDecimal message;
    private Integer sourceId;
    private Date dt;
    private String country;
    private Date sunrise;
    private Date sunset;
    private Integer code;
    private String name;
    private String base;
    public WeatherSnapshotModel () {
    }

    public WeatherSnapshotModel(WeatherSnapshot w) {
        this.id = w.getId();
    }

    public static WeatherSnapshotModel buildWeatherModel(WeatherSnapshotJson weather) {
    	WeatherSnapshotModel weatherModel = new WeatherSnapshotModel(); 
    	
    	weatherModel.setId(weather.getId().longValue());
    	weatherModel.setName(weather.getName());
    	weatherModel.setCode(weather.getCod());
    	weatherModel.setBase(weather.getBase());
    	weatherModel.setDt(weather.getDt());
    	
    	buildWeatherModel(weatherModel, weather.getWeather().get(0));
    	buildWeatherModel(weatherModel, weather.getCoord());
    	buildWeatherModel(weatherModel, weather.getMain());
    	buildWeatherModel(weatherModel, weather.getWind());
    	buildWeatherModel(weatherModel, weather.getClouds());
    	buildWeatherModel(weatherModel, weather.getSys());
    	
    	return weatherModel;
    }
    
    private static void buildWeatherModel(WeatherSnapshotModel weatherModel, Coord coord) {
    	weatherModel.setLon(BigDecimal.valueOf(coord.getLon()));
    	weatherModel.setLat(BigDecimal.valueOf(coord.getLat()));
    }

    private static void buildWeatherModel(WeatherSnapshotModel weatherModel, Weather weather) {
    	weatherModel.setWeatherId(weather.getId());
    	weatherModel.setMain(weather.getMain());
    	weatherModel.setDescription(weather.getDescription());
    	weatherModel.setIcon(weather.getIcon());
    }

    //temp, pressure, humidity, temp_min, temp_max, sea_level, grnd_level
    private static void buildWeatherModel(WeatherSnapshotModel weatherModel, Main main) {
    	weatherModel.setTemp(main.getTemp());
    	weatherModel.setPressure(main.getPressure());
    	weatherModel.setHumidity(main.getHumidity());
    	weatherModel.setTempMin(main.getTempMin());
    	weatherModel.setTempMax(main.getTempMax());
    	weatherModel.setSeaLevel(main.getSeaLevel());
    	weatherModel.setGrndLevel(main.getGrndLevel());
    }

    private static void buildWeatherModel(WeatherSnapshotModel weatherModel, Wind wind) {
    	weatherModel.setWindSpeed(BigDecimal.valueOf(wind.getSpeed()));
    	weatherModel.setWindDeg(BigDecimal.valueOf(wind.getDeg()));
    }

    private static void buildWeatherModel(WeatherSnapshotModel weatherModel, Clouds clouds) {
    	weatherModel.setClouds(clouds.getAll());
    }

    private static void buildWeatherModel(WeatherSnapshotModel weatherModel, Sys sys) {
    	weatherModel.setMessage(BigDecimal.valueOf(sys.getMessage()));
    	weatherModel.setCountry(sys.getCountry());
    	weatherModel.setSunrise(sys.getSunrise());
    	weatherModel.setSunset(sys.getSunset());
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

    public BigDecimal getLat() {
        return lat;
    }

    public void setLat(BigDecimal lat) {
        this.lat = lat;
    }

    public BigDecimal getLon() {
        return lon;
    }

    public void setLon(BigDecimal lon) {
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

//    public Integer getTempMin() {
//        return tempMin;
//    }
//
//    public void setTempMin(Integer tempMin) {
//        this.tempMin = tempMin;
//    }
//
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

    public BigDecimal getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(BigDecimal windSpeed) {
        this.windSpeed = windSpeed;
    }

    public BigDecimal getWindDeg() {
        return windDeg;
    }

    public void setWindDeg(BigDecimal windDeg) {
        this.windDeg = windDeg;
    }

    public BigDecimal getRain3h() {
        return rain3h;
    }

    public void setRain3h(BigDecimal rain3h) {
        this.rain3h = rain3h;
    }

    public Integer getClouds() {
        return clouds;
    }

    public void setClouds(Integer clouds) {
        this.clouds = clouds;
    }

    public BigDecimal getMessage() {
		return message;
	}

	public void setMessage(BigDecimal message) {
		this.message = message;
	}

	public Integer getSourceId() {
        return sourceId;
    }

    public void setSourceId(Integer sourceId) {
        this.sourceId = sourceId;
    }

    public Date getDt() {
        return dt;
    }

    public void setDt(Date dt) {
        this.dt = dt;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}
}
