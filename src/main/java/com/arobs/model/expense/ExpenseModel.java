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

    private Long rootCategoryId, subCategoryId;
    private String rootCategoryName, subCategoryName;

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
        ExpenseCategory rootCategory = expense.getRootCategory();
        ExpenseCategory subCategory = expense.getSubCategory();

        rootCategoryId = rootCategory.getId();
        rootCategoryName = rootCategory.getName();

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

    public Long getRootCategoryId() {
        return rootCategoryId;
    }

    public void setRootCategoryId(Long rootCategoryId) {
        this.rootCategoryId = rootCategoryId;
    }

    public Long getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(Long subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public String getRootCategoryName() {
        return rootCategoryName;
    }

    public void setRootCategoryName(String rootCategoryName) {
        this.rootCategoryName = rootCategoryName;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }
}