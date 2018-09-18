package com.arobs.model.expense;

import com.arobs.entity.Expense;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MachineryExpenseModel {

    private Long id;
    private String title;
    private Date expenseDate;

    private List<ExpenseItemModel> expenseItems = new ArrayList<>();

    private List<Long> machines = new ArrayList<>();
    private List<Long> employees = new ArrayList<>();

    public MachineryExpenseModel() {
    }

    public MachineryExpenseModel(Expense expense) {
        id = expense.getId();
        title = expense.getTitle();
        expenseDate = expense.getExpenseDate();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getExpenseDate() {
        return expenseDate;
    }

    public void setExpenseDate(Date expenseDate) {
        this.expenseDate = expenseDate;
    }

    public List<ExpenseItemModel> getExpenseItems() {
        return expenseItems;
    }

    public void setExpenseItems(List<ExpenseItemModel> expenseItems) {
        this.expenseItems = expenseItems;
    }

    public List<Long> getMachines() {
        return machines;
    }

    public void setMachines(List<Long> machines) {
        this.machines = machines;
    }

    public List<Long> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Long> employees) {
        this.employees = employees;
    }
}
