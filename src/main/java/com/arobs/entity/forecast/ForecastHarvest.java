package com.arobs.entity.forecast;

import com.arobs.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by mihail.gorgos on 03.08.2018.
 */
@Entity
@Table(name = "forecast_harvest")
public class ForecastHarvest extends BaseEntity {

    @Column(name = "forecast_snapshot_id")
    private Long forecastSnapshotId;

    @Column(name = "factor_name")
    private String factorName;

    @Column(name = "quantity")
    private Double quantity;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "created_by")
    private Long createdBy;

    public Long getForecastSnapshotId() {
        return forecastSnapshotId;
    }

    public void setForecastSnapshotId(Long forecastSnapshotId) {
        this.forecastSnapshotId = forecastSnapshotId;
    }

    public String getFactorName() {
        return factorName;
    }

    public void setFactorName(String factorName) {
        this.factorName = factorName;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }
}
