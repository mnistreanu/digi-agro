package com.arobs.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by mihail.gorgos on 14.07.2018.
 */
@Entity
@Table(name = "crops")
public class Crop {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "crop_category_id")
    private Integer cropCategoryId;

    @Column(name = "name_ro")
    private String nameRo;

    @Column(name = "name_ru")
    private String nameRu;

    public Crop() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCropCategoryId() {
        return cropCategoryId;
    }

    public void setCropCategoryId(Integer cropCategoryId) {
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
