package com.arobs.model.expense;

import com.arobs.entity.Expense;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by mihail.gorgos on 21.09.2018.
 */
public class ExpenseModel {

    private Long id;
    private String title;
    private Date expenseDate;
    private List<ExpenseItemModel> expenseItems = new ArrayList<>();


    public ExpenseModel() {
        // empty
    }

    public ExpenseModel(Expense expense) {
        this.id = expense.getId();
        this.title = expense.getTitle();
        this.expenseDate = expense.getExpenseDate();
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

}
