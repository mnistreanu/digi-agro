package com.arobs.model.parcel;

import com.arobs.entity.AgroWork;
import com.arobs.entity.AgroWorkType;
import com.arobs.entity.Parcel;
import com.arobs.entity.ParcelCropSeason;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class ParcelCropSeasonModel {

    private Long id;
    private Date plantedAt;
    private Integer rowsOnParcel;
    private Integer plantsOnRow;
    private Double spaceBetweenRows;
    private Double spaceBetweenPlants;

    public ParcelCropSeasonModel() {
    }

    public ParcelCropSeasonModel(ParcelCropSeason parcelCropSeason) {
        this.id = parcelCropSeason.getId();
        this.plantedAt = parcelCropSeason.getPlantedAt();
        this.rowsOnParcel = parcelCropSeason.getRowsOnParcel();
        this.plantsOnRow = parcelCropSeason.getPlantsOnRow();
        this.spaceBetweenRows = parcelCropSeason.getSpaceBetweenRows();
        this.spaceBetweenPlants = parcelCropSeason.getSpaceBetweenPlants();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getPlantedAt() {
        return plantedAt;
    }

    public void setPlantedAt(Date plantedAt) {
        this.plantedAt = plantedAt;
    }

    public Integer getRowsOnParcel() {
        return rowsOnParcel;
    }

    public void setRowsOnParcel(Integer rowsOnParcel) {
        this.rowsOnParcel = rowsOnParcel;
    }

    public Integer getPlantsOnRow() {
        return plantsOnRow;
    }

    public void setPlantsOnRow(Integer plantsOnRow) {
        this.plantsOnRow = plantsOnRow;
    }

    public Double getSpaceBetweenRows() {
        return spaceBetweenRows;
    }

    public void setSpaceBetweenRows(Double spaceBetweenRows) {
        this.spaceBetweenRows = spaceBetweenRows;
    }

    public Double getSpaceBetweenPlants() {
        return spaceBetweenPlants;
    }

    public void setSpaceBetweenPlants(Double spaceBetweenPlants) {
        this.spaceBetweenPlants = spaceBetweenPlants;
    }
}
