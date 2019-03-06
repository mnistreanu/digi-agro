package com.arobs.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Long id;

    @Column(name = "tenant_id")
    private Long tenant;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "crop_season_id")
    private CropSeason cropSeason;
    @Column(name = "crop_season_id", insertable = false, updatable = false)
    private Long cropSeasonId;

    private Long categoryId;

    private Date date;
    private String title;
    private String description;
    private BigDecimal cost;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTenant() {
        return tenant;
    }

    public void setTenant(Long tenant) {
        this.tenant = tenant;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
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
    }

    public Long getCropSeasonId() {
        return cropSeasonId;
    }

    public void setCropSeasonId(Long cropSeasonId) {
        this.cropSeasonId = cropSeasonId;
    }
}
