package com.arobs.model.forecast;

import com.arobs.entity.forecast.ForecastHarvest;

import java.util.Date;

public class ForecastHarvestModel {

    private Long id;

    private String factorName;
    private Double quantity;
    private Date createdAt;

    public ForecastHarvestModel() {
    }

    public ForecastHarvestModel(ForecastHarvest entity) {
        id = entity.getId();
        factorName = entity.getFactorName();
        quantity = entity.getQuantity();
        createdAt = entity.getCreatedAt();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getFactorName() {
        return factorName;
    }

    public void setFactorName(String factorName) {
        this.factorName = factorName;
    }
}
