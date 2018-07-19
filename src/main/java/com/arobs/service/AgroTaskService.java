package com.arobs.service;

import com.arobs.entity.AgroTask;
import com.arobs.interfaces.HasRepository;
import com.arobs.model.AgroTaskModel;
import com.arobs.repository.AgroTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class AgroTaskService implements HasRepository<AgroTaskRepository> {

    @Autowired
    private AgroTaskRepository agroTaskRepository;

    public List<AgroTask> find(Long tenantId) {
        return getRepository().find(tenantId);
    }

    public List<AgroTask> findFutureTasks(Long tenantId, Date scheduledTime) {
        return getRepository().findInFuture(tenantId, scheduledTime);
    }

    public List<AgroTask> findPastTasks(Long tenantId, Date scheduledTime) {
        return getRepository().findInPast(tenantId, scheduledTime);
    }

    public AgroTask findOne(Long id) {
        return getRepository().findOne(id);
    }

    @Transactional
    public void remove(Long id) {
        getRepository().remove(id);
    }

    @Transactional(rollbackOn = Exception.class)
    public AgroTask save(AgroTaskModel model) {
        AgroTask entity;

        if (model.getId() == null) {
            entity = new AgroTask();
        }
        else {
            entity = findOne(model.getId());
        }

        copyValues(entity, model);
        return getRepository().save(entity);
    }

    @Transactional
    private void copyValues(AgroTask entity, AgroTaskModel model) {
//        entity.setIdentifier(model.getIdentifier());
//        entity.setName(model.getName());
//        entity.setType(model.getType());
//
//        entity.setFabricationDate(model.getFabricationDate());
//        entity.setFabricationCountry(model.getFabricationCountry());
//
//        entity.setMotorType(model.getMotorType());
//        entity.setConsumption(model.getConsumption());
//        entity.setPower(model.getPower());
//        entity.setSpeedOnRoad(model.getSpeedOnRoad());
//        entity.setSpeedInWork(model.getSpeedInWork());
//
//        entity.setOwner(ownerService.register(model.getOwner()));
//        entity.setBrand(brandService.register(model.getBrand()));
//
//        entity.getWorkTypes().clear();
//        entity.getWorkTypes().addAll(workTypeService.findByNames(model.getWorkTypes()));
    }
    @Override
    public AgroTaskRepository getRepository() {
        return agroTaskRepository;
    }
}
