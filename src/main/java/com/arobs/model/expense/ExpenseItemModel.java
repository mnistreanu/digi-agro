package com.arobs.model.expense;

import com.arobs.entity.ExpenseItem;

import java.math.BigDecimal;

public class ExpenseItemModel {

    private Long id;
    private String title;
    private BigDecimal totalCost;

    public ExpenseItemModel() {
    }

    public ExpenseItemModel(ExpenseItem entity) {
        id = entity.getId();
        title = entity.getTitle();
        totalCost = entity.getTotalCost();
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

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }
}
