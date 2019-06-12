package com.arobs.entity;


import javax.persistence.Entity;

@Entity
public class Brand extends BaseEntity {

    private String name;

    public Brand() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
