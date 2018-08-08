package com.arobs.scheduler.weather.snapshot;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "message", "country", "sunrise", "sunset" })
public class Sys {
	@JsonProperty("message")
	private Double message;
	@JsonProperty("country")
	private String country;
	@JsonProperty("sunrise")
	private Integer sunrise;
	@JsonProperty("sunset")
	private Integer sunset;

	@JsonProperty("message")
	public Double getMessage() {
		return message;
	}

	@JsonProperty("message")
	public void setMessage(Double message) {
		this.message = message;
	}

	@JsonProperty("country")
	public String getCountry() {
		return country;
	}

	@JsonProperty("country")
	public void setCountry(String country) {
		this.country = country;
	}

	@JsonProperty("sunrise")
	public Date getSunrise() {
		return new Date(sunrise * 1000L);
	}

	@JsonProperty("sunrise")
	public void setSunrise(Integer sunrise) {
		this.sunrise = sunrise;
	}

	@JsonProperty("sunset")
	public Date getSunset() {
		return new Date(sunset * 1000L);
	}

	@JsonProperty("sunset")
	public void setSunset(Integer sunset) {
		this.sunset = sunset;
	}
}