package com.arobs.model;


import com.arobs.entity.Brand;

public class BrandModel {

    private Long id;
    private String name;

    public BrandModel() {
    }

    public BrandModel(Brand entity) {
        id = entity.getId();
        name = entity.getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
