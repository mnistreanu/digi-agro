package com.arobs.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by mihail.gorgos on 16.08.2018.
 * Aici se va defini relatie parcela cu cultura agricola si cu lucrarea efetuata.
 *
 * Se va folosi tabelul AGRO_WORK
 */
@Entity
@Table(name = "agro_work")
public class AgroWork {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Long id;

    @Column(name = "tenant_id")
    private Long tenant;

    @Column(name = "parcel_crop_id")
    private Long parcelCropId;

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name = "work_type_id")
    private AgroWorkType workType;

    @Column(name = "title")
    private String title;

    @Column(name = "work_date")
    private Date workDate;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "created_by")
    private Long createdBy;

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

    public Long getParcelCropId() {
        return parcelCropId;
    }

    public void setParcelCropId(Long parcelCropId) {
        this.parcelCropId = parcelCropId;
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

    public Date getWorkDate() {
        return workDate;
    }

    public void setWorkDate(Date workDate) {
        this.workDate = workDate;
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
}
