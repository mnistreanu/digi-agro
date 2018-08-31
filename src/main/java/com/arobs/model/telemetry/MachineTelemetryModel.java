package com.arobs.model.telemetry;


import com.arobs.entity.MachineTelemetry;

import java.math.BigDecimal;
import java.util.Date;

public class MachineTelemetryModel {

    private Long id;

    private Long machineId;

    private BigDecimal latitude;
    private BigDecimal longitude;

    private Date createdAt;

    public MachineTelemetryModel() {
    }

    public MachineTelemetryModel(MachineTelemetry entity) {
        id = entity.getId();

        machineId = entity.getMachine().getId();

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

    public Long getMachineId() {
        return machineId;
    }

    public void setMachineId(Long machineId) {
        this.machineId = machineId;
    }
}
