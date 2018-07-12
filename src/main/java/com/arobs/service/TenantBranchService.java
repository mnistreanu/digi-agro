package com.arobs.service;

import com.arobs.entity.TenantBranch;
import com.arobs.interfaces.HasRepository;
import com.arobs.model.ListItemModel;
import com.arobs.model.tenantBranch.TenantBranchFilterRequestModel;
import com.arobs.model.tenantBranch.TenantBranchModel;
import com.arobs.repository.TenantBranchRepository;
import com.arobs.repository.custom.TenantBranchCustomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class TenantBranchService implements HasRepository<TenantBranchRepository> {

    @Autowired
    private TenantBranchRepository tenantBranchRepository;
    @Autowired
    private TenantBranchCustomRepository tenantBranchCustomRepository;
    @Autowired
    private TenantService tenantService;

    @Override
    public TenantBranchRepository getRepository() {
        return tenantBranchRepository;
    }

    public TenantBranch findOne(Long id) {
        return getRepository().findOne(id);
    }

    public List<TenantBranchModel> findByFilter(TenantBranchFilterRequestModel filterRequestModel) {
        return tenantBranchCustomRepository.findByFilter(filterRequestModel);
    }

    public boolean checkNameUnique(Long id, String name) {
        if (id == -1) {
            return getRepository().countByName(name) == 0;
        }
        return getRepository().countByNameEscapeId(id, name) == 0;
    }

    @Transactional
    public TenantBranch save(TenantBranchModel model) {

        TenantBranch branch;

        if (model.getId() == null) {
            branch = new TenantBranch();
        }
        else {
            branch = findOne(model.getId());
        }

        copyValues(branch, model);

        return save(branch);
    }

    private void copyValues(TenantBranch entity, TenantBranchModel model) {
        entity.setName(model.getName());
        entity.setDescription(model.getDescription());
        entity.setCountry(model.getCountry());
        entity.setCounty(model.getCounty());
        entity.setVillageCity(model.getVillageCity());
        entity.setAddress(model.getAddress());
        entity.setPhones(model.getPhones());
        if (model.getParentId() != null) {
            entity.setParent(findOne(model.getParentId()));
        }
        else {
            entity.setParent(null);
        }
        if (model.getTenantId() != null) {
            entity.setTenant(tenantService.findOne(model.getTenantId()));
        }
        else {
            entity.setTenant(null);
        }
    }

    @Transactional
    public TenantBranch save(TenantBranch TenantBranch) {
        return getRepository().save(TenantBranch);
    }

    @Transactional
    public void remove(Long id) {
        List<Long> treeDown = new ArrayList<>();
        getTreeDown(id, treeDown);
        for (Long itemId : treeDown) {
            getRepository().remove(itemId);
        }
    }

    private void getTreeDown(Long id, List<Long> treeDown) {
        List<Long> childIds = getChildren(id);
        for (Long childId : childIds) {
            getTreeDown(childId, treeDown);
        }
        treeDown.add(id);
    }

    private List<Long> getChildren(Long id) {
        return getRepository().getChildren(id);
    }

    public List<ListItemModel> fetchItemsByTenant(Long tenantId, Long skipId) {
        if (skipId == null) {
            return getRepository().fetchItemsByTenant(tenantId);
        }
        else {
            List<Long> treeDown = new ArrayList<>();
            getTreeDown(skipId, treeDown);
            return getRepository().fetchItemsByTenantAndNotIn(tenantId, treeDown);
        }
    }

    public List<ListItemModel> findByTenants(List<Long> tenants) {
        return getRepository().findByTenants(tenants);
    }

    public List<TenantBranch> findByIds(List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return new ArrayList<>();
        }
        return getRepository().findByIds(ids);
    }
}
