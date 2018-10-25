package com.arobs.entity;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "branch")
public class Branch {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tenant_id")
    private Tenant tenant;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Branch parent;

    @NotNull
    @Column(columnDefinition = "varchar(128)")
    private String name;

    @Column(columnDefinition = "varchar(1024)")
    private String description;

    @Column(name = "county_id", columnDefinition = "char(2)")
    private String countyId;

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name = "county_id", insertable = false, updatable = false)
    private County county;

    @Column(name = "city", columnDefinition = "varchar(128)")
    private String city;

    @Column(name = "address", columnDefinition = "varchar(1024)")
    private String address;

    @Column(name = "phones", columnDefinition = "varchar(128)")
    private String phones;

    public Branch() {
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

    public String getCountyId() {
        return countyId;
    }

    public void setCountyId(String countyId) {
        this.countyId = countyId;
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

    public Branch getParent() {
        return parent;
    }

    public void setParent(Branch parent) {
        this.parent = parent;
    }

}
