package com.arobs.entity.forecast;

import com.arobs.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by mihail.gorgos on 03.08.2018.
 */
@Entity
@Table(name = "forecast_parcel")
public class ForecastParcel extends BaseEntity {

    @Column(name = "forecast_snapshot_id")
    private Long forecastSnapshotId;

    @Column(name = "parcel_id")
    private Long parcelId;

    public Long getForecastSnapshotId() {
        return forecastSnapshotId;
    }

    public void setForecastSnapshotId(Long forecastSnapshotId) {
        this.forecastSnapshotId = forecastSnapshotId;
    }

    public Long getParcelId() {
        return parcelId;
    }

    public void setParcelId(Long parcelId) {
        this.parcelId = parcelId;
    }
}
