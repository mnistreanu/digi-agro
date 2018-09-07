package com.arobs.entity;

import javax.persistence.*;
import java.util.Date;


@Entity
public class ExpenseGoal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long expenseItemId;

    private String unitOfMeasure;

    private Double amount;

    private String description;

    public ExpenseGoal() {
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

    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
