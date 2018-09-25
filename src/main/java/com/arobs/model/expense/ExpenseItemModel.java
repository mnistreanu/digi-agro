package com.arobs.model.expense;

import com.arobs.entity.ExpenseItem;

import java.math.BigDecimal;

public class ExpenseItemModel {

    private Long id;
    private String title;
    private Double amount;
    private BigDecimal totalCost;

    private boolean deleted = false;

    public ExpenseItemModel() {
    }

    public ExpenseItemModel(ExpenseItem entity) {
        id = entity.getId();
        title = entity.getTitle();
        amount = entity.getActualQuantity();
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

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
