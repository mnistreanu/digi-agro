package com.arobs.entity;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by mihail.gorgos on 02.09.2018.
 */
@Entity
@Table(name = "expense_item")
public class ExpenseItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "expense_id")
    private Long expenseId;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private ExpenseCategory category;

    @Column(name = "title")
    private String title;

    @Column(name = "norm_qty")
    private Double normQuantity;

    @Column(name = "actual_qty")
    private Double actualQuantity;

    @Column(name = "unit_of_measure")
    private String unitOfMeasure;

    @Column(name = "norm_per_unit")
    private Double normPerUnit;

    @Column(name = "actual_per_unit")
    private Double actualPerUnit;

    @Column(name = "unit_cost")
    private BigDecimal unitCost;

    @Column(name = "total_cost")
    private BigDecimal totalCost;

    @Column(name = "currency")
    private String currency;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(Long expenseId) {
        this.expenseId = expenseId;
    }

    public ExpenseCategory getCategory() {
        return category;
    }

    public void setCategory(ExpenseCategory category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getNormQuantity() {
        return normQuantity;
    }

    public void setNormQuantity(Double normQuantity) {
        this.normQuantity = normQuantity;
    }

    public Double getActualQuantity() {
        return actualQuantity;
    }

    public void setActualQuantity(Double actualQuantity) {
        this.actualQuantity = actualQuantity;
    }

    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    public Double getNormPerUnit() {
        return normPerUnit;
    }

    public void setNormPerUnit(Double normPerUnit) {
        this.normPerUnit = normPerUnit;
    }

    public Double getActualPerUnit() {
        return actualPerUnit;
    }

    public void setActualPerUnit(Double actualPerUnit) {
        this.actualPerUnit = actualPerUnit;
    }

    public BigDecimal getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(BigDecimal unitCost) {
        this.unitCost = unitCost;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
