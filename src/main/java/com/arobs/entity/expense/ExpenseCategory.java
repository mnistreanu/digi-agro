package com.arobs.entity.expense;

import javax.persistence.*;

/**
 * Created by mihail.gorgos on 14.07.2018.
 */
@Entity
public class ExpenseCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "tenant_id")
    private Long tenantId;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private ExpenseCategory parent;
    @Column (name = "parent_id", insertable = false, updatable = false)
    private Long parentId;

    private String name;
    private String description;

    @Column(columnDefinition = "boolean default true")
    private boolean active = true;

    public ExpenseCategory() {
    }

    public String getRootName() {

        /*
            We have only 2 levels
         */

        if (parent == null) {
            return name;
        }

        return parent.getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ExpenseCategory getParent() {
        return parent;
    }

    public void setParent(ExpenseCategory parent) {
        this.parent = parent;
    }
}
