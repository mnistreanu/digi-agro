package com.arobs.entity;

import com.arobs.entity.agro.AgroWorkType;
import com.arobs.enums.MachineType;
import com.arobs.enums.MotorType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "machine")
public class Machine extends BaseEntity {

    private String identifier;

    @Enumerated(EnumType.STRING)
    private MachineType type;

    @ManyToOne
    @JoinColumn(name = "tenant_id")
    private Tenant tenant;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    private String model;

    private Integer fabricationYear;
    private String fabricationCountry;

    @Enumerated(EnumType.STRING)
    private MotorType motorType;

    private Double consumption;
    private Double power;

    private Double speedOnRoad;
    private Double speedInWork;

    @ManyToOne
    @JoinColumn(name = "machine_group_id")
    private MachineGroup machineGroup;

    @ManyToMany
    @JoinTable(name = "machine_work_type",
            joinColumns = {@JoinColumn(name = "machine_id")},
            inverseJoinColumns = {@JoinColumn(name = "work_type_id")})
    private List<AgroWorkType> workTypes = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "machine_employee",
            joinColumns = {@JoinColumn(name = "machine_id")},
            inverseJoinColumns = {@JoinColumn(name = "employee_id")})
    private List<Employee> employees = new ArrayList<>();

    @Column(columnDefinition = "boolean default true")
    private boolean active = true;

    public Machine() {
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

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getFabricationYear() {
        return fabricationYear;
    }

    public void setFabricationYear(Integer fabricationYear) {
        this.fabricationYear = fabricationYear;
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

    public List<AgroWorkType> getWorkTypes() {
        return workTypes;
    }

    public void setWorkTypes(List<AgroWorkType> workTypes) {
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

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public MachineGroup getMachineGroup() {
        return machineGroup;
    }

    public void setMachineGroup(MachineGroup machineGroup) {
        this.machineGroup = machineGroup;
    }
}
