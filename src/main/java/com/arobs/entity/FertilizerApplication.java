package com.arobs.entity;

import com.arobs.entity.parcel.Parcel;
import com.arobs.enums.FertilizerType;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by mihail.gorgos on 15.07.2019
 */
@Entity
@Table(name = "fertilizer_application")
public class FertilizerApplication extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "parcel_id")
    private Parcel parcel;

    @Column(name = "application_date")
    private Date applicationDate;

    @Column(name = "comments")
    private String comments;

    @Column(name = "placement_type")
    private String placementType;

    @ManyToOne
    @JoinColumn(name = "fertilizer_id")
    private Fertilizer fertilizer;

    @Column(name = "tone_price")
    private BigDecimal tonePrice;

    @Column(name = "fertilized_area")
    private Double fertilizedArea;

    @Column(name = "rate")
    private Double rate;

    @Column(name = "rate_unit_of_measure")
    private String rateUnitOfMeasure;

    @Column(name = "hectare_cost")
    private BigDecimal hectareCost;

//    @Enumerated(EnumType.STRING)
//    private FertilizerType fertilizerType;

    public FertilizerApplication() {
    }

    public Parcel getParcel() {
        return parcel;
    }

    public void setParcel(Parcel parcel) {
        this.parcel = parcel;
    }

    public Date getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getPlacementType() {
        return placementType;
    }

    public void setPlacementType(String placementType) {
        this.placementType = placementType;
    }

    public Fertilizer getFertilizer() {
        return fertilizer;
    }

    public void setFertilizer(Fertilizer fertilizer) {
        this.fertilizer = fertilizer;
    }

    public BigDecimal getTonePrice() {
        return tonePrice;
    }

    public void setTonePrice(BigDecimal tonePrice) {
        this.tonePrice = tonePrice;
    }

    public Double getFertilizedArea() {
        return fertilizedArea;
    }

    public void setFertilizedArea(Double fertilizedArea) {
        this.fertilizedArea = fertilizedArea;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public String getRateUnitOfMeasure() {
        return rateUnitOfMeasure;
    }

    public void setRateUnitOfMeasure(String rateUnitOfMeasure) {
        this.rateUnitOfMeasure = rateUnitOfMeasure;
    }

    public BigDecimal getHectareCost() {
        return hectareCost;
    }

    public void setHectareCost(BigDecimal hectareCost) {
        this.hectareCost = hectareCost;
    }
}
