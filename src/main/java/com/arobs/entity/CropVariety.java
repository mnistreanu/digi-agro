package com.arobs.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by mihail.gorgos on 14.07.2018.
 */
@Entity
@Table(name = "crop_varieties")
public class CropVariety {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "crop_id")
    private Integer cropId;

    @Column(name = "name_ro")
    private String nameRo;

    @Column(name = "name_ru")
    private String nameRu;

    @Column(name = "description_ru")
    private String descriptionRu;

    @Column(name = "description_ro")
    private String descriptionRo;

    public CropVariety() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCropId() {
        return cropId;
    }

    public void setCropId(Integer cropId) {
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
}
