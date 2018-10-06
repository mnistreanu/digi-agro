package com.arobs.model.expense;


import com.arobs.entity.ExpenseNorm;

public class ExpenseNormModel {

    private Long id;

    private Double perUnit;
    private Double total;

    public ExpenseNormModel(ExpenseNorm entity) {
        id = entity.getId();
        perUnit = entity.getPerUnit();
        total = entity.getTotal();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPerUnit() {
        return perUnit;
    }

    public void setPerUnit(Double perUnit) {
        this.perUnit = perUnit;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
