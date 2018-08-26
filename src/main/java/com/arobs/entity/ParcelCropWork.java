package com.arobs.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by mihail.gorgos on 16.08.2018.
 * Aici se va defini relatie parcela cu cultura agricola si cu lucrarea efetuata.
 */
@Entity
@Table(name = "parcel_crop_work")
public class ParcelCropWork {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Long id;

    @Column(name = "parcel_crop_id")
    private Long parcelCropId;

    @Column(name = "work_type_id")
    private Long workTypeId;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "created_by")
    private Long createdBy;

    @Column(name = "deleted_at")
    private Date deletedAt;

    @Column(name = "deleted_by")
    private Long deletedBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParcelCropId() {
        return parcelCropId;
    }

    public void setParcelCropId(Long parcelCropId) {
        this.parcelCropId = parcelCropId;
    }

    public Long getWorkTypeId() {
        return workTypeId;
    }

    public void setWorkTypeId(Long workTypeId) {
        this.workTypeId = workTypeId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }

    public Long getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(Long deletedBy) {
        this.deletedBy = deletedBy;
    }
}
