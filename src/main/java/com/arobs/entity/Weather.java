package com.arobs.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by mihail.gorgos on 31.07.2018.
 */
@Entity
@Table(name = "weather")
public class Weather {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="parcel_id")
    private Long parcelId;

    @Column(name="lat")
    private BigDecimal lat;

    @Column(name="lon")
    private BigDecimal lon;

    @Column(name="main")
    private String main;

    @Column(name="description")
    private String description;

    @Column(name="icon")
    private String icon;

    @Column(name="temp")
    private Integer temp;

    @Column(name="pressure")
    private Integer pressure;

    @Column(name="humidity_air")
    private Integer humidityAir;

    @Column(name="humidity_soil")
    private Integer humiditySoil;

    @Column(name="wind_speed")
    private Double windSpeed;

    @Column(name="wind_deg")
    private BigDecimal windDeg;

    /**
     * Rain during last 3 hours
     */
    @Column(name="rain_3h")
    private BigDecimal rain3h;

    @Column(name="clouds")
    private Integer clouds;

    @Column(name="sourceId")
    private Integer sourceId;

    @Column(name="dt")
    private Date dt;

    @Column(name="countyId")
    private String countyId;

    @Column(name="countryId")
    private String countryId;

    @Column(name="name")
    private String name;

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

    public Integer getTemp() {
        return temp;
    }

    public void setTemp(Integer temp) {
        this.temp = temp;
    }

    public Integer getPressure() {
        return pressure;
    }

    public void setPressure(Integer pressure) {
        this.pressure = pressure;
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

    public Double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(Double windSpeed) {
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

    public String getCountyId() {
        return countyId;
    }

    public void setCountyId(String countyId) {
        this.countyId = countyId;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

