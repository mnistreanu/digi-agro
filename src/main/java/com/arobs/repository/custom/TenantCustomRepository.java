package com.arobs.repository.custom;


import com.arobs.model.tenant.TenantFilterRequestModel;
import com.arobs.model.tenant.TenantModel;

import java.util.List;

public interface TenantCustomRepository {
    List<TenantModel> findByFilter(TenantFilterRequestModel filterRequestModel);
}
