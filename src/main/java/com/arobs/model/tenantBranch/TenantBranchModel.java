package com.arobs.model.tenantBranch;


import com.arobs.entity.TenantBranch;

public class TenantBranchModel {

    private Long id;
    private String name;
    private String description;
    private String country;
    private String county;
    private String city;
    private String address;
    private String phones;

    private Long parentId;
    private Long tenantId;

    public TenantBranchModel() {
    }

    public TenantBranchModel(TenantBranch entity) {
        id = entity.getId();
        name = entity.getName();
        description = entity.getDescription();
        country = entity.getCountry();
        county = entity.getCounty();
        city = entity.getCity();
        address = entity.getAddress();
        phones = entity.getPhones();
        if (entity.getParent() != null) {
            parentId = entity.getParent().getId();
        }
        if (entity.getTenant() != null) {
            tenantId = entity.getTenant().getId();
        }
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }
}
