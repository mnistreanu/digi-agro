package com.arobs.model.telemetry;


import com.arobs.entity.MachineTelemetry;

import java.math.BigDecimal;
import java.util.Date;

public class MachineTelemetryModel {

    private Long id;

    private String machineIdentifier;

    private BigDecimal latitude;
    private BigDecimal longitude;

    private Date createdAt;

    public MachineTelemetryModel() {
    }

    public MachineTelemetryModel(MachineTelemetry entity) {
        id = entity.getId();

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
