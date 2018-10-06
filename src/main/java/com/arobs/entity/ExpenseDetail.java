package com.arobs.entity;

import com.arobs.enums.ExpenseDetailType;

import javax.persistence.*;

@Entity
public class ExpenseDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long expenseItemId;

    @Enumerated(EnumType.STRING)
    private ExpenseDetailType itemKey;

    private String itemValue;

    public ExpenseDetail() {
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

    public ExpenseDetailType getItemKey() {
        return itemKey;
    }

    public void setItemKey(ExpenseDetailType itemKey) {
        this.itemKey = itemKey;
    }

    public String getItemValue() {
        return itemValue;
    }

    public void setItemValue(String itemValue) {
        this.itemValue = itemValue;
    }
}
