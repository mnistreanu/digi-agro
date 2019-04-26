package com.arobs.model.parcel;

import com.arobs.entity.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class ParcelModel {

    private Long id;
    private Long tenantId;
    private Long branchId;
    private String countryId;
    private String countyId;
    private Long cityId;
    private String cadasterNumber;
    private Integer landWorthinessPoints;
    private Double area;
    private String name;
    private boolean irrigated;
    private String description;

    private List<BigDecimal[]> coordinates;

    private String lastWorkTypeRo, lastWorkTypeRu;
    private Date lastWorkDate;

    public ParcelModel() {
    }

    public ParcelModel(Parcel entity) {
        this.id = entity.getId();
        this.tenantId = entity.getTenantId();
        this.branchId = entity.getBranchId();
        this.countryId = entity.getCountryId();
        this.countyId = entity.getCountyId();
        this.cityId = entity.getCityId();
        this.cadasterNumber = entity.getCadasterNumber();
        this.landWorthinessPoints = entity.getLandWorthinessPoints();
        this.area = entity.getArea();
        this.name = entity.getName();
        this.description = entity.getDescription();
        this.irrigated = entity.isIrrigated();
    }

    public void setupLastCropWork(AgroWork lastAgroWork, AgroWorkType workType) {
        this.lastWorkDate = lastAgroWork.getWorkDate();
        this.lastWorkTypeRo = workType.getNameRo();
        this.lastWorkTypeRu = workType.getNameRu();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getCountyId() {
        return countyId;
    }

    public void setCountyId(String countyId) {
        this.countyId = countyId;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
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

    public boolean isIrrigated() {
        return irrigated;
    }

    public void setIrrigated(boolean irrigated) {
        this.irrigated = irrigated;
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
//
//    public String getIcon() {
//        return icon;
//    }
//
//    public void setIcon(String icon) {
//        this.icon = icon;
//    }
//
//    public Date getPlantedAt() {
//        return plantedAt;
//    }
//
//    public void setPlantedAt(Date plantedAt) {
//        this.plantedAt = plantedAt;
//    }
//
//    public Integer getRowsOnParcel() {
//        return rowsOnParcel;
//    }
//
//    public void setRowsOnParcel(Integer rowsOnParcel) {
//        this.rowsOnParcel = rowsOnParcel;
//    }
//
//    public Integer getPlantsOnRow() {
//        return plantsOnRow;
//    }
//
//    public void setPlantsOnRow(Integer plantsOnRow) {
//        this.plantsOnRow = plantsOnRow;
//    }
//
//    public Integer getSpaceBetweenRows() {
//        return spaceBetweenRows;
//    }
//
//    public void setSpaceBetweenRows(Integer spaceBetweenRows) {
//        this.spaceBetweenRows = spaceBetweenRows;
//    }
//
//    public Integer getSpaceBetweenPlants() {
//        return spaceBetweenPlants;
//    }
//
//    public void setSpaceBetweenPlants(Integer spaceBetweenPlants) {
//        this.spaceBetweenPlants = spaceBetweenPlants;
//    }
//
//    public String getCropNameRo() {
//        return cropNameRo;
//    }
//
//    public void setCropNameRo(String cropNameRo) {
//        this.cropNameRo = cropNameRo;
//    }
//
//    public String getCropNameRu() {
//        return cropNameRu;
//    }
//
//    public void setCropNameRu(String cropNameRu) {
//        this.cropNameRu = cropNameRu;
//    }

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
