package com.arobs.service;

import com.arobs.entity.MachineGroup;
import com.arobs.model.machine.MachineGroupModel;
import com.arobs.repository.MachineGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class MachineGroupService extends BaseEntityService<MachineGroup, MachineGroupRepository> {

    @Autowired
    private MachineGroupRepository machineGroupRepository;

    @Override
    public MachineGroupRepository getRepository() {
        return machineGroupRepository;
    }

    public List<MachineGroup> find(Long tenantId) {
        return getRepository().find(tenantId);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        getRepository().softDelete(id);
    }

    @Transactional(rollbackOn = Exception.class)
    public MachineGroup save(MachineGroupModel model, Long tenantId) {
        MachineGroup entity;

        if (model.getId() == null) {
            entity = new MachineGroup();
            entity.setTenantId(tenantId);
        } else {
            entity = findOne(model.getId());
        }

        copyValues(entity, model);
        return save(entity);
    }

    private void copyValues(MachineGroup entity, MachineGroupModel model) {
        entity.setName(model.getName());
    }
}
