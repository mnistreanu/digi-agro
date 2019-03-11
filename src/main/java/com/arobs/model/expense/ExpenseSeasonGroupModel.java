package com.arobs.model.expense;

import java.math.BigDecimal;

public class ExpenseSeasonGroupModel {

    private Long cropSeasonId;
    private Long categoryId;
    private String categoryName;
    private Integer periodIndex;
    private BigDecimal cost;

    public ExpenseSeasonGroupModel(Long cropSeasonId, Long categoryId, String categoryName, Integer periodIndex, BigDecimal cost) {
        this.cropSeasonId = cropSeasonId;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.periodIndex = periodIndex;
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

    public Integer getPeriodIndex() {
        return periodIndex;
    }

    public void setPeriodIndex(Integer periodIndex) {
        this.periodIndex = periodIndex;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
