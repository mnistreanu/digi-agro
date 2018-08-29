package com.arobs.model;


import com.arobs.entity.AgroWorkType;

import java.io.Serializable;

public class AgroWorkTypeModel implements Serializable {

    private Long id;
    private Long tenantId;
    private Integer parentId;
    private String nameRo;
    private String nameRu;

    public AgroWorkTypeModel() {
    }

    public AgroWorkTypeModel(AgroWorkType workType) {
        this.id = workType.getId();
        this.tenantId = workType.getTenantId();
        this.parentId = workType.getParentId();
        this.nameRo = workType.getNameRo();
        this.nameRu = workType.getNameRu();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
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
}
