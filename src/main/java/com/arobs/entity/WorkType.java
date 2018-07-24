package com.arobs.entity;

import com.arobs.enums.WorkName;

import javax.persistence.*;

@Entity
public class WorkType {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private WorkName name;

    public WorkType() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public WorkName getName() {
        return name;
    }

    public void setName(WorkName name) {
        this.name = name;
    }
}
