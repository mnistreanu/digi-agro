package com.arobs.model.agrowork;


import com.arobs.entity.AgroWorkType;
import com.arobs.enums.UnitOfMeasure;
import com.arobs.model.CropModel;

import java.io.Serializable;

public class OtherWorksModel implements Serializable {

    private Long id;
    private Long parcelCropId;
    private String unitOfMeasure;
    private Double quantity;

    /**
     * Crop
     */
    private Long cropId;
    private String cropNameRo;
    private String cropNameRu;

    /**
     * AgroWorkType
     */
    private Long workTypeId;
    private String workTypeNameRo;
    private String workTypeNameRu;


    public OtherWorksModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParcelCropId() {
        return parcelCropId;
    }

    public void setParcelCropId(Long parcelCropId) {
        this.parcelCropId = parcelCropId;
    }

    public Long getCropId() {
        return cropId;
    }

    public void setCropId(Long cropId) {
        this.cropId = cropId;
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

    public Long getWorkTypeId() {
        return workTypeId;
    }

    public void setWorkTypeId(Long workTypeId) {
        this.workTypeId = workTypeId;
    }

    public String getWorkTypeNameRo() {
        return workTypeNameRo;
    }

    public void setWorkTypeNameRo(String workTypeNameRo) {
        this.workTypeNameRo = workTypeNameRo;
    }

    public String getWorkTypeNameRu() {
        return workTypeNameRu;
    }

    public void setWorkTypeNameRu(String workTypeNameRu) {
        this.workTypeNameRu = workTypeNameRu;
    }

    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }
}
