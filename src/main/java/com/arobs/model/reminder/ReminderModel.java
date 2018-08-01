package com.arobs.model.reminder;


import com.arobs.entity.Reminder;

import java.io.Serializable;
import java.util.Date;

public class ReminderModel implements Serializable {

    private Long id;
    private Integer workTypeId;
    private String title;
    private String description;
    private Long tenantId;
    private Long createdBy;
    private Date starting;
    private Date ending;

    public ReminderModel() {
    }

    public ReminderModel(Reminder entity) {
        this.id = entity.getId();
        this.workTypeId = entity.getWorkType().getId();
        this.title = entity.getTitle();
        this.description = entity.getDescription();
        this.tenantId = entity.getTenantId();
        this.createdBy = entity.getCreatedBy();
        this.starting = entity.getStarting();
        this.ending = entity.getEnding();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getWorkTypeId() {
        return workTypeId;
    }

    public void setWorkTypeId(Integer workTypeId) {
        this.workTypeId = workTypeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Date getStarting() {
        return starting;
    }

    public void setStarting(Date starting) {
        this.starting = starting;
    }

    public Date getEnding() {
        return ending;
    }

    public void setEnding(Date ending) {
        this.ending = ending;
    }
}
