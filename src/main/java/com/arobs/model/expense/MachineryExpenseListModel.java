package com.arobs.model.expense;

import java.util.Date;

@Deprecated
public class MachineryExpenseListModel {

    private Long expenseId;
    private Date expenseDate;
    private String machine;
    private String employee;
    private String sparePart;
    private Double sparePartPrice;

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

    public String getSparePart() {
        return sparePart;
    }

    public void setSparePart(String sparePart) {
        this.sparePart = sparePart;
    }

    public Double getSparePartPrice() {
        return sparePartPrice;
    }

    public void setSparePartPrice(Double sparePartPrice) {
        this.sparePartPrice = sparePartPrice;
    }
}
