package com.arobs.model.geo;

public class GeoModel {

    private Object id;
    private String nameRo;
    private String nameRu;

    public GeoModel() {
    }

    public GeoModel(Object id, String nameRo, String nameRu) {
        this.id = id;
        this.nameRo = nameRo;
        this.nameRu = nameRu;
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
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
