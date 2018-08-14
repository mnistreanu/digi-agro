package com.arobs.model;


import com.arobs.entity.Weather;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class WeatherModel implements Serializable {

    private Long id;

    private Long parcelId;

    private BigDecimal lat;

    private BigDecimal lon;

    private String main;

    private String description;

    private String icon;

    private Integer tempMax;

    private Integer tempMin;

    private Integer pressure;

    private Integer humidityAir;

    private Integer humiditySoil;

    private Double windSpeed;

    private BigDecimal windDeg;

    /**
     * Rain during last 3 hours
     */
    private BigDecimal rain3h;

    private Integer clouds;

    private Integer sourceId;

    private Date dt;

    private String countyId;

    private String countryId;

    private String name;

    public WeatherModel () {

    }

    public WeatherModel (Weather w) {
        this.id = w.getId();
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

    public Integer getTempMax() {
        return tempMax;
    }

    public void setTempMax(Integer tempMax) {
        this.tempMax = tempMax;
    }

    public Integer getTempMin() {
        return tempMin;
    }

    public void setTempMin(Integer tempMin) {
        this.tempMin = tempMin;
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
