package com.arobs.weather.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.jdto.annotation.Source;

@Entity
@Table(name = "weather_forecast_daily")
public class WeatherForecastDaily {
    @Id
//    @Column(name="id")
//    private Long id;
//    @Column(name="name")
//    private String name;
	@Source( "cod")
    @Column(name="code")
    private String code;
//    @Column(name="dt")
//    private Date dt;
//    @Column(name="base")
//    private String base;
//    @Column(name="visibility")
//    private String visibility;
    
//    @Column(name="lon")
//	@Source( "coord.lon")
//    private BigDecimal lon;
//    @Column(name="lat")
//	@Source( "coord.lat")
//    private BigDecimal lat;
    
//    @Column(name="weather_id")
//	@Source( "weather[0].id")
//    private String weatherId;
//    @Column(name="main")
//	@Source( "weather[0].main")
//    private String main;
//    @Column(name="description")
//	@Source( "weather[0].description")
//    private String description;
//    @Column(name="icon")
//	@Source( "weather[0].icon")
//    private String icon;
    
//    @Column(name="temp")
//	@Source( "main.temp")
//    private Integer temp;
//    @Column(name="pressure")
//	@Source( "main.pressure")
//    private Integer pressure;
//    @Column(name="humidity")
//	@Source( "main.humidity")
//    private Integer humidity;
//    @Column(name="temp_min")
//	@Source( "main.tempMin")
//    private Integer tempMin;
//    @Column(name="temp_max")
//	@Source( "main.tempMax")
//    private Integer tempMax;
    
//    @Column(name="speed")
//	@Source( "wind.speed")
//    private Double speed;
//    @Column(name="deg")
//	@Source( "wind.deg")
//    private Double deg;
    
//    @Column(name="clouds")
//	@Source( "clouds.all")
//    private Integer clouds;
    
//    @Column(name="sys_id")
//	@Source( "sys.id")
//	private Integer sysId;
//    @Column(name="type")
//	@Source( "sys.type")
//	private Integer type;
//    @Column(name="message")
//	@Source( "sys.message")
//	private Double message;
//    @Column(name="country_code")
//	@Source("sys.country")
//	private String countryCode;
//    @Column(name="sunrise")
//	@Source( "sys.sunrise")
//	private Integer sunrise;
//    @Column(name="sunset")
//	@Source( "sys.sunset")
//	private Integer sunset;

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }

//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
    
    public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

//    public Date getDt() {
//        return dt;
//    }
//
//    public void setDt(Date dt) {
//        this.dt = dt;
//    }

//	public String getBase() {
//		return base;
//	}
//
//	public void setBase(String base) {
//		this.base = base;
//	}

//	public String getVisibility() {
//		return visibility;
//	}
//
//	public void setVisibility(String visibility) {
//		this.visibility = visibility;
//	}

//    public BigDecimal getLon() {
//        return lon;
//    }
//
//    public void setLon(BigDecimal lon) {
//        this.lon = lon;
//    }
//
//	public BigDecimal getLat() {
//        return lat;
//    }
//
//    public void setLat(BigDecimal lat) {
//        this.lat = lat;
//    }

//    public String getWeatherId() {
//		return weatherId;
//	}
//
//	public void setWeatherId(String weatherId) {
//		this.weatherId = weatherId;
//	}
//
//	public String getMain() {
//        return main;
//    }
//
//    public void setMain(String main) {
//        this.main = main;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public String getIcon() {
//        return icon;
//    }
//
//    public void setIcon(String icon) {
//        this.icon = icon;
//    }

//    public Integer getTemp() {
//        return temp;
//    }
//
//    public void setTemp(Integer temp) {
//        this.temp = temp;
//    }
//
//    public Integer getPressure() {
//        return pressure;
//    }
//
//    public void setPressure(Integer pressure) {
//        this.pressure = pressure;
//    }
//
//    public Integer getHumidity() {
//        return humidity;
//    }
//
//    public void setHumidity(Integer humidity) {
//        this.humidity = humidity;
//    }
//
//    public Integer getTempMin() {
//		return tempMin;
//	}
//
//	public void setTempMin(Integer tempMin) {
//		this.tempMin = tempMin;
//	}
//
//	public Integer getTempMax() {
//		return tempMax;
//	}
//
//	public void setTempMax(Integer tempMax) {
//		this.tempMax = tempMax;
//	}

//	public Double getSpeed() {
//        return speed;
//    }
//
//    public void setSpeed(Double speed) {
//        this.speed = speed;
//    }
//
//    public Double getDeg() {
//        return deg;
//    }
//
//    public void setDeg(Double deg) {
//        this.deg = deg;
//    }


//	public Integer getClouds() {
//		return clouds;
//	}
//
//	public void setClouds(Integer clouds) {
//		this.clouds = clouds;
//	}

//	public Integer getSysId() {
//		return sysId;
//	}
//
//	public void setSysId(Integer sysId) {
//		this.sysId = sysId;
//	}
//
//	public Integer getType() {
//		return type;
//	}
//
//	public void setType(Integer type) {
//		this.type = type;
//	}
//
//	public Double getMessage() {
//		return message;
//	}
//
//	public void setMessage(Double message) {
//		this.message = message;
//	}
//
//	public String getCountryCode() {
//		return countryCode;
//	}
//
//	public void setCountryCode(String countryCode) {
//		this.countryCode = countryCode;
//	}
//
//	public Integer getSunrise() {
//		return sunrise;
//	}
//
//	public void setSunrise(Integer sunrise) {
//		this.sunrise = sunrise;
//	}
//
//	public Integer getSunset() {
//		return sunset;
//	}
//
//	public void setSunset(Integer sunset) {
//		this.sunset = sunset;
//	}
}
