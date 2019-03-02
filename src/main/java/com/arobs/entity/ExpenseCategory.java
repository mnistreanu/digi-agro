package com.arobs.entity;

import javax.persistence.*;

/**
 * Created by mihail.gorgos on 14.07.2018.
 */
@Entity
public class ExpenseCategory {

    // todo: remove?
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

    // todo: remove?
    @Column (name = "default_category_id")
    private Long defaultCategoryId;

    // todo: remove (refactor changelog)
    private String title;

    private String name;
    private String description;


    @Column(columnDefinition = "boolean default true")
    private boolean active = true;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
