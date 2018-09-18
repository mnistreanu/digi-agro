package com.arobs.entity;

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

    @Column (name = "pesticide_type_id")
    private Long typeId;

    @Column (name = "name_ro")
    private String nameRo;

    @Column (name = "name_ru")
    private String nameRu;

    @Column (name = "name2_ro")
    private String name2Ro;

    @Column (name = "name2_ru")
    private String name2Ru;

    @Column (name = "description_ro", length = 4096)
    private String descriptionRo;

    @Column (name = "description_ru", length = 4096)
    private String descriptionRu;

    @ManyToMany
    @JoinTable(name="pesticide_harmful_organism",
            joinColumns= @JoinColumn(name="pesticide_id"),
            inverseJoinColumns = @JoinColumn(name = "harmful_organism_id"))
    private List<Pest> pests;

    @Column (name = "harmful_organisms_ro", length = 4098)
    private String harmfulOrganismsRo;

    @Column (name = "harmful_organisms_ru", length = 4098)
    private String harmfulOrganismsRu;

    @Column (name = "active_substance")
    private String activeSubstance;

    @Column (name = "consumption_norm")
    private Double consumptionNorm;

    public Pesticide() {
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

    public String getName2Ro() {
        return name2Ro;
    }

    public void setName2Ro(String name2Ro) {
        this.name2Ro = name2Ro;
    }

    public String getName2Ru() {
        return name2Ru;
    }

    public void setName2Ru(String name2Ru) {
        this.name2Ru = name2Ru;
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

    public String getHarmfulOrganismsRo() {
        return harmfulOrganismsRo;
    }

    public void setHarmfulOrganismsRo(String harmfulOrganismsRo) {
        this.harmfulOrganismsRo = harmfulOrganismsRo;
    }

    public String getHarmfulOrganismsRu() {
        return harmfulOrganismsRu;
    }

    public void setHarmfulOrganismsRu(String harmfulOrganismsRu) {
        this.harmfulOrganismsRu = harmfulOrganismsRu;
    }

    public String getActiveSubstance() {
        return activeSubstance;
    }

    public void setActiveSubstance(String activeSubstance) {
        this.activeSubstance = activeSubstance;
    }

    public Double getConsumptionNorm() {
        return consumptionNorm;
    }

    public void setConsumptionNorm(Double consumptionNorm) {
        this.consumptionNorm = consumptionNorm;
    }
}
