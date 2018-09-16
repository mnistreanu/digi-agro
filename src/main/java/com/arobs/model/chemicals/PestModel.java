package com.arobs.model.chemicals;


import com.arobs.entity.Pest;

import java.io.Serializable;

public class PestModel implements Serializable {

    private Long id;
    private String nameRo;
    private String nameRu;

    public PestModel() {
    }

    public PestModel(Pest pest) {
        this.id = pest.getId();
        this.nameRo = pest.getNameRo();
        this.nameRu = pest.getNameRu();
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
