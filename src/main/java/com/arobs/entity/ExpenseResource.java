package com.arobs.entity;

import javax.persistence.*;

@Entity
@Table(name = "expense_resource")
public class ExpenseResource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "expense_id")
    private Long expenseId;

    @Column (name = "resource_id")
    private Long resourceId;

    @Column (name = "table_name")
    private String tableName;

    public ExpenseResource() {
    }

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

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
