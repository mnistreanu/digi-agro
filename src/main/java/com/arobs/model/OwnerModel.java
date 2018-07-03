package com.arobs.model;


import com.arobs.entity.Owner;

public class OwnerModel {

    private Long id;
    private String name;

    public OwnerModel() {
    }

    public OwnerModel(Owner entity) {
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
