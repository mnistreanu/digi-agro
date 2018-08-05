package com.arobs.model;


import com.arobs.entity.Crop;

import java.io.Serializable;

public class CropModel implements Serializable {

    private Long id;
    private Long cropCategoryId;
    private String nameRo;
    private String nameRu;

    public CropModel() {
    }

    public CropModel(Crop entity) {
        this.id = entity.getId();
        this.cropCategoryId = entity.getCropCategoryId();
        this.nameRo = entity.getNameRo();
        this.nameRu = entity.getNameRu();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCropCategoryId() {
        return cropCategoryId;
    }

    public void setCropCategoryId(Long cropCategoryId) {
        this.cropCategoryId = cropCategoryId;
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
}
