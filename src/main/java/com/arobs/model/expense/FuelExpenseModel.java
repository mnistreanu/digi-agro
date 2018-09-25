package com.arobs.model.expense;

import com.arobs.entity.Expense;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Deprecated
public class FuelExpenseModel extends ExpenseModel{

    private List<Long> machines = new ArrayList<>();
    private List<Long> employees = new ArrayList<>();

    public FuelExpenseModel() {
        super();
    }

    public FuelExpenseModel(Expense expense) {
        super(expense);
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
