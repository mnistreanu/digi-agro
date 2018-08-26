package com.arobs.model.parcel;

import com.arobs.entity.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class ParcelModel {

    private Long id;
    private String cadasterNumber;
    private Integer landWorthinessPoints;
    private Double area;
    private String name;
    private String description;

    private List<BigDecimal[]> coordinates;

    private String icon;
    private String cropNameRo, cropNameRu;

    private Date plantedAt;
    private Integer rowsOnParcel;
    private Integer plantsOnRow;
    private Integer spaceBetweenRows;
    private Integer spaceBetweenPlants;

    private String lastWorkTypeRo, lastWorkTypeRu;
    private Date lastWorkDate;

    public ParcelModel() {
    }

    public ParcelModel(Parcel entity) {
        id = entity.getId();
        cadasterNumber = entity.getCadasterNumber();
        landWorthinessPoints = entity.getLandWorthinessPoints();
        area = entity.getArea();
        name = entity.getName();
        description = entity.getDescription();
    }

    public void setupCropInfo(ParcelCrop parcelCrop, Crop crop) {
        plantedAt = parcelCrop.getPlantedAt();
        rowsOnParcel = parcelCrop.getRowsOnParcel();
        plantsOnRow = parcelCrop.getPlantsOnRow();
        spaceBetweenPlants = parcelCrop.getSpaceBetweenPlants();
        spaceBetweenPlants = parcelCrop.getSpaceBetweenPlants();

        icon = crop.getIcon();
        cropNameRo = crop.getNameRo();
        cropNameRu = crop.getNameRu();
    }

    public void setupLastCropWork(ParcelCropWork parcelCropWork, AgroWorkType workType) {
        lastWorkDate = parcelCropWork.getCreatedAt();
        lastWorkTypeRo = workType.getNameRo();
        lastWorkTypeRu = workType.getNameRu();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCadasterNumber() {
        return cadasterNumber;
    }

    public void setCadasterNumber(String cadasterNumber) {
        this.cadasterNumber = cadasterNumber;
    }

    public Integer getLandWorthinessPoints() {
        return landWorthinessPoints;
    }

    public void setLandWorthinessPoints(Integer landWorthinessPoints) {
        this.landWorthinessPoints = landWorthinessPoints;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<BigDecimal[]> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<BigDecimal[]> coordinates) {
        this.coordinates = coordinates;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
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

    public String getCropNameRo() {
        return cropNameRo;
    }

    public void setCropNameRo(String cropNameRo) {
        this.cropNameRo = cropNameRo;
    }

    public String getCropNameRu() {
        return cropNameRu;
    }

    public void setCropNameRu(String cropNameRu) {
        this.cropNameRu = cropNameRu;
    }

    public String getLastWorkTypeRo() {
        return lastWorkTypeRo;
    }

    public void setLastWorkTypeRo(String lastWorkTypeRo) {
        this.lastWorkTypeRo = lastWorkTypeRo;
    }

    public String getLastWorkTypeRu() {
        return lastWorkTypeRu;
    }

    public void setLastWorkTypeRu(String lastWorkTypeRu) {
        this.lastWorkTypeRu = lastWorkTypeRu;
    }

    public Date getLastWorkDate() {
        return lastWorkDate;
    }

    public void setLastWorkDate(Date lastWorkDate) {
        this.lastWorkDate = lastWorkDate;
    }
}
