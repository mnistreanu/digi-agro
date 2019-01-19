package com.arobs.model.crop;

import com.arobs.entity.CropSeason;

import java.io.Serializable;
import java.util.Date;

public class CropSeasonModel implements Serializable {

    private Long id;
    private Long tenantId;
    private Long cropId;
    private Long cropVarietyId;
    private Integer harvestYear;
    private Date startDate;
    private Date endDate;
    private String comments;

    public CropSeasonModel() {
    }

    public CropSeasonModel(CropSeason season) {
        this.id = season.getId();
        this.tenantId = season.getTenantId();
        this.cropId = season.getCropId();
        this.cropVarietyId = season.getCropVarietyId();
        this.harvestYear = season.getHarvestYear();
        this.startDate = season.getStartDate();
        this.endDate = season.getEndDate();
        this.comments = season.getComments();
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

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
