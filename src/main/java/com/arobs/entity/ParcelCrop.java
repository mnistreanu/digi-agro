package com.arobs.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by mihail.gorgos on 25.07.2018.
 * Aici se va defini relatie parcela si cultura agricola care creste pe aceasta parcela.
 * Deja lucrarile efectuate pentru aceasta parcela se vor defini in alte tabele.
 */
@Entity
@Table(name = "parcel_crop")
public class ParcelCrop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Long id;

    @Column(name = "parcel_id")
    private Long parcelId;

    @Column(name = "crop_id")
    private Long cropId;

    @Column(name = "crop_variety_id")
    private Long cropVarietyId;

    @Column(name = "planted_at")
    private Date plantedAt;

    @Column(name = "rows_on_parcel")
    private Integer rowsOnParcel;

    @Column(name = "plants_on_rows")
    private Integer plantsOnRow;

    @Column(name = "space_between_rows")
    private Integer spaceBetweenRows;

    @Column(name = "space_between_plants")
    private Integer spaceBetweenPlants;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "created_by")
    private Long createdBy;

    @Column(name = "deleted_at")
    private Date deletedAt;

    @Column(name = "deleted_by")
    private Long deletedBy;

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

    public Integer getSpaceBetweenRows() {
        return spaceBetweenRows;
    }

    public void setSpaceBetweenRows(Integer spaceBetweenRows) {
        this.spaceBetweenRows = spaceBetweenRows;
    }

    public Integer getSpaceBetweenPlants() {
        return spaceBetweenPlants;
    }

    public void setSpaceBetweenPlants(Integer spaceBetweenPlants) {
        this.spaceBetweenPlants = spaceBetweenPlants;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }

    public Long getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(Long deletedBy) {
        this.deletedBy = deletedBy;
    }

    public Date getPlantedAt() {
        return plantedAt;
    }

    public void setPlantedAt(Date plantedAt) {
        this.plantedAt = plantedAt;
    }
}
