package com.arobs.entity;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "tenants")
public class Tenant {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @NotNull
    @Column(name="name", columnDefinition = "varchar(256)")
    private String name;

    @Column(name="description", columnDefinition = "varchar(1024)")
    private String description;

    @Column(name = "fiscal_code")
    private String fiscalCode;

    @NotNull
    @Column(name = "country_id", columnDefinition = "char(2)")
    private String country;

    @Column(name = "county_id", columnDefinition = "char(2)")
    private String county;

    @Column(name = "city_village_id")
    private Long cityVillageId;

    @Column(name = "address", columnDefinition = "varchar(1024)")
    private String address;

    @Column(name = "phones", columnDefinition = "varchar(128)")
    private String phones;

    @Column(name="deleted_at")
    private Date deletedAt;

    @Column(name="deleted_by")
    private Long deletedBy;

    @Column(name="blocked_at")
    private Date blockedAt;

    @Column(name="blocked_by")
    private Long blockedBy;

    @Column(name="blocked_reason", columnDefinition = "varchar(256)")
    private String blockedReason;

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

    public Long getCityVillageId() {
        return cityVillageId;
    }

    public void setCityVillageId(Long cityVillageId) {
        this.cityVillageId = cityVillageId;
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

    public Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }

    public Long getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(Long deletedBy) {
        this.deletedBy = deletedBy;
    }

    public Date getBlockedAt() {
        return blockedAt;
    }

    public void setBlockedAt(Date blockedAt) {
        this.blockedAt = blockedAt;
    }

    public Long getBlockedBy() {
        return blockedBy;
    }

    public void setBlockedBy(Long blockedBy) {
        this.blockedBy = blockedBy;
    }

    public String getBlockedReason() {
        return blockedReason;
    }

    public void setBlockedReason(String blockedReason) {
        this.blockedReason = blockedReason;
    }
}




