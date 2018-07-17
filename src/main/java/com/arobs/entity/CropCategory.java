package com.arobs.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by mihail.gorgos on 14.07.2018.
 */
@Entity
@Table(name = "crop_categories")
public class CropCategory {

    @Id
    @Column(name="id")
    private String id;

    @Column (name = "name_ro")
    private String nameRo;

    @Column (name = "name_ru")
    private String nameRu;

    public CropCategory() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
