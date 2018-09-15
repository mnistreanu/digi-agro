package com.arobs.entity;

import javax.persistence.*;

/**
 * Created by mihail.gorgos on 14.07.2018.
 */
@Entity
@Table(name = "pesticide_harmful_organ")
public class PesticideHarmfulOrgan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "pesticide_id")
    private Long pesticideId;

    @Column (name = "harmful_organ_id")
    private Long harmfulOrganId;

    public PesticideHarmfulOrgan() {
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

    public Long getHarmfulOrganId() {
        return harmfulOrganId;
    }

    public void setHarmfulOrganId(Long harmfulOrganId) {
        this.harmfulOrganId = harmfulOrganId;
    }
}
