package com.arobs.model.expense;

import java.util.Date;

public class FuelExpenseListModel {

    private Long expenseId;
    private Date expenseDate;
    private String machine;
    private String employee;
    private String unitOfMeasure;
    private Double diesel;
    private Double oil;
    private Double solidol;
    private Double negrol;

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

    public String getMachine() {
        return machine;
    }

    public void setMachine(String machine) {
        this.machine = machine;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    public Double getDiesel() {
        return diesel;
    }

    public void setDiesel(Double diesel) {
        this.diesel = diesel;
    }

    public Double getOil() {
        return oil;
    }

    public void setOil(Double oil) {
        this.oil = oil;
    }

    public Double getSolidol() {
        return solidol;
    }

    public void setSolidol(Double solidol) {
        this.solidol = solidol;
    }

    public Double getNegrol() {
        return negrol;
    }

    public void setNegrol(Double negrol) {
        this.negrol = negrol;
    }
}
