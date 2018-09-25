package com.arobs.model.chemicals;


import com.arobs.entity.Pesticide;
import com.arobs.enums.PesticideType;

import java.io.Serializable;

public class PesticideModel implements Serializable {

    private Long id;
    private PesticideType pesticideType;
    private String nameRo;
    private String nameRu;
    private String descriptionRo;
    private String descriptionRu;
    private String pestsRo;
    private String pestsRu;
    private String activeSubstance;
    private Integer toxicityGroup;

    public PesticideModel() {
    }

    public PesticideModel(Pesticide pesticide) {
        this.id = pesticide.getId();
        this.pesticideType = pesticide.getPesticideType();
        this.nameRo = pesticide.getNameRo();
        this.nameRu = pesticide.getNameRu();
        this.descriptionRo = pesticide.getDescriptionRo();
        this.descriptionRu = pesticide.getDescriptionRu();
        this.pestsRo = pesticide.getPestsRo();
        this.pestsRu = pesticide.getPestsRu();
        this.activeSubstance = pesticide.getActiveSubstance();
        this.toxicityGroup = pesticide.getToxicityGroup();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PesticideType getPesticideType() {
        return pesticideType;
    }

    public void setPesticideType(PesticideType pesticideType) {
        this.pesticideType = pesticideType;
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

    public String getPestsRo() {
        return pestsRo;
    }

    public void setPestsRo(String pestsRo) {
        this.pestsRo = pestsRo;
    }

    public String getPestsRu() {
        return pestsRu;
    }

    public void setPestsRu(String pestsRu) {
        this.pestsRu = pestsRu;
    }

    public String getActiveSubstance() {
        return activeSubstance;
    }

    public void setActiveSubstance(String activeSubstance) {
        this.activeSubstance = activeSubstance;
    }

    public Integer getToxicityGroup() {
        return toxicityGroup;
    }

    public void setToxicityGroup(Integer toxicityGroup) {
        this.toxicityGroup = toxicityGroup;
    }
}
