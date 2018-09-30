package com.arobs.model.expense;

import com.arobs.model.EmployeeModel;
import com.arobs.model.MachineModel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MachineryExpenseListModel {

    private Long expenseId;
    private Date expenseDate;
    private List<MachineModel> machines = new ArrayList<>();
    private List<EmployeeModel> employees = new ArrayList<>();
    private String sparePart;
    private BigDecimal sparePartPrice;
    private Date createdAt;
    private String createdBy;

    public MachineryExpenseListModel() {
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

    public String getSparePart() {
        return sparePart;
    }

    public void setSparePart(String sparePart) {
        this.sparePart = sparePart;
    }

    public BigDecimal getSparePartPrice() {
        return sparePartPrice;
    }

    public void setSparePartPrice(BigDecimal sparePartPrice) {
        this.sparePartPrice = sparePartPrice;
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
}
