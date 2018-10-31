package com.arobs.model.crop;

import com.arobs.entity.CropCategory;

import java.io.Serializable;

public class CropCategoryModel implements Serializable {

    private Long id;
    private String nameRo;
    private String nameRu;

    public CropCategoryModel() {
    }

    public CropCategoryModel(CropCategory category) {
        this.id = category.getId();
        this.nameRo = category.getNameRo();
        this.nameRu = category.getNameRu();
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
}
