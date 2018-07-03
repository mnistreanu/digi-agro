package com.arobs.entity;


import com.arobs.enums.MachineType;
import com.arobs.enums.MotorType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Machine {

    @Id
    @GeneratedValue
    private Long id;

    private String identifier;

    @Enumerated(EnumType.STRING)
    private MachineType type;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    private String name;

    private Date fabricationDate;
    private String fabricationCountry;

    @Enumerated(EnumType.STRING)
    private MotorType motorType;

    private Double consumption;
    private Double power;

    private Double speedOnRoad;
    private Double speedInWork;

    @ManyToMany
    @JoinTable(name = "machine_work_types",
            joinColumns = { @JoinColumn(name = "machine_id") },
            inverseJoinColumns = { @JoinColumn(name = "work_type_id") })
    private List<WorkType> workTypes = new ArrayList<>();

    @Column(columnDefinition = "boolean default true")
    private boolean active = true;

    public Machine() { }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public MachineType getType() {
        return type;
    }

    public void setType(MachineType type) {
        this.type = type;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getFabricationDate() {
        return fabricationDate;
    }

    public void setFabricationDate(Date fabricationDate) {
        this.fabricationDate = fabricationDate;
    }

    public MotorType getMotorType() {
        return motorType;
    }

    public void setMotorType(MotorType motorType) {
        this.motorType = motorType;
    }

    public Double getConsumption() {
        return consumption;
    }

    public void setConsumption(Double consumption) {
        this.consumption = consumption;
    }

    public Double getPower() {
        return power;
    }

    public void setPower(Double power) {
        this.power = power;
    }

    public Double getSpeedOnRoad() {
        return speedOnRoad;
    }

    public void setSpeedOnRoad(Double speedOnRoad) {
        this.speedOnRoad = speedOnRoad;
    }

    public Double getSpeedInWork() {
        return speedInWork;
    }

    public void setSpeedInWork(Double speedInWork) {
        this.speedInWork = speedInWork;
    }

    public List<WorkType> getWorkTypes() {
        return workTypes;
    }

    public void setWorkTypes(List<WorkType> workTypes) {
        this.workTypes = workTypes;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getFabricationCountry() {
        return fabricationCountry;
    }

    public void setFabricationCountry(String fabricationCountry) {
        this.fabricationCountry = fabricationCountry;
    }
}
