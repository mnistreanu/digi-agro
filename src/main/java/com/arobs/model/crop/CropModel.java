package com.arobs.model.crop;

import com.arobs.entity.Crop;

public class CropModel {

    private Long id;
    private Long cropCategoryId;
    private String nameRo;
    private String nameRu;

    public CropModel(Crop entity) {
        id = entity.getId();
        cropCategoryId = entity.getCropCategoryId();
        nameRo = entity.getNameRo();
        nameRu = entity.getNameRu();
    }

    public CropModel() {
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
