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
@Deprecated
@Table(name = "parcel_crop_work")
public class ParcelCropWork {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Long id;

    @Column(name = "tenant_id")
    private Long tenant;

    @Column(name = "parcel_crop_id")
    private Long parcelCropId;

    @Column(name = "work_type_id")
    private Long workTypeId;

    @Column(name = "title")
    private String title;

    @Column(name = "work_date")
    private Date workDate;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "created_by")
    private Long createdBy;



}
