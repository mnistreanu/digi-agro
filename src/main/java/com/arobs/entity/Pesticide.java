package com.arobs.entity;

import com.arobs.enums.PesticideType;

import javax.persistence.*;
import java.util.List;

/**
 * Created by mihail.gorgos on 14.07.2018.
 */
@Entity
@Table(name = "pesticide")
public class Pesticide {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column (name = "pesticide_type")
    private PesticideType pesticideType;

    @Column (name = "name_ro")
    private String nameRo;

    @Column (name = "name_ru")
    private String nameRu;

    @Column (name = "description_ro", length = 4096)
    private String descriptionRo;

    @Column (name = "description_ru", length = 4096)
    private String descriptionRu;

    @ManyToMany
    @JoinTable(name="pesticide_pest",
            joinColumns= @JoinColumn(name="pesticide_id"),
            inverseJoinColumns = @JoinColumn(name = "pest_id"))
    private List<Pest> pests;

    @Column (name = "pests_ro", length = 4098)
    private String pestsRo;

    @Column (name = "pests_ru", length = 4098)
    private String pestsRu;

    @Column (name = "active_substance")
    private String activeSubstance;

    @Column (name = "toxicity_group")
    private Integer toxicityGroup;

    public Pesticide() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PesticideType getPesticideType() {
        return pesticideType;
    }

    public void setPesticideType(PesticideType pesticideType) {
        this.pesticideType = pesticideType;
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

    public List<Pest> getPests() {
        return pests;
    }

    public void setPests(List<Pest> pests) {
        this.pests = pests;
    }

    public String getPestsRo() {
        return pestsRo;
    }

    public void setPestsRo(String pestsRo) {
        this.pestsRo = pestsRo;
    }

    public String getPestsRu() {
        return pestsRu;
    }

    public void setPestsRu(String pestsRu) {
        this.pestsRu = pestsRu;
    }

    public String getActiveSubstance() {
        return activeSubstance;
    }

    public void setActiveSubstance(String activeSubstance) {
        this.activeSubstance = activeSubstance;
    }

    public Integer getToxicityGroup() {
        return toxicityGroup;
    }

    public void setToxicityGroup(Integer toxicityGroup) {
        this.toxicityGroup = toxicityGroup;
    }
}
