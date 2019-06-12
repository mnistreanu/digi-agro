package com.arobs.entity;

import com.arobs.entity.agro.AgroWorkType;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by mihail.gorgos on 18.07.2018.
 */
@Entity
@Table(name = "reminder")
public class Reminder extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "agro_work_type_id")
    private AgroWorkType workType;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "tenant_id")
    private Long tenantId;

    @Column(name = "created_by")
    private Long createdBy;

    @Column(name = "starting")
    private Date starting;

    @Column(name = "ending")
    private Date ending;

    public Reminder() {
    }

    public AgroWorkType getWorkType() {
        return workType;
    }

    public void setWorkType(AgroWorkType workType) {
        this.workType = workType;
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
