package com.arobs.model.machine;


import com.arobs.entity.MachineGroup;

public class MachineGroupModel {

    private Long id;
    private Long tenantId;
    private String name;

    public MachineGroupModel() {
    }

    public MachineGroupModel(MachineGroup entity) {
        this.id = entity.getId();
        this.tenantId = entity.getTenantId();
        this.name = entity.getName();
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
