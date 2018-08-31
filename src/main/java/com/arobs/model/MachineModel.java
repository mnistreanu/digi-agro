package com.arobs.model;


import com.arobs.entity.AgroWorkType;
import com.arobs.entity.Machine;
import com.arobs.enums.MachineType;
import com.arobs.enums.MotorType;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class MachineModel {

    private Long id;
    private String identifier;
    private String name;

    private MachineType type;

    private String brand;

    private Date fabricationDate;
    private String fabricationCountry;

    private MotorType motorType;
    private Double consumption;
    private Double power;

    private Double speedOnRoad;
    private Double speedInWork;

    private List<Long> workTypes;

    public MachineModel() {
    }

    public MachineModel(Machine entity) {
        id = entity.getId();
        identifier = entity.getIdentifier();
        name = entity.getName();

        type = entity.getType();

        brand = entity.getBrand().getName();

        fabricationDate = entity.getFabricationDate();
        fabricationCountry = entity.getFabricationCountry();

        motorType = entity.getMotorType();
        consumption = entity.getConsumption();
        power = entity.getPower();

        speedOnRoad = entity.getSpeedOnRoad();
        speedInWork = entity.getSpeedInWork();

        if (entity.getWorkTypes() != null) {
            workTypes = entity.getWorkTypes().stream().map(AgroWorkType::getId).collect(Collectors.toList());
        }
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MachineType getType() {
        return type;
    }

    public void setType(MachineType type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Date getFabricationDate() {
        return fabricationDate;
    }

    public void setFabricationDate(Date fabricationDate) {
        this.fabricationDate = fabricationDate;
    }

    public String getFabricationCountry() {
        return fabricationCountry;
    }

    public void setFabricationCountry(String fabricationCountry) {
        this.fabricationCountry = fabricationCountry;
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

    public List<Long> getWorkTypes() {
        return workTypes;
    }

    public void setWorkTypes(List<Long> workTypes) {
        this.workTypes = workTypes;
    }
}
