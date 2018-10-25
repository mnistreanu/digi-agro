package com.arobs.model.branch;


import com.arobs.entity.Branch;
import com.arobs.entity.County;

public class BranchModel {

    private Long id;
    private String name;
    private String description;
    private County county;
    private String city;
    private String address;
    private String phones;

    private Long parentId;
    private Long tenantId;

    public BranchModel() {
    }

    public BranchModel(Branch entity) {
        id = entity.getId();
        name = entity.getName();
        description = entity.getDescription();
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

    public County getCounty() {
        return county;
    }

    public void setCounty(County county) {
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
