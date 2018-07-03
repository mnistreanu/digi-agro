package com.arobs.model;

import com.arobs.entity.WorkType;
import com.arobs.enums.WorkName;

public class WorkTypeModel {

    private Long id;
    private WorkName name;

    public WorkTypeModel() {
    }

    public WorkTypeModel(WorkType entity) {
        id = entity.getId();
        name = entity.getName();
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
