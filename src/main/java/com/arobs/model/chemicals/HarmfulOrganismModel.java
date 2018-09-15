package com.arobs.model.chemicals;


import com.arobs.entity.HarmfulOrganism;

import java.io.Serializable;

public class HarmfulOrganismModel implements Serializable {

    private Long id;
    private String nameRo;
    private String nameRu;

    public HarmfulOrganismModel() {
    }

    public HarmfulOrganismModel(HarmfulOrganism harmfulOrganism) {
        this.id = harmfulOrganism.getId();
        this.nameRo = harmfulOrganism.getNameRo();
        this.nameRu = harmfulOrganism.getNameRu();
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
