package com.arobs.model.expense;

import com.arobs.entity.ExpenseItem;

import java.math.BigDecimal;

public class ExpenseItemModel {

    private Long id;
    private String title;
    private Double quantity;
    private BigDecimal totalCost;

    private Long parentCategoryId;
    private Long categoryId;
    private String category;


    private boolean deleted = false;

    public ExpenseItemModel() {
    }

    public ExpenseItemModel(ExpenseItem entity) {
        id = entity.getId();
        title = entity.getTitle();
        quantity = entity.getActualQuantity();
        totalCost = entity.getTotalCost();
        if (entity.getCategory() != null) {
            parentCategoryId = entity.getCategory().getParentId();
            categoryId = entity.getCategory().getId();
            category = entity.getCategory().getName();
        }
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

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Long getParentCategoryId() {
        return parentCategoryId;
    }

    public void setParentCategoryId(Long parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }
}
