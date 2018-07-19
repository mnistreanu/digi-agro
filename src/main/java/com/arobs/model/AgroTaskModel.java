package com.arobs.model;


import com.arobs.entity.AgroTask;
import com.arobs.entity.AgroWorkType;
import com.arobs.entity.Machine;
import com.arobs.entity.WorkType;
import com.arobs.enums.MachineType;
import com.arobs.enums.MotorType;
import com.arobs.enums.WorkName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class AgroTaskModel implements Serializable {

    private Long id;
    private AgroWorkType type;
    private String title;
    private String description;
    private Long tenantId;
    private Long createdBy;
    private Date scheduledStart;
    private Date scheduledEnd;

    public AgroTaskModel() {
    }

    public AgroTaskModel(AgroTask entity) {
        this.id = entity.getId();
        this.type = entity.getWorkType();
        this.title = entity.getTitle();
        this.description = entity.getDescription();
        this.tenantId = entity.getTenantId();
        this.createdBy = entity.getCreatedBy();
        this.scheduledStart = entity.getScheduledStart();
        this.scheduledEnd = entity.getScheduledEnd();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AgroWorkType getType() {
        return type;
    }

    public void setType(AgroWorkType type) {
        this.type = type;
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

    public Date getScheduledStart() {
        return scheduledStart;
    }

    public void setScheduledStart(Date scheduledStart) {
        this.scheduledStart = scheduledStart;
    }

    public Date getScheduledEnd() {
        return scheduledEnd;
    }

    public void setScheduledEnd(Date scheduledEnd) {
        this.scheduledEnd = scheduledEnd;
    }
}
