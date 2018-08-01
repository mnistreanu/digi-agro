package com.arobs.scheduler.weather;

import com.fasterxml.jackson.annotation.JsonProperty;

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
	public Integer getSunrise() {
		return sunrise;
	}

	@JsonProperty("sunrise")
	public void setSunrise(Integer sunrise) {
		this.sunrise = sunrise;
	}

	@JsonProperty("sunset")
	public Integer getSunset() {
		return sunset;
	}

	@JsonProperty("sunset")
	public void setSunset(Integer sunset) {
		this.sunset = sunset;
	}
}