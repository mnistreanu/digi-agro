package com.arobs.model.forecast;

import com.arobs.entity.Forecast;

public class ForecastModel {

    private Long id;
    private String name;
    private String description;
    private Integer harvestingYear;

    private Long cropCategoryId;
    private Long cropId;
    private Long cropVarietyId;

    private ForecastSnapshotModel snapshot;

    public ForecastModel() {
    }

    public ForecastModel(Forecast forecast) {
        this.id = forecast.getId();
        this.name = forecast.getName();
        this.description = forecast.getDescription();
        this.harvestingYear = forecast.getHarvestingYear();

        this.cropCategoryId = forecast.getCropCategoryId();
        this.cropId = forecast.getCropId();
        this.cropVarietyId = forecast.getCropVarietyId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getCropVarietyId() {
        return cropVarietyId;
    }

    public void setCropVarietyId(Long cropVarietyId) {
        this.cropVarietyId = cropVarietyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getHarvestingYear() {
        return harvestingYear;
    }

    public void setHarvestingYear(Integer harvestingYear) {
        this.harvestingYear = harvestingYear;
    }

    public ForecastSnapshotModel getSnapshot() {
        return snapshot;
    }

    public void setSnapshot(ForecastSnapshotModel snapshot) {
        this.snapshot = snapshot;
    }
}
