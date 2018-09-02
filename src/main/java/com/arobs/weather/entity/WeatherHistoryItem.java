package com.arobs.weather.entity;

import com.arobs.weather.snapshot.Weather;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Date istorice. Se transfera zilnic din tabelul "weather_snapshot"
 */
@Entity
@Table(name = "weather_history_item")
public class WeatherHistoryItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="day_timestamp")
    private Long dayTimestamp;
    @Column(name="openweather_id", insertable = true, updatable = true)
    private Integer openweatherId;
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumns(
    	value = {
			@JoinColumn(name = "day_timestamp", referencedColumnName = "day_timestamp", insertable = false, updatable = false),
			@JoinColumn(name = "openweather_id", referencedColumnName = "openweather_id", insertable = false, updatable = false)
    	},
		foreignKey=@ForeignKey(name = "FK_history_item_history")
    )
    private WeatherHistory weatherHistory;  
    @Column(name="dt")
    private Date dt;
    @Column(name="base")
    private String base;
    @Column(name="rain_3h")
    private Double rain3h;
    
    @Transient
    private List<Weather> weather;

    @Column(name="weather_id")
    private Integer weatherId;
    @Column(name="main")
    private String main;
    @Column(name="description")
    private String description;
    @Column(name="icon")
    private String icon;

    @Column(name="temp")
    private Double temp;
    @Column(name="pressure")
    private Double pressure;
    @Column(name="humidity")
    private Integer humidity;
    @Column(name="humidity_air")
    private Integer humidityAir;
    @Column(name="humidity_soil")
    private Integer humiditySoil;
    @Column(name="temp_min")
	private Double tempMin;
    @Column(name="temp_max")
	private Double tempMax;
    @Column(name="sea_level")
	private Double seaLevel;
    @Column(name="grnd_level")
	private Double grndLevel;

    @Column(name="speed")
    private Double speed;
    @Column(name="deg")
    private Double deg;

    @Column(name="clouds")
    private Integer clouds;

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
}

