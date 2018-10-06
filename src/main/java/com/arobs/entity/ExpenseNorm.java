package com.arobs.entity;

import javax.persistence.*;

@Entity
public class ExpenseNorm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long expenseItemId;

    private Double perUnit;
    private Double total;

    public ExpenseNorm() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getExpenseItemId() {
        return expenseItemId;
    }

    public void setExpenseItemId(Long expenseItemId) {
        this.expenseItemId = expenseItemId;
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
