package com.arobs.service;

import com.arobs.entity.TenantBranch;
import com.arobs.interfaces.HasRepository;
import com.arobs.model.tenantBranch.TenantBranchModel;
import com.arobs.repository.TenantBranchRepository;
import com.arobs.repository.custom.CommonCustomRepository;
import com.arobs.utils.StaticUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TenantBranchService implements HasRepository<TenantBranchRepository> {

    @Autowired
    private TenantBranchRepository tenantBranchRepository;
    @Autowired
    private CommonCustomRepository commonCustomRepository;
    @Autowired
    private TenantService tenantService;

    @Override
    public TenantBranchRepository getRepository() {
        return tenantBranchRepository;
    }

    public TenantBranch findOne(Long id) {
        return getRepository().findOne(id);
    }

    public List<TenantBranch> find(List<Long> tenants) {
        return getRepository().find(tenants);
    }

    public List<TenantBranch> find(List<Long> tenants, Long skipRootId) {
        List<TenantBranch> branches = find(tenants);
        if (skipRootId != null) {
            List<Long> treeDown = new ArrayList<>();
            getTreeDown(skipRootId, treeDown);
            branches = branches.stream().filter(b -> !treeDown.contains(b.getId())).collect(Collectors.toList());
        }
        return branches;
    }

    public List<TenantBranch> findAll(List<Long> ids) {

        if (StaticUtil.isEmpty(ids)) {
            return new ArrayList<>();
        }
        return getRepository().findAll(ids);
    }

    public boolean isUnique(Long currentId, String value) {
        return commonCustomRepository.isUnique("TenantBranch", currentId, "name", value);
    }

    @Transactional
    public TenantBranch save(TenantBranchModel model) {

        TenantBranch branch;

        if (model.getId() == null) {
            branch = new TenantBranch();
            branch.setTenant(model.getTenantId() == null ? null : tenantService.findOne(model.getTenantId()));
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
        entity.setParent(model.getParentId() == null ? null : findOne(model.getParentId()));
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
}
