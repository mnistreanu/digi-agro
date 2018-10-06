package com.arobs.entity;

import javax.persistence.*;

/**
 * Created by mihail.gorgos on 14.07.2018.
 */
@Entity
@Table(name = "crop_variety")
public class CropVariety {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "crop_id")
    private Long cropId;

    @Column(name = "name_ro")
    private String nameRo;

    @Column(name = "name_ru")
    private String nameRu;

    @Column(name = "description_ro", length = 4000)
    private String descriptionRo;

    @Column(name = "description_ru", length = 4000)
    private String descriptionRu;

    @Column(name = "seed_consumption_ha")
    private Double seedConsumptionHa;

    @Column(name = "unit_of_measure")
    private String unitOfMeasure;


    public CropVariety() {
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

    public String getDescriptionRu() {
        return descriptionRu;
    }

    public void setDescriptionRu(String descriptionRu) {
        this.descriptionRu = descriptionRu;
    }

    public String getDescriptionRo() {
        return descriptionRo;
    }

    public void setDescriptionRo(String descriptionRo) {
        this.descriptionRo = descriptionRo;
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
}
