package com.arobs.service;

import com.arobs.entity.MachineGroup;
import com.arobs.interfaces.HasRepository;
import com.arobs.model.MachineGroupModel;
import com.arobs.repository.MachineGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class MachineGroupService implements HasRepository<MachineGroupRepository> {

    @Autowired
    private MachineGroupRepository machineGroupRepository;

    @Override
    public MachineGroupRepository getRepository() {
        return machineGroupRepository;
    }

//    public boolean isUnique(Long currentId, String field, String value) {
//        return commonCustomRepository.isUnique("MachineGroup", currentId, field, value);
//    }

    public MachineGroup findOne(Long id) {
        return getRepository().findOne(id);
    }

    public List<MachineGroup> find(Long tenantId) {
        return getRepository().find(tenantId);
    }

    public List<MachineGroup> findAll(List<Long> ids) {
        return getRepository().findAll(ids);
    }

    @Transactional
    public void remove(Long id) {
        getRepository().remove(id);
    }

    @Transactional(rollbackOn = Exception.class)
    public MachineGroup save(MachineGroupModel model, Long tenantId) {
        MachineGroup entity;

        if (model.getId() == null) {
            entity = new MachineGroup();
            entity.setTenantId(tenantId);
        }
        else {
            entity = findOne(model.getId());
        }

        copyValues(entity, model);
        return getRepository().save(entity);
    }

    private void copyValues(MachineGroup entity, MachineGroupModel model) {
        entity.setTenantId(model.getTenantId());
        entity.setName(model.getName());
    }
}
