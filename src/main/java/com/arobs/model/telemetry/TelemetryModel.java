package com.arobs.model.telemetry;


import com.arobs.entity.Telemetry;

import java.math.BigDecimal;
import java.util.Date;

public class TelemetryModel {

    private Long id;

    private String username;
    private String machineIdentifier;

    private BigDecimal latitude;
    private BigDecimal longitude;

    private Date createdAt;

    public TelemetryModel() {
    }

    public TelemetryModel(Telemetry entity) {
        id = entity.getId();

        username = entity.getUserAccount().getUsername();
        machineIdentifier = entity.getMachine().getIdentifier();

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMachineIdentifier() {
        return machineIdentifier;
    }

    public void setMachineIdentifier(String machineIdentifier) {
        this.machineIdentifier = machineIdentifier;
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
}
