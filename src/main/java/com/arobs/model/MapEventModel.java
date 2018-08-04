package com.arobs.model;


import com.arobs.entity.MapEvent;

import java.math.BigDecimal;
import java.util.Date;

public class MapEventModel {

    private Long id;
    private String message;

    private BigDecimal latitude;
    private BigDecimal longitude;

    private Date createdAt;

    public MapEventModel() {
    }

    public MapEventModel(MapEvent entity) {
        id = entity.getId();
        message = entity.getMessage();

        longitude = entity.getLongitude();
        latitude = entity.getLatitude();

        createdAt = entity.getCreatedAt();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
