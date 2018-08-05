package com.arobs.model;

import com.arobs.entity.CropVariety;

import java.io.Serializable;

public class CropVarietyModel implements Serializable {

    private Long id;
    private Long cropId;
    private String nameRo;
    private String nameRu;
    private String descriptionRo;
    private String descriptionRu;

    public CropVarietyModel() {
    }

    public CropVarietyModel(CropVariety cropVariety) {
        this.id = cropVariety.getId();
        this.cropId = cropVariety.getCropId();
        this.nameRo = cropVariety.getNameRo();
        this.nameRu = cropVariety.getNameRu();
        this.descriptionRo = cropVariety.getDescriptionRo();
        this.descriptionRu = cropVariety.getDescriptionRu();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCropId() {
        return cropId;
    }

    public void setCropId(Long cropId) {
        this.cropId = cropId;
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
