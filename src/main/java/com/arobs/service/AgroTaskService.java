package com.arobs.service;

import com.arobs.entity.AgroTask;
import com.arobs.interfaces.HasRepository;
import com.arobs.model.agroTask.AgroTaskModel;
import com.arobs.repository.AgroTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class AgroTaskService implements HasRepository<AgroTaskRepository> {

    @Autowired
    private AuthService authService;
    @Autowired
    private AgroTaskRepository agroTaskRepository;
    @Autowired
    private AgroWorkTypeService agroWorkTypeService;

    @Override
    public AgroTaskRepository getRepository() {
        return agroTaskRepository;
    }

    public List<AgroTask> find(Long tenantId, Date scheduledTime) {
        if (scheduledTime == null) {
            return getRepository().findAll(tenantId);
        } else {
            return getRepository().findInFuture(tenantId, scheduledTime);
        }
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
            entity.setCreatedBy(authService.getCurrentUser().getId());
            entity.setTenantId(model.getTenantId());
        }
        else {
            entity = findOne(model.getId());
        }

        copyValues(entity, model);

        return getRepository().save(entity);
    }

    @Transactional
    private void copyValues(AgroTask entity, AgroTaskModel model) {
        entity.setTitle(model.getTitle());
        entity.setDescription(model.getDescription());
        entity.setScheduledStart(model.getScheduledStart());
        entity.setScheduledEnd(model.getScheduledEnd());
        entity.setWorkType(agroWorkTypeService.findOne(model.getWorkTypeId()));
    }

    @Transactional
    public void changeSchedule(Long id, Date start, Date end) {
        getRepository().changeSchedule(id, start, end);
    }
}
