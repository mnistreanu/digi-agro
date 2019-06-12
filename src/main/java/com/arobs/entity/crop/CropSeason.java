package com.arobs.entity.crop;

import com.arobs.entity.BaseEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by mihail.gorgos on 14.07.2019.
 */
@Entity
@Table(name = "crop_season")
public class CropSeason extends BaseEntity {

    @Column(name = "tenant_id")
    private Long tenantId;

    @Column(name = "harvest_year")
    private Integer harvestYear;

    @ManyToOne
    @JoinColumn(name = "crop_id")
    private Crop crop;

    @ManyToOne
    @JoinColumn(name = "crop_subculture_id")
    private CropSubculture cropSubculture;

    @ManyToOne
    @JoinColumn(name = "crop_variety_id")
    private CropVariety cropVariety;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "yield_goal")
    private Double yieldGoal;

    public CropSeason() {
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public Integer getHarvestYear() {
        return harvestYear;
    }

    public void setHarvestYear(Integer harvestYear) {
        this.harvestYear = harvestYear;
    }

    public Crop getCrop() {
        return crop;
    }

    public void setCrop(Crop crop) {
        this.crop = crop;
    }

    public CropSubculture getCropSubculture() {
        return cropSubculture;
    }

    public void setCropSubculture(CropSubculture cropSubculture) {
        this.cropSubculture = cropSubculture;
    }

    public CropVariety getCropVariety() {
        return cropVariety;
    }

    public void setCropVariety(CropVariety cropVariety) {
        this.cropVariety = cropVariety;
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
