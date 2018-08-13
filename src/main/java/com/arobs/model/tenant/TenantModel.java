package com.arobs.model.tenant;


import com.arobs.entity.Tenant;

public class TenantModel {

    private Long id;
    private String name;
    private String description;
    private String fiscalCode;
    private String country;
    private String county;
    private Long cityId;
    private String address;
    private String phones;

    public TenantModel() {
    }

    public TenantModel(Tenant entity) {
        id = entity.getId();
        name = entity.getName();
        description = entity.getDescription();
        fiscalCode = entity.getFiscalCode();
        country = entity.getCountry();
        county = entity.getCounty();
        cityId = entity.getCityId();
        address = entity.getAddress();
        phones = entity.getPhones();
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

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
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
}
