package com.arobs.model.parcel;

import com.arobs.entity.ParcelCropSeason;
import com.arobs.model.crop.CropModel;
import com.arobs.model.crop.CropVarietyModel;

import java.util.Date;

public class ParcelCropSeasonModel {

    private Long id;
    private Long parcelId;
    private String parcelName;
    private String cadasterNumber;
    private Double area;
    private Boolean irrigated;

    private Long cropSeasonId;

    private Long cropCategoryId;
    private Long cropId;
    private CropModel cropModel;

    private Long cropSubcultureId;
    private Long cropVarietyId;
    private CropVarietyModel cropVarietyModel;
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

        if (parcelCropSeason.getParcel() != null) {
            this.parcelName = parcelCropSeason.getParcel().getName();
            this.cadasterNumber = parcelCropSeason.getParcel().getCadasterNumber();
            this.area = parcelCropSeason.getParcel().getArea();
            this.irrigated = parcelCropSeason.getParcel().isIrrigated();
        }

        if (parcelCropSeason.getCropSeason() != null) {
            this.cropSeasonId = parcelCropSeason.getCropSeason().getId();

            if (parcelCropSeason.getCropSeason().getCrop() != null) {
                this.cropModel = new CropModel(parcelCropSeason.getCropSeason().getCrop());
                this.cropCategoryId = parcelCropSeason.getCropSeason().getCrop().getCropCategoryId();
                this.cropId = parcelCropSeason.getCropSeason().getCrop().getId();
            }

            if (parcelCropSeason.getCropSeason().getCropSubculture() != null) {
                this.cropSubcultureId = parcelCropSeason.getCropSeason().getCropSubculture().getId();
            }

            if (parcelCropSeason.getCropSeason().getCropVariety() != null) {
                this.cropVarietyModel = new CropVarietyModel(parcelCropSeason.getCropSeason().getCropVariety());
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

    public String getParcelName() {
        return parcelName;
    }

    public void setParcelName(String parcelName) {
        this.parcelName = parcelName;
    }

    public String getCadasterNumber() {
        return cadasterNumber;
    }

    public void setCadasterNumber(String cadasterNumber) {
        this.cadasterNumber = cadasterNumber;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public Boolean getIrrigated() {
        return irrigated;
    }

    public void setIrrigated(Boolean irrigated) {
        this.irrigated = irrigated;
    }

    public Long getCropSeasonId() {
        return cropSeasonId;
    }

    public void setCropSeasonId(Long cropSeasonId) {
        this.cropSeasonId = cropSeasonId;
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

    public CropModel getCropModel() {
        return cropModel;
    }

    public void setCropModel(CropModel cropModel) {
        this.cropModel = cropModel;
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

    public CropVarietyModel getCropVarietyModel() {
        return cropVarietyModel;
    }

    public void setCropVarietyModel(CropVarietyModel cropVarietyModel) {
        this.cropVarietyModel = cropVarietyModel;
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
