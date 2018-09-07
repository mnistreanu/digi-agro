package com.arobs.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by mihail.gorgos on 02.09.2018.
 */
@Entity
@Table(name = "expense")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tenant_id")
    private Long tenant;

    @Column(name = "expense_date")
    private Date expenseDate;

    @Column(name = "title")
    private String title;

    @Column(name = "created_by")
    private Long createdBy;

    @Column(name = "created_at")
    private Date createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTenant() {
        return tenant;
    }

    public void setTenant(Long tenant) {
        this.tenant = tenant;
    }

    public Date getExpenseDate() {
        return expenseDate;
    }

    public void setExpenseDate(Date expenseDate) {
        this.expenseDate = expenseDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
