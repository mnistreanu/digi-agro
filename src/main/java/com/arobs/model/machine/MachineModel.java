package com.arobs.model.machine;


import com.arobs.entity.AgroWorkType;
import com.arobs.entity.Machine;
import com.arobs.entity.MachineGroup;
import com.arobs.enums.MachineType;
import com.arobs.enums.MotorType;
import com.arobs.model.EmployeeModel;

import java.util.List;
import java.util.stream.Collectors;

public class MachineModel {

    private Long id;
    private String identifier;
    private String model;

    private MachineType type;

    private String brand;

    private Integer fabricationYear;
    private String fabricationCountry;

    private MotorType motorType;
    private Double consumption;
    private Double power;

    private Double speedOnRoad;
    private Double speedInWork;

    private MachineGroupModel machineGroupModel;
    private Long machineGroupId;

    private List<EmployeeModel> employees;

    private List<Long> workTypes;

    public MachineModel() {
    }

    public MachineModel(Machine entity) {
        id = entity.getId();
        identifier = entity.getIdentifier();
        model = entity.getModel();

        type = entity.getType();

        brand = entity.getBrand().getName();

        fabricationYear = entity.getFabricationYear();
        fabricationCountry = entity.getFabricationCountry();

        motorType = entity.getMotorType();
        consumption = entity.getConsumption();
        power = entity.getPower();

        speedOnRoad = entity.getSpeedOnRoad();
        speedInWork = entity.getSpeedInWork();

        if (entity.getEmployees() != null) {
            employees = entity.getEmployees().stream().map(EmployeeModel::new).collect(Collectors.toList());
        }

        if (entity.getWorkTypes() != null) {
            workTypes = entity.getWorkTypes().stream().map(AgroWorkType::getId).collect(Collectors.toList());
        }

        if (entity.getMachineGroup() != null) {
            machineGroupModel = new MachineGroupModel(entity.getMachineGroup());
            machineGroupId = entity.getMachineGroup().getId();
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

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
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

    public Integer getFabricationYear() {
        return fabricationYear;
    }

    public void setFabricationYear(Integer fabricationYear) {
        this.fabricationYear = fabricationYear;
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

    public List<EmployeeModel> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeModel> employees) {
        this.employees = employees;
    }

    public List<Long> getWorkTypes() {
        return workTypes;
    }

    public void setWorkTypes(List<Long> workTypes) {
        this.workTypes = workTypes;
    }

    public MachineGroupModel getMachineGroupModel() {
        return machineGroupModel;
    }

    public void setMachineGroupModel(MachineGroupModel machineGroupModel) {
        this.machineGroupModel = machineGroupModel;
    }

    public Long getMachineGroupId() {
        return machineGroupId;
    }

    public void setMachineGroupId(Long machineGroupId) {
        this.machineGroupId = machineGroupId;
    }
}
