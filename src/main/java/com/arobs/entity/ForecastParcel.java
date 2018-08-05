package com.arobs.entity;

import javax.persistence.*;

/**
 * Created by mihail.gorgos on 03.08.2018.
 */
@Entity
@Table(name = "forecast_parcel")
public class ForecastParcel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "forecast_snapshot_id")
    private Long forecastSnapshotId;

    @Column(name = "parcel_id")
    private Long parcelId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
