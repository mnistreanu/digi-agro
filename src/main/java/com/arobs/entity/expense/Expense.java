package com.arobs.entity.expense;

import com.arobs.entity.BaseEntity;
import com.arobs.entity.crop.CropSeason;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class Expense extends BaseEntity {

    @Column(name = "tenant_id")
    private Long tenant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "crop_season_id")
    private CropSeason cropSeason;
    @Column(name = "crop_season_id", insertable = false, updatable = false)
    private Long cropSeasonId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private ExpenseCategory category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sub_category_id")
    private ExpenseCategory subCategory;

    private Date date;
    private String title;
    private String description;
    private BigDecimal cost;

    public Long getTenant() {
        return tenant;
    }

    public void setTenant(Long tenant) {
        this.tenant = tenant;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public CropSeason getCropSeason() {
        return cropSeason;
    }

    public void setCropSeason(CropSeason cropSeason) {
        this.cropSeason = cropSeason;
        this.cropSeasonId = cropSeason == null ? null : cropSeason.getId();
    }

    public Long getCropSeasonId() {
        return cropSeasonId;
    }

    public void setCropSeasonId(Long cropSeasonId) {
        this.cropSeasonId = cropSeasonId;
    }

    public ExpenseCategory getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(ExpenseCategory subCategory) {
        this.subCategory = subCategory;
    }

    public ExpenseCategory getCategory() {
        return category;
    }

    public void setCategory(ExpenseCategory category) {
        this.category = category;
    }
}
