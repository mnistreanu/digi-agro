package com.arobs.entity;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Tenant {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(columnDefinition = "varchar(128)")
    private String name;

    @Column(columnDefinition = "varchar(1024)")
    private String description;

    @Column(name = "fiscal_code")
    private String fiscalCode;

    @NotNull
    @Column(name = "country_id", columnDefinition = "char(2)")
    private String country;

    @Column(name = "county_id", columnDefinition = "char(2)")
    private String county;

    @Column(name = "village_city_id")
    private String villageCity;

    @Column(name = "address", columnDefinition = "varchar(1024)")
    private String address;

    @Column(name = "phones", columnDefinition = "varchar(128)")
    private String phones;

    @Column(columnDefinition = "boolean default true")
    private boolean active = true;

    public Tenant() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFiscalCode() {
        return fiscalCode;
    }

    public void setFiscalCode(String fiscalCode) {
        this.fiscalCode = fiscalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getVillageCity() {
        return villageCity;
    }

    public void setVillageCity(String villageCity) {
        this.villageCity = villageCity;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhones() {
        return phones;
    }

    public void setPhones(String phones) {
        this.phones = phones;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}

