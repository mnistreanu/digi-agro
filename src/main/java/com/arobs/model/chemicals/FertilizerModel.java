package com.arobs.model.chemicals;


import com.arobs.entity.Fertilizer;
import com.arobs.enums.FertilizerType;

import java.io.Serializable;

public class FertilizerModel implements Serializable {

    private Long id;
    private FertilizerType fertilizerType;
    private String nameRo;
    private String nameRu;
    private String descriptionRo;
    private String descriptionRu;

    public FertilizerModel() {
    }

    public FertilizerModel(Fertilizer fertilizer) {
        this.id = fertilizer.getId();
        this.fertilizerType = fertilizer.getFertilizerType();
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

    public FertilizerType getFertilizerType() {
        return fertilizerType;
    }

    public void setFertilizerType(FertilizerType fertilizerType) {
        this.fertilizerType = fertilizerType;
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
