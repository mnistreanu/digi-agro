package com.arobs.model.expense;

import com.arobs.model.EmployeeModel;
import com.arobs.model.machine.MachineModel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WorksExpenseListModel {

    private Long expenseId;
    private Date expenseDate;
    private List<MachineModel> machines = new ArrayList<>();
    private List<EmployeeModel> employees = new ArrayList<>();
    private String workType;
    private String crop;
    private String unitOfMeasure;
    private Double quantity;
    private Double quantityNorm;
    private Double quantityDefacto;
    private BigDecimal price1Norm;

    private Date createdAt;
    private String createdBy;

    public WorksExpenseListModel() {
    }

    public Long getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(Long expenseId) {
        this.expenseId = expenseId;
    }

    public Date getExpenseDate() {
        return expenseDate;
    }

    public void setExpenseDate(Date expenseDate) {
        this.expenseDate = expenseDate;
    }

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }

    public String getCrop() {
        return crop;
    }

    public void setCrop(String crop) {
        this.crop = crop;
    }

    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getQuantityNorm() {
        return quantityNorm;
    }

    public void setQuantityNorm(Double quantityNorm) {
        this.quantityNorm = quantityNorm;
    }

    public Double getQuantityDefacto() {
        return quantityDefacto;
    }

    public void setQuantityDefacto(Double quantityDefacto) {
        this.quantityDefacto = quantityDefacto;
    }

    public BigDecimal getPrice1Norm() {
        return price1Norm;
    }

    public void setPrice1Norm(BigDecimal price1Norm) {
        this.price1Norm = price1Norm;
    }

    public List<MachineModel> getMachines() {
        return machines;
    }

    public void setMachines(List<MachineModel> machines) {
        this.machines = machines;
    }

    public List<EmployeeModel> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeModel> employees) {
        this.employees = employees;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public BigDecimal getSum() {
        return (BigDecimal.valueOf(this.quantity).multiply(this.price1Norm));
    }
}
