package com.arobs.model.chemicals;


import com.arobs.entity.Pesticide;

import java.io.Serializable;

public class PesticideModel implements Serializable {

    private Long id;
    private Long typeId;
    private String nameRo;
    private String nameRu;
    private String name2Ro;
    private String name2Ru;
    private String descriptionRo;
    private String descriptionRu;
    private String harmfulOrganismsRo;
    private String harmfulOrganismsRu;
    private String activeSubstance;
    private Double consumptionNorm;

    public PesticideModel() {
    }

    public PesticideModel(Pesticide pesticide) {
        this.id = pesticide.getId();
        this.typeId = pesticide.getTypeId();
        this.nameRo = pesticide.getNameRo();
        this.nameRu = pesticide.getNameRu();
        this.name2Ro = pesticide.getName2Ro();
        this.name2Ru = pesticide.getName2Ru();
        this.descriptionRo = pesticide.getDescriptionRo();
        this.descriptionRu = pesticide.getDescriptionRu();
        this.harmfulOrganismsRo = pesticide.getHarmfulOrganismsRo();
        this.harmfulOrganismsRu = pesticide.getHarmfulOrganismsRu();
        this.activeSubstance = pesticide.getActiveSubstance();
        this.consumptionNorm = pesticide.getConsumptionNorm();
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

    public String getName2Ro() {
        return name2Ro;
    }

    public void setName2Ro(String name2Ro) {
        this.name2Ro = name2Ro;
    }

    public String getName2Ru() {
        return name2Ru;
    }

    public void setName2Ru(String name2Ru) {
        this.name2Ru = name2Ru;
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

    public String getHarmfulOrganismsRo() {
        return harmfulOrganismsRo;
    }

    public void setHarmfulOrganismsRo(String harmfulOrganismsRo) {
        this.harmfulOrganismsRo = harmfulOrganismsRo;
    }

    public String getHarmfulOrganismsRu() {
        return harmfulOrganismsRu;
    }

    public void setHarmfulOrganismsRu(String harmfulOrganismsRu) {
        this.harmfulOrganismsRu = harmfulOrganismsRu;
    }

    public String getActiveSubstance() {
        return activeSubstance;
    }

    public void setActiveSubstance(String activeSubstance) {
        this.activeSubstance = activeSubstance;
    }

    public Double getConsumptionNorm() {
        return consumptionNorm;
    }

    public void setConsumptionNorm(Double consumptionNorm) {
        this.consumptionNorm = consumptionNorm;
    }
}
