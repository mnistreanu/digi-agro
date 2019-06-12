package com.arobs.service;

import com.arobs.entity.Machine;
import com.arobs.model.EmployeeModel;
import com.arobs.model.machine.MachineModel;
import com.arobs.repository.MachineRepository;
import com.arobs.repository.custom.CommonCustomRepository;
import com.arobs.service.agrowork.AgroWorkTypeService;
import com.arobs.utils.StaticUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MachineService extends BaseEntityService<Machine, MachineRepository> {

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
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private MachineGroupService machineGroupService;

    @Override
    public MachineRepository getRepository() {
        return machineRepository;
    }

    public boolean isUnique(Long currentId, String field, String value) {
        return commonCustomRepository.isUnique("Machine", currentId, field, value);
    }

    public List<Machine> find(Long tenantId) {
        return getRepository().find(tenantId);
    }

    public List<Machine> find(Long tenantId, Long machineGroupId) {
        if (machineGroupId == null) {
            return find(tenantId);
        }
        return getRepository().find(tenantId, machineGroupId);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        getRepository().softDelete(id);
    }

    @Transactional(rollbackOn = Exception.class)
    public Machine save(MachineModel model, Long tenant) {
        Machine entity;

        if (model.getId() == null) {
            entity = new Machine();
            entity.setTenant(tenantService.getOne(tenant));
        } else {
            entity = findOne(model.getId());
        }

        copyValues(entity, model);
        return save(entity);
    }

    private void copyValues(Machine entity, MachineModel model) {

        entity.setBrand(brandService.register(model.getBrand()));

        entity.setIdentifier(model.getIdentifier());
        entity.setModel(model.getModel());
        entity.setType(model.getType());

        entity.setFabricationYear(model.getFabricationYear());
        entity.setFabricationCountry(model.getFabricationCountry());

        entity.setMotorType(model.getMotorType());
        entity.setConsumption(model.getConsumption());
        entity.setPower(model.getPower());
        entity.setSpeedOnRoad(model.getSpeedOnRoad());
        entity.setSpeedInWork(model.getSpeedInWork());

        if (model.getMachineGroupId() == null) {
            entity.setMachineGroup(null);
        } else {
            entity.setMachineGroup(machineGroupService.getOne(model.getMachineGroupId()));
        }

        entity.getWorkTypes().clear();
        if (!StaticUtil.isEmpty(model.getWorkTypes())) {
            entity.getWorkTypes().addAll(workTypeService.findAll(model.getWorkTypes()));
        }

        entity.getEmployees().clear();
        if (!StaticUtil.isEmpty(model.getEmployees())) {
            List<Long> ids = model.getEmployees().stream().map(EmployeeModel::getId).collect(Collectors.toList());
            entity.getEmployees().addAll(employeeService.findAll(ids));
        }
    }
}
