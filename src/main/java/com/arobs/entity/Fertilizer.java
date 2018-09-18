package com.arobs.entity;

import com.arobs.enums.FertilizerType;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by mihail.gorgos on 14.07.2018.
 */
@Entity
@Table(name = "fertilizer")
public class Fertilizer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private FertilizerType fertilizerType;

    @Column(name = "name_ro")
    private String nameRo;

    @Column(name = "name_ru")
    private String nameRu;

    @Column(name = "description_ro", length = 4096)
    private String descriptionRo;

    @Column(name = "description_ru", length = 4096)
    private String descriptionRu;

    @Column(name = "deleted_at")
    private Date deletedAt;

    public Fertilizer() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameRo() {
        return nameRo;
    }

    public void setNameRo(String nameRo) {
        this.nameRo = nameRo;
    }

    public String getNameRu() {
        return nameRu;
    }

    public void setNameRu(String nameRu) {
        this.nameRu = nameRu;
    }

    public String getDescriptionRo() {
        return descriptionRo;
    }

    public void setDescriptionRo(String descriptionRo) {
        this.descriptionRo = descriptionRo;
    }

    public String getDescriptionRu() {
        return descriptionRu;
    }

    public void setDescriptionRu(String descriptionRu) {
        this.descriptionRu = descriptionRu;
    }

    public Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }

    public FertilizerType getFertilizerType() {
        return fertilizerType;
    }

    public void setFertilizerType(FertilizerType fertilizerType) {
        this.fertilizerType = fertilizerType;
    }
}
