
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

import com.arobs.weather.forecast.daily.Weather;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "weather_forecast_daily_item")
public class ForecastDailyItem {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column(name="id")
    @DTOTransient
    private Long id;
    @Column(name="forecast_daily_id", insertable = false, updatable = false)
    @DTOTransient
    private Long forecastDailyId;
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "forecast_daily_id", referencedColumnName = "id", foreignKey=@ForeignKey(name = "FK_Forecast_daily_item_Forecast_daily"))
    private WeatherForecastDaily forecastDaily;
	@Column(name="dt")
	@Source("dt")
    private Date dt;
    @Column(name="pressure")
	@Source("pressure")
    private Double pressure;
    @Column(name="humidity")
	@Source("humidity")
    private Integer humidity;
    
    @Column(name="day")
	@Source("temp.day")
    private Double day;
    @Column(name="min")
	@Source("temp.min")
    private Double min;
    @Column(name="max")
	@Source("temp.max")
    private Double max;
    @Column(name="night")
	@Source("temp.night")
    private Double night;
    @Column(name="eve")
	@Source("temp.eve")
    private Double eve;
    @Column(name="morn")
	@Source("temp.morn")
    private Double morn;
    
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

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getForecastDailyId() {
		return forecastDailyId;
	}

	public void setForecastDailyId(Long forecastDailyId) {
		this.forecastDailyId = forecastDailyId;
	}

	public WeatherForecastDaily getForecastDaily() {
		return forecastDaily;
	}

	public void setForecastDaily(WeatherForecastDaily forecastDaily) {
		this.forecastDaily = forecastDaily;
	}

    public Date getDt() {
        return dt;
    }

    public void setDt(Date dt) {
        this.dt = dt;
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

    public Double getDay() {
		return day;
	}

	public void setDay(Double day) {
		this.day = day;
	}

	public Double getMin() {
		return min;
	}

	public void setMin(Double min) {
		this.min = min;
	}

	public Double getMax() {
		return max;
	}

	public void setMax(Double max) {
		this.max = max;
	}

	public Double getNight() {
		return night;
	}

	public void setNight(Double night) {
		this.night = night;
	}

	public Double getEve() {
		return eve;
	}

	public void setEve(Double eve) {
		this.eve = eve;
	}

	public Double getMorn() {
		return morn;
	}

	public void setMorn(Double morn) {
		this.morn = morn;
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
}
