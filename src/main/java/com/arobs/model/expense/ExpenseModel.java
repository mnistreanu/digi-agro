package com.arobs.model.expense;

import com.arobs.entity.expense.Expense;
import com.arobs.entity.expense.ExpenseCategory;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ExpenseModel implements Serializable {

    private Long id;
    private Long tenantId;
    private Long cropSeasonId;

    private Long categoryId, subCategoryId;
    private String categoryName, subCategoryName;

    private Date date;
    private String title;
    private String description;
    private BigDecimal cost;

    public ExpenseModel() {
    }

    public ExpenseModel(Expense expense) {
        id = expense.getId();
        tenantId = expense.getTenant();
        cropSeasonId = expense.getCropSeasonId();

        // todo: optimize
        ExpenseCategory category = expense.getCategory();
        ExpenseCategory subCategory = expense.getSubCategory();

        categoryId = category.getId();
        categoryName = category.getName();

        if (subCategory != null) {
            subCategoryId = subCategory.getId();
            subCategoryName = subCategory.getName();
        }

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

    public Long getCropSeasonId() {
        return cropSeasonId;
    }

    public void setCropSeasonId(Long cropSeasonId) {
        this.cropSeasonId = cropSeasonId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(Long subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }
}