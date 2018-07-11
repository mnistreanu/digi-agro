package com.arobs.entity;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class TenantBranch {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tenant_id")
    private Tenant tenant;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private TenantBranch parent;

    @NotNull
    @Column(columnDefinition = "varchar(128)")
    private String name;

    @Column(columnDefinition = "varchar(1024)")
    private String description;

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

    public TenantBranch() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
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

    public TenantBranch getParent() {
        return parent;
    }

    public void setParent(TenantBranch parent) {
        this.parent = parent;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
