
package com.arobs.weather.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.jdto.annotation.DTOCascade;
import org.jdto.annotation.DTOTransient;
import org.jdto.annotation.Source;

import com.arobs.weather.forecast.hour.Weather;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "forecast_hour_item")
public class ForecastHourItem {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name="id")
    @DTOTransient
    private Long id;
    @Column(name="forecast_hour_id", insertable = false, updatable = false)
    @DTOTransient
    private Long forecastHourId;
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "forecast_hour_id", referencedColumnName = "id", foreignKey=@ForeignKey(name = "FK_Forecast_hour_item_Forecast_hour"))
    private WeatherForecastHour forecastHour;
	@Column(name="dt")
	@Source("dt")
    private Date dt;
	@Column(name="dt_txt")
	@Source("dtTxt")
    private String dtTxt;
	
    @Column(name="temp")
	@Source("main.temp")
    private Double temp;
    @Column(name="temp_min")
	@Source("main.tempMin")
    private Double tempMin;
    @Column(name="temp_max")
	@Source("main.tempMax")
    private Double tempMax;
    @Column(name="pressure")
	@Source("main.pressure")
    private Double pressure;
    @Column(name="sea_level")
	@Source("main.seaLevel")
    private Double seaLevel;
    @Column(name="grnd_level")
	@Source("main.grndLevel")
    private Double grndLevel;
    @Column(name="humidity")
	@Source("main.humidity")
    private Integer humidity;
    @Column(name="temp_kf")
	@Source("main.tempKf")
    private Integer tempKf;
    
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
    
    @Column(name="clouds")
	@Source("clouds.all")
    private Integer clouds;
    
    @Column(name="speed")
	@Source("wind.speed")
    private Double speed;
    @Column(name="deg")
	@Source("wind.deg")
    private Double deg;
    
    @Column(name="rain_3h")
	@Source("rain.3h")
    private Double rain3h;
    
    @Column(name="pod")
	@Source( "sys.pod")
	private String pod;

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
	public WeatherForecastHour getForecastHour() {
		return forecastHour;
	}
	public void setForecastHour(WeatherForecastHour forecastHour) {
		this.forecastHour = forecastHour;
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
