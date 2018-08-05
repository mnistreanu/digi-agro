package com.arobs.model.parcel;

import com.arobs.entity.Parcel;

import java.math.BigDecimal;
import java.util.List;

public class ParcelModel {

    private Long id;
    private String cadasterNumber;
    private Integer landworthinessPoints;
    private Double area;
    private String name;
    private String description;

    private List<BigDecimal[]> coordinates;

    public ParcelModel() {
    }

    public ParcelModel(Parcel entity) {

        id = entity.getId();
        cadasterNumber = entity.getCadasterNumber();
        landworthinessPoints = entity.getLandworthinessPoints();
        area = entity.getArea();
        name = entity.getName();
        description = entity.getDescription();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCadasterNumber() {
        return cadasterNumber;
    }

    public void setCadasterNumber(String cadasterNumber) {
        this.cadasterNumber = cadasterNumber;
    }

    public Integer getLandworthinessPoints() {
        return landworthinessPoints;
    }

    public void setLandworthinessPoints(Integer landworthinessPoints) {
        this.landworthinessPoints = landworthinessPoints;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<BigDecimal[]> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<BigDecimal[]> coordinates) {
        this.coordinates = coordinates;
    }
}
