package com.arobs.entity.parcel;

import com.arobs.entity.BaseEntity;
import com.arobs.entity.crop.CropSeason;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by mihail.gorgos on 25.07.2018.
 * Aici se va defini relatie parcela si cultura agricola care creste pe aceasta parcela.
 * Deja lucrarile efectuate pentru aceasta parcela se vor defini in alte tabele.
 */
@Entity
@Table(name = "parcel_crop_season")
public class ParcelCropSeason extends BaseEntity {

    @Column(name = "parcel_id")
    private Long parcelId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(insertable = false, updatable = false)
    private Parcel parcel;

    @Column(name = "crop_season_id")
    private Long cropSeasonId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(insertable = false, updatable = false)
    private CropSeason cropSeason;

    @Column(name = "crop_variety_id")
    private Long cropVarietyId;

    @Column(name = "planted_at")
    private Date plantedAt;

    @Column(name = "rows_on_parcel")
    private Integer rowsOnParcel;

    @Column(name = "plants_on_row")
    private Integer plantsOnRow;

    @Column(name = "space_between_rows")
    private Double spaceBetweenRows;

    @Column(name = "space_between_plants")
    private Double spaceBetweenPlants;

    @Column(name = "yield_goal")
    private Double yieldGoal;

    public Long getParcelId() {
        return parcelId;
    }

    public void setParcelId(Long parcelId) {
        this.parcelId = parcelId;
    }

    public Parcel getParcel() {
        return parcel;
    }

    public void setParcel(Parcel parcel) {
        this.parcel = parcel;
    }

    public Long getCropSeasonId() {
        return cropSeasonId;
    }

    public void setCropSeasonId(Long cropSeasonId) {
        this.cropSeasonId = cropSeasonId;
    }

    public CropSeason getCropSeason() {
        return cropSeason;
    }

    public void setCropSeason(CropSeason cropSeason) {
        this.cropSeason = cropSeason;
    }

    public Long getCropVarietyId() {
        return cropVarietyId;
    }

    public void setCropVarietyId(Long cropVarietyId) {
        this.cropVarietyId = cropVarietyId;
    }

    public Integer getRowsOnParcel() {
        return rowsOnParcel;
    }

    public void setRowsOnParcel(Integer rowsOnParcel) {
        this.rowsOnParcel = rowsOnParcel;
    }

    public Integer getPlantsOnRow() {
        return plantsOnRow;
    }

    public void setPlantsOnRow(Integer plantsOnRow) {
        this.plantsOnRow = plantsOnRow;
    }

    public Double getSpaceBetweenRows() {
        return spaceBetweenRows;
    }

    public void setSpaceBetweenRows(Double spaceBetweenRows) {
        this.spaceBetweenRows = spaceBetweenRows;
    }

    public Double getSpaceBetweenPlants() {
        return spaceBetweenPlants;
    }

    public void setSpaceBetweenPlants(Double spaceBetweenPlants) {
        this.spaceBetweenPlants = spaceBetweenPlants;
    }

    public Date getPlantedAt() {
        return plantedAt;
    }

    public void setPlantedAt(Date plantedAt) {
        this.plantedAt = plantedAt;
    }

    public Double getYieldGoal() {
        return yieldGoal;
    }

    public void setYieldGoal(Double yieldGoal) {
        this.yieldGoal = yieldGoal;
    }
}
