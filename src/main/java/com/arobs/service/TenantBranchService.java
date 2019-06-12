package com.arobs.service;

import com.arobs.entity.Branch;
import com.arobs.model.branch.BranchModel;
import com.arobs.repository.TenantBranchRepository;
import com.arobs.repository.custom.CommonCustomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TenantBranchService extends BaseEntityService<Branch, TenantBranchRepository> {

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

    public List<Branch> find(List<Long> tenants) {
        return getRepository().find(tenants);
    }

    public List<Branch> find(List<Long> tenants, Long skipRootId) {
        List<Branch> branches = find(tenants);
        if (skipRootId != null) {
            List<Long> treeDown = new ArrayList<>();
            getTreeDown(skipRootId, treeDown);
            branches = branches.stream().filter(b -> !treeDown.contains(b.getId())).collect(Collectors.toList());
        }
        return branches;
    }

    public boolean isUnique(Long currentId, String value) {
        return commonCustomRepository.isUnique("Branch", currentId, "name", value);
    }

    @Transactional
    public Branch save(BranchModel model) {

        Branch branch;

        if (model.getId() == null) {
            branch = new Branch();
            branch.setTenant(model.getTenantId() == null ? null : tenantService.getOne(model.getTenantId()));
        } else {
            branch = findOne(model.getId());
        }

        copyValues(branch, model);

        return save(branch);
    }

    private void copyValues(Branch entity, BranchModel model) {
        entity.setName(model.getName());
        entity.setDescription(model.getDescription());
        entity.setCountyId(model.getCounty().getId());
        entity.setCity(model.getCity());
        entity.setAddress(model.getAddress());
        entity.setPhones(model.getPhones());
        entity.setParent(model.getParentId() == null ? null : findOne(model.getParentId()));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        List<Long> treeDown = new ArrayList<>();
        getTreeDown(id, treeDown);
        for (Long itemId : treeDown) {
            getRepository().delete(itemId);
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
