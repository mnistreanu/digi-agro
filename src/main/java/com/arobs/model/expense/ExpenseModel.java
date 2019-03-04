package com.arobs.model.expense;

import com.arobs.entity.Expense;
import com.arobs.entity.ExpenseCategory;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ExpenseModel implements Serializable {

    private Long id;
    private Long tenantId;

    private Long categoryId;
    private String categoryName;

    private Date date;
    private String title;
    private String description;
    private BigDecimal cost;

    public ExpenseModel() {
    }

    public ExpenseModel(Expense expense, ExpenseCategory expenseCategory) {
        id = expense.getId();
        tenantId = expense.getTenant();

        categoryId = expense.getCategoryId();
        categoryName = expenseCategory.getName();

        date = expense.getDate();
        title = expense.getTitle();
        description = expense.getDescription();
        cost = expense.getCost();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }


}