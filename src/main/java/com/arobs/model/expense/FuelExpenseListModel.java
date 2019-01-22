package com.arobs.model.expense;

import com.arobs.model.EmployeeModel;
import com.arobs.model.machine.MachineModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FuelExpenseListModel {

    private Long expenseId;
    private Date expenseDate;
    private List<MachineModel> machines = new ArrayList<>();
    private List<EmployeeModel> employees = new ArrayList<>();
    private List<ExpenseItemModel> fuels = new ArrayList<>();

    private String unitOfMeasure;
    private Date createdAt;
    private String createdBy;

    public FuelExpenseListModel() {
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

    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    public List<ExpenseItemModel> getFuels() {
        return fuels;
    }

    public void setFuels(List<ExpenseItemModel> fuels) {
        this.fuels = fuels;
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
