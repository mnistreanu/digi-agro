package com.arobs.model.forecast;

import com.arobs.entity.ForecastSnapshot;

import java.util.List;

public class ForecastSnapshotModel {

    private Long id;

    private String unitOfMeasure;
    private String currency;
    private Double unitPrice;

    private List<Long> parcels;

    private List<ForecastHarvestModel> harvests;

    public ForecastSnapshotModel() {
    }

    public ForecastSnapshotModel(ForecastSnapshot snapshot, List<Long> parcels, List<ForecastHarvestModel> harvests) {
        id = snapshot.getId();
        unitOfMeasure = snapshot.getUnitOfMeasure();
        currency = snapshot.getCurrency();
        unitPrice = snapshot.getUnitPrice();
        this.parcels = parcels;
        this.harvests = harvests;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public List<Long> getParcels() {
        return parcels;
    }

    public void setParcels(List<Long> parcels) {
        this.parcels = parcels;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public List<ForecastHarvestModel> getHarvests() {
        return harvests;
    }

    public void setHarvests(List<ForecastHarvestModel> harvests) {
        this.harvests = harvests;
    }
}
