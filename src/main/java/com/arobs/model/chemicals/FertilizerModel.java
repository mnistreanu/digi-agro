package com.arobs.model.chemicals;


import com.arobs.entity.Fertilizer;

import java.io.Serializable;

public class FertilizerModel implements Serializable {

    private Long id;
    private Long typeId;
    private String nameRo;
    private String nameRu;
    private String descriptionRo;
    private String descriptionRu;

    public FertilizerModel() {
    }

    public FertilizerModel(Fertilizer fertilizer) {
        this.id = fertilizer.getId();
        this.typeId = fertilizer.getTypeId();
        this.nameRo = fertilizer.getNameRo();
        this.nameRu = fertilizer.getNameRu();
        this.descriptionRo = fertilizer.getDescriptionRo();
        this.descriptionRu = fertilizer.getDescriptionRu();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
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
