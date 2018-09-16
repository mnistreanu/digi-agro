package com.arobs.entity;

import javax.persistence.*;
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

    @Column (name = "fertilizer_type_id")
    private Long typeId;

    @Column (name = "name_ro")
    private String nameRo;

    @Column (name = "name_ru")
    private String nameRu;

    @Column (name = "description_ro", length = 4096)
    private String descriptionRo;

    @Column (name = "description_ru", length = 4096)
    private String descriptionRu;

//    @ManyToMany
//    @JoinTable(name="fertilizer_crop_category",
//            joinColumns= @JoinColumn(name="fertilizer_id"),
//            inverseJoinColumns = @JoinColumn(name = "crop_category_id"))
//    private List<Crop> crops;
//
    public Fertilizer() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
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

}
