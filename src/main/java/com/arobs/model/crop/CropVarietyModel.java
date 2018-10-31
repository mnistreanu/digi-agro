package com.arobs.model.crop;

import com.arobs.entity.Crop;
import com.arobs.entity.CropVariety;

import java.io.Serializable;

public class CropVarietyModel implements Serializable {

    private Long cropCategoryId;
    private Long cropId;
    private Long id;
    private String cropNameRo;
    private String cropNameRu;
    private String nameRo;
    private String nameRu;
    private String descriptionRo;
    private String descriptionRu;
    private Double seedConsumptionHa;
    private String unitOfMeasure;

    public CropVarietyModel() {
    }

    public CropVarietyModel(CropVariety cropVariety) {
        this.id = cropVariety.getId();
        Crop crop = cropVariety.getCrop();
        this.cropId = crop.getId();
        this.cropNameRo = crop.getNameRo();
        this.cropNameRu = crop.getNameRu();
        this.cropCategoryId = crop.getCropCategoryId();
        this.nameRo = cropVariety.getNameRo();
        this.nameRu = cropVariety.getNameRu();
        this.descriptionRo = cropVariety.getDescriptionRo();
        this.descriptionRu = cropVariety.getDescriptionRu();
        this.seedConsumptionHa = cropVariety.getSeedConsumptionHa();
        this.unitOfMeasure = cropVariety.getUnitOfMeasure();
    }

    public Long getCropCategoryId() {
        return cropCategoryId;
    }

    public void setCropCategoryId(Long cropCategoryId) {
        this.cropCategoryId = cropCategoryId;
    }

    public Long getCropId() {
        return cropId;
    }

    public void setCropId(Long cropId) {
        this.cropId = cropId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameRo() {
        return nameRo;
    }

    public void setNameRo(String nameRo) {
        this.nameRo = nameRo;
    }

    public String getNameRu() {
        return nameRu;
    }

    public void setNameRu(String nameRu) {
        this.nameRu = nameRu;
    }

    public String getDescriptionRo() {
        return descriptionRo;
    }

    public void setDescriptionRo(String descriptionRo) {
        this.descriptionRo = descriptionRo;
    }

    public String getDescriptionRu() {
        return descriptionRu;
    }

    public void setDescriptionRu(String descriptionRu) {
        this.descriptionRu = descriptionRu;
    }

    public Double getSeedConsumptionHa() {
        return seedConsumptionHa;
    }

    public void setSeedConsumptionHa(Double seedConsumptionHa) {
        this.seedConsumptionHa = seedConsumptionHa;
    }

    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
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
}
