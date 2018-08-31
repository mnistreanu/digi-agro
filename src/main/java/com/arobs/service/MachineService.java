package com.arobs.service;

import com.arobs.entity.Machine;
import com.arobs.interfaces.HasRepository;
import com.arobs.model.MachineModel;
import com.arobs.repository.MachineRepository;
import com.arobs.repository.custom.CommonCustomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class MachineService implements HasRepository<MachineRepository> {

    @Autowired
    private MachineRepository machineRepository;
    @Autowired
    private BrandService brandService;
    @Autowired
    private AgroWorkTypeService workTypeService;
    @Autowired
    private CommonCustomRepository commonCustomRepository;
    @Autowired
    private TenantService tenantService;

    @Override
    public MachineRepository getRepository() {
        return machineRepository;
    }

    public boolean isUnique(Long currentId, String field, String value) {
        return commonCustomRepository.isUnique("Machine", currentId, field, value);
    }

    public Machine findByIdentifier(String identifier) {
        return getRepository().findByIdentifier(identifier);
    }

    public Machine findOne(Long id) {
        return getRepository().findOne(id);
    }

    public List<Machine> find(Long tenantId) {
        return getRepository().find(tenantId);
    }

    public List<String> fetchIdentifiers() {
        return getRepository().fetchIdentifiers();
    }

    @Transactional
    public void remove(Long id) {
        getRepository().remove(id);
    }

    @Transactional(rollbackOn = Exception.class)
    public Machine save(MachineModel model, Long tenant) {
        Machine entity;

        if (model.getId() == null) {
            entity = new Machine();
            entity.setTenant(tenantService.findOne(tenant));
        }
        else {
            entity = findOne(model.getId());
        }

        copyValues(entity, model);
        return getRepository().save(entity);
    }

    private void copyValues(Machine entity, MachineModel model) {

        entity.setBrand(brandService.register(model.getBrand()));

        entity.setIdentifier(model.getIdentifier());
        entity.setName(model.getName());
        entity.setType(model.getType());

        entity.setFabricationDate(model.getFabricationDate());
        entity.setFabricationCountry(model.getFabricationCountry());

        entity.setMotorType(model.getMotorType());
        entity.setConsumption(model.getConsumption());
        entity.setPower(model.getPower());
        entity.setSpeedOnRoad(model.getSpeedOnRoad());
        entity.setSpeedInWork(model.getSpeedInWork());

        entity.getWorkTypes().clear();
        entity.getWorkTypes().addAll(workTypeService.find());
    }
}
