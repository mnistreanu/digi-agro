package com.arobs.model.crop;

import com.arobs.entity.CropSeason;

import java.io.Serializable;
import java.util.Date;

public class CropSeasonModel implements Serializable {

    private Long id;
    private Long tenantId;
    private Integer harvestYear;
    private Long cropId;
    private CropModel cropModel;
    private Long cropVarietyId;
    private CropVarietyModel cropVarietyModel;
    private Date startDate;
    private Date endDate;
    private Double yieldGoal;

    public CropSeasonModel() {
    }

    public CropSeasonModel(CropSeason season) {
        this.id = season.getId();
        this.tenantId = season.getTenantId();
        this.harvestYear = season.getHarvestYear();
        if (season.getCrop() != null) {
            this.cropId = season.getCrop().getId();
            this.cropModel = new CropModel(season.getCrop());
        }
        if (season.getCropVariety() != null) {
            this.cropVarietyId = season.getCropVariety().getId();
            this.cropVarietyModel = new CropVarietyModel(season.getCropVariety());
        }
        this.startDate = season.getStartDate();
        this.endDate = season.getEndDate();
        this.yieldGoal = season.getYieldGoal();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
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

    public CropVarietyModel getCropVarietyModel() {
        return cropVarietyModel;
    }

    public void setCropVarietyModel(CropVarietyModel cropVarietyModel) {
        this.cropVarietyModel = cropVarietyModel;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Double getYieldGoal() {
        return yieldGoal;
    }

    public void setYieldGoal(Double yieldGoal) {
        this.yieldGoal = yieldGoal;
    }
}
