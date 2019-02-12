package com.arobs.model.parcel;

import com.arobs.entity.ParcelCropSeason;

import java.util.Date;

public class ParcelCropSeasonModel {

    private Long id;
    private Long parcelId;

    private Long cropCategoryId;
    private Long cropId;
    private Long cropSubcultureId;
    private Long cropVarietyId;
    private Integer harvestYear;

    private Date plantedAt;
    private Integer rowsOnParcel;
    private Integer plantsOnRow;
    private Double spaceBetweenRows;
    private Double spaceBetweenPlants;
    private Double yieldGoal;

    public ParcelCropSeasonModel() {
    }

    public ParcelCropSeasonModel(ParcelCropSeason parcelCropSeason) {
        this.id = parcelCropSeason.getId();
        this.parcelId = parcelCropSeason.getParcelId();

        if (parcelCropSeason.getCropSeason() != null) {
            if (parcelCropSeason.getCropSeason().getCrop() != null) {
                this.cropCategoryId = parcelCropSeason.getCropSeason().getCrop().getCropCategoryId();
                this.cropId = parcelCropSeason.getCropSeason().getCrop().getId();
            }

            if (parcelCropSeason.getCropSeason().getCropSubculture() != null) {
                this.cropSubcultureId = parcelCropSeason.getCropSeason().getCropSubculture().getId();
            }

            this.harvestYear = parcelCropSeason.getCropSeason().getHarvestYear();
        }

        this.cropVarietyId = parcelCropSeason.getCropVarietyId();
        this.plantedAt = parcelCropSeason.getPlantedAt();
        this.rowsOnParcel = parcelCropSeason.getRowsOnParcel();
        this.plantsOnRow = parcelCropSeason.getPlantsOnRow();
        this.spaceBetweenRows = parcelCropSeason.getSpaceBetweenRows();
        this.spaceBetweenPlants = parcelCropSeason.getSpaceBetweenPlants();
        this.yieldGoal = parcelCropSeason.getYieldGoal();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParcelId() {
        return parcelId;
    }

    public void setParcelId(Long parcelId) {
        this.parcelId = parcelId;
    }

    public Long getCropCategoryId() {
        return cropCategoryId;
    }

    public void setCropCategoryId(Long cropCategoryId) {
        this.cropCategoryId = cropCategoryId;
    }

    public Long getCropId() {
        return cropId;
    }

    public void setCropId(Long cropId) {
        this.cropId = cropId;
    }

    public Long getCropSubcultureId() {
        return cropSubcultureId;
    }

    public void setCropSubcultureId(Long cropSubcultureId) {
        this.cropSubcultureId = cropSubcultureId;
    }

    public Long getCropVarietyId() {
        return cropVarietyId;
    }

    public void setCropVarietyId(Long cropVarietyId) {
        this.cropVarietyId = cropVarietyId;
    }

    public Integer getHarvestYear() {
        return harvestYear;
    }

    public void setHarvestYear(Integer harvestYear) {
        this.harvestYear = harvestYear;
    }

    public Date getPlantedAt() {
        return plantedAt;
    }

    public void setPlantedAt(Date plantedAt) {
        this.plantedAt = plantedAt;
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

    public Double getYieldGoal() {
        return yieldGoal;
    }

    public void setYieldGoal(Double yieldGoal) {
        this.yieldGoal = yieldGoal;
    }
}
