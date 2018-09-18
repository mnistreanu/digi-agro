package com.arobs.entity;

import javax.persistence.*;

/**
 * Created by mihail.gorgos on 14.07.2018.
 */
@Entity
@Table(name = "pesticide_harmful_organism")
public class PesticideHarmfulOrganism {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "pesticide_id")
    private Long pesticideId;

    @Column (name = "harmful_organism_id")
    private Long harmfulOrganismId;

    public PesticideHarmfulOrganism() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPesticideId() {
        return pesticideId;
    }

    public void setPesticideId(Long pesticideId) {
        this.pesticideId = pesticideId;
    }

    public Long getHarmfulOrganismId() {
        return harmfulOrganismId;
    }

    public void setHarmfulOrganismId(Long harmfulOrganismId) {
        this.harmfulOrganismId = harmfulOrganismId;
    }
}
