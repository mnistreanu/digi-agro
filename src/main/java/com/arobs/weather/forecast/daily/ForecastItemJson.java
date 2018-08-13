
package com.arobs.weather.forecast.daily;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"dt", "pressure", "humidity", "temp", "weather"})
public class ForecastItemJson {

    @JsonProperty("dt")
    private Integer dt;
    @JsonProperty("pressure")
    private Double pressure;
    @JsonProperty("humidity")
    private Integer humidity;
    @JsonProperty("temp")
    private Temp temp;
    @JsonProperty("weather")
    private java.util.List<Weather> weather = null;

    @JsonProperty("dt")
    public Date getDt() {
        return new Date(dt * 1000L);
    }

    @JsonProperty("dt")
    public void setDt(Integer dt) {
        this.dt = dt;
    }

    @JsonProperty("pressure")
    public Double getPressure() {
        return pressure;
    }

    @JsonProperty("pressure")
    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    @JsonProperty("humidity")
    public Integer getHumidity() {
        return humidity;
    }

    @JsonProperty("humidity")
    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    @JsonProperty("temp")
    public Temp getTemp() {
        return temp;
    }

    @JsonProperty("temp")
    public void setTemp(Temp temp) {
        this.temp = temp;
    }

    @JsonProperty("weather")
    public java.util.List<Weather> getWeather() {
        return weather;
    }

    @JsonProperty("weather")
    public void setWeather(java.util.List<Weather> weather) {
        this.weather = weather;
    }
}
