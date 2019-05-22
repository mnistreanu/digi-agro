package com.arobs.model.chemicals;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class FertilizerApplicationModel implements Serializable {

    private Long id;
    private Long parcelId;
    private Date applicationDate;
    private String comments;
    private Integer placementType;
    private Integer fertilizerType;
    private Long fertilizerId;
    private String fertilizerNameRo;
    private String fertilizerNameRu;
    private BigDecimal tonePrice;
    private Double fertilizedArea;
    private Double rate;
    private String rateUnitOfMeasure;
    private BigDecimal hectareCost;

    public FertilizerApplicationModel() {
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

    public Integer getPlacementType() {
        return placementType;
    }

    public void setPlacementType(Integer placementType) {
        this.placementType = placementType;
    }

    public Integer getFertilizerType() {
        return fertilizerType;
    }

    public void setFertilizerType(Integer fertilizerType) {
        this.fertilizerType = fertilizerType;
    }

    public Long getFertilizerId() {
        return fertilizerId;
    }

    public void setFertilizerId(Long fertilizerId) {
        this.fertilizerId = fertilizerId;
    }

    public String getFertilizerNameRo() {
        return fertilizerNameRo;
    }

    public void setFertilizerNameRo(String fertilizerNameRo) {
        this.fertilizerNameRo = fertilizerNameRo;
    }

    public String getFertilizerNameRu() {
        return fertilizerNameRu;
    }

    public void setFertilizerNameRu(String fertilizerNameRu) {
        this.fertilizerNameRu = fertilizerNameRu;
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
