package com.arobs.weather.entity;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Date istorice. Se transfera zilnic din tabelul "weather_snapshot"
 */
@Entity
@Table(name = "weather_history",  uniqueConstraints = {@UniqueConstraint(name="UQ_weather_history_openweather_id", columnNames = {"day_timestamp", "openweather_id"})})
public class WeatherHistory implements Serializable {
	private static final long serialVersionUID = -649436542429821841L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="day_timestamp")
    private Long dayTimestamp;
    @Column(name="openweather_id")
    private Integer openweatherId;
    @Column(name="parcel_id")
    private Long parcelId;
    @Column(name="name")
    private String name;
    @Column(name="cod")
    private Integer cod;
    @Column(name="source_id")
    private Integer sourceId;
    @Column(name="county_id")
    private String countyId;
    
    @Column(name="lat")
    private Double lat;
    @Column(name="lon")
    private Double lon;

    @Column(name="sys_id")
	private Integer sysId;
    @Column(name="sys_type")
	private Integer sysType;
    @Column(name="message")
	private Double message;
    @Column(name="country_code")
	private String countryCode;
    @Column(name="sunrise")
	private Date sunrise;
    @Column(name="sunset")
	private Date sunset;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "weatherHistory")
    private List<WeatherHistoryItem> historyItems;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public Long getDayTimestamp() {
		return dayTimestamp;
	}

	public void setDayTimestamp(Long dayTimestamp) {
		this.dayTimestamp = dayTimestamp;
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

	public List<WeatherHistoryItem> getHistoryItems() {
		return historyItems;
	}

	public void setHistoryItems(List<WeatherHistoryItem> historyItems) {
		this.historyItems = historyItems;
	}
}

