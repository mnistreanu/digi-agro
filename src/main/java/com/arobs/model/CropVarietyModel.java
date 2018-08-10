package com.arobs.model;

import com.arobs.entity.Crop;
import com.arobs.entity.CropCategory;
import com.arobs.entity.CropVariety;

import java.io.Serializable;

public class CropVarietyModel implements Serializable {

    private Long cropCategoryId;
    private Long cropId;
    private Long id;
    private String nameRo;
    private String nameRu;
    private String descriptionRo;
    private String descriptionRu;

    public CropVarietyModel() {
    }

    public CropVarietyModel(CropCategory category) {
        this.id = category.getId();
//        this.cropCategoryId = category.getId();
        this.nameRo = category.getNameRo();
        this.nameRu = category.getNameRu();
    }


    public CropVarietyModel(Crop crop) {
        this.id = crop.getId();
        this.cropCategoryId = crop.getCropCategoryId();
//        this.cropId = crop.getId();
        this.nameRo = crop.getNameRo();
        this.nameRu = crop.getNameRu();
    }


    public CropVarietyModel(CropVariety cropVariety) {
        this.id = cropVariety.getId();
        this.cropId = cropVariety.getCropId();
        this.nameRo = cropVariety.getNameRo();
        this.nameRu = cropVariety.getNameRu();
        this.descriptionRo = cropVariety.getDescriptionRo();
        this.descriptionRu = cropVariety.getDescriptionRu();
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
}
