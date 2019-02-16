package com.arobs.entity;

import javax.persistence.*;

/**
 * Created by mihail.gorgos on 14.07.2018.
 */
@Entity
@Table(name = "expense_category")
public class ExpenseCategory {

    public static final long MACHINERY = 1L;
    public static final long FUEL = 2L;
    public static final long SOWING = 3L;
    public static final long AGRO_WORKS = 4L;
    public static final long FERTILIZERS = 5L;
    public static final long PESTICIDES = 6L;
    public static final long IRRIGATION = 7L;
    public static final long RENTING = 8L;
    public static final long OPERATIONAL = 9L;
    public static final long OTHERS = 10L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "tenant_id")
    private Long tenantId;

    @Column (name = "parent_id")
    private Long parentId;

    @Column (name = "default_category_id")
    private Long defaultCategoryId;

    @Column (name = "title", length = 64)
    private String title;

    @Column (name = "name", length = 256)
    private String name;

    public ExpenseCategory() {
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getDefaultCategoryId() {
        return defaultCategoryId;
    }

    public void setDefaultCategoryId(Long defaultCategoryId) {
        this.defaultCategoryId = defaultCategoryId;
    }
}
