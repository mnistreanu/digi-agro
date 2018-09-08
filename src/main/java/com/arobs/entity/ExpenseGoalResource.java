package com.arobs.entity;

import javax.persistence.*;

@Entity
@Table(name = "expense_goal_resource")
public class ExpenseGoalResource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "expense_goal_id")
    private Long expenseGoalId;

    @Column (name = "resource_id")
    private Long resourceId;

    @Column (name = "table_name")
    private String tableName;

    public ExpenseGoalResource() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getExpenseGoalId() {
        return expenseGoalId;
    }

    public void setExpenseGoalId(Long expenseGoalId) {
        this.expenseGoalId = expenseGoalId;
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
