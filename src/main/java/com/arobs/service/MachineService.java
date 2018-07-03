package com.arobs.service;

import com.arobs.entity.Machine;
import com.arobs.interfaces.HasRepository;
import com.arobs.model.MachineModel;
import com.arobs.repository.MachineRepository;
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
    private OwnerService ownerService;
    @Autowired
    private WorkTypeService workTypeService;


    public boolean checkIdentifierUnique(Long id, String value) {
        if (id == -1) {
            return getRepository().countByIdentifier(value) == 0;
        }
        return getRepository().countByIdentifierEscapeId(id, value) == 0;
    }

    public Machine findByIdentifier(String identifier) {
        return getRepository().findByIdentifier(identifier);
    }

    public Machine findOne(Long id) {
        return getRepository().findOne(id);
    }

    public MachineModel findModelById(Long id) {
        return new MachineModel(getRepository().findOne(id));
    }

    public List<Machine> findAll() {
        return getRepository().findAll();
    }

    public List<String> fetchIdentifiers() {
        return getRepository().fetchIdentifiers();
    }

    @Transactional
    public void remove(Long id) {
        getRepository().remove(id);
    }

    @Transactional(rollbackOn = Exception.class)
    public Machine save(MachineModel model) {
        Machine entity;

        if (model.getId() == null) {
            entity = new Machine();
        }
        else {
            entity = findOne(model.getId());
        }

        copyValues(entity, model);
        return getRepository().save(entity);
    }

    @Transactional
    private void copyValues(Machine entity, MachineModel model) {
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

        entity.setOwner(ownerService.register(model.getOwner()));
        entity.setBrand(brandService.register(model.getBrand()));

        entity.getWorkTypes().clear();
        entity.getWorkTypes().addAll(workTypeService.findByNames(model.getWorkTypes()));
    }

    @Override
    public MachineRepository getRepository() {
        return machineRepository;
    }
}
