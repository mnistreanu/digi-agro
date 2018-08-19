package com.arobs.weather.entity;

import javax.persistence.*;

import org.jdto.annotation.DTOCascade;
import org.jdto.annotation.DTOTransient;
import org.jdto.annotation.Source;

import com.arobs.weather.snapshot.Weather;

import java.util.Date;
import java.util.List;

/**
 * Date istorice. Se transfera zilnic din tabelul "weather_snapshot"
 */
@Entity
@Table(name = "weather_history")
public class WeatherHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    @DTOTransient
    private Long id;
    @Column(name="openweatherId")
	@Source("id")
    private Integer openweatherId;
    @Column(name="parcel_id")
    @DTOTransient
    private Long parcelId;
    @Column(name="name")
    private String name;
    @Column(name="cod")
    private Integer cod;
    @Column(name="dt")
    private Date dt;
    @Column(name="base")
    private String base;
    @Column(name="rain_3h")
	@Source("rain.3h")
    private Double rain3h;
    @Column(name="source_id")
    @DTOTransient
    private Integer sourceId;
    @Column(name="countyId")
	@DTOTransient
    private String countyId;
    
    @Column(name="lat")
	@Source("coord.lat")
    private Double lat;
    @Column(name="lon")
	@Source("coord.lon")
    private Double lon;

    @Transient
    @DTOCascade
	@Source("weather")
    private List<Weather> weather;

    @Column(name="weather_id")
	@DTOTransient
    private Integer weatherId;
    @Column(name="main")
	@DTOTransient
    private String main;
    @Column(name="description")
	@DTOTransient
    private String description;
    @Column(name="icon")
	@DTOTransient
    private String icon;

    @Column(name="temp")
	@Source( "main.temp")
    private Double temp;
    @Column(name="pressure")
	@Source( "main.pressure")
    private Double pressure;
    @Column(name="humidity")
	@Source( "main.humidity")
    private Integer humidity;
    @Column(name="humidity_air")
	@DTOTransient
    private Integer humidityAir;
    @Column(name="humidity_soil")
	@DTOTransient
    private Integer humiditySoil;
    @Column(name="temp_min")
	@Source("main.tempMin")
	private Double tempMin;
    @Column(name="temp_max")
	@Source("main.tempMax")
	private Double tempMax;
    @Column(name="sea_level")
	@Source("main.seaLevel")
	private Double seaLevel;
    @Column(name="grnd_level")
	@Source("main.grndLevel")
	private Double grndLevel;

    @Column(name="speed")
    @Source("wind.speed")
    private Double speed;
    @Column(name="deg")
    @Source("wind.deg")
    private Double deg;

    @Column(name="clouds")
	@Source( "clouds.all")
    private Integer clouds;

    @Column(name="message")
	@Source( "sys.message")
	private Double message;
    @Column(name="country_code")
	@Source("sys.country")
	private String countryCode;
    @Column(name="sunrise")
	@Source( "sys.sunrise")
	private Date sunrise;
    @Column(name="sunset")
	@Source( "sys.sunset")
	private Date sunset;
    
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

    public List<Weather> getWeather() {
		return weather;
	}

	public void setWeather(List<Weather> weather) {
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

