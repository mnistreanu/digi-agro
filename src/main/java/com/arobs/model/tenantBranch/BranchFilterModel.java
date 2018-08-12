package com.arobs.model.tenantBranch;


import java.util.List;

public class BranchFilterModel {

    private Long skipRootId;
    private List<Long> tenants;

    public Long getSkipRootId() {
        return skipRootId;
    }

    public void setSkipRootId(Long skipRootId) {
        this.skipRootId = skipRootId;
    }

    public List<Long> getTenants() {
        return tenants;
    }

    public void setTenants(List<Long> tenants) {
        this.tenants = tenants;
    }
}
