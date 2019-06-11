package com.arobs.model.expense;

import java.math.BigDecimal;

public class ExpenseSummaryModel {

    private String categoryName;
    private BigDecimal cost;

    public ExpenseSummaryModel(String categoryName, BigDecimal cost) {
        this.categoryName = categoryName;
        this.cost = cost;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }
}
