package com.arobs.repository.custom;


import com.arobs.model.tenantBranch.TenantBranchFilterRequestModel;
import com.arobs.model.tenantBranch.TenantBranchModel;

import java.util.List;

public interface TenantBranchCustomRepository {
    List<TenantBranchModel> findByFilter(TenantBranchFilterRequestModel filterRequestModel);
}
