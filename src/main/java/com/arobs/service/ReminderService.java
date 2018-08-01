package com.arobs.service;

import com.arobs.entity.Reminder;
import com.arobs.interfaces.HasRepository;
import com.arobs.model.reminder.ReminderModel;
import com.arobs.repository.ReminderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class ReminderService implements HasRepository<ReminderRepository> {

    @Autowired
    private AuthService authService;
    @Autowired
    private ReminderRepository reminderRepository;
    @Autowired
    private AgroWorkTypeService agroWorkTypeService;

    @Override
    public ReminderRepository getRepository() {
        return reminderRepository;
    }

    public List<Reminder> find(Long tenantId, Date scheduledTime) {
        if (scheduledTime == null) {
            return getRepository().find(tenantId);
        } else {
            return getRepository().find(tenantId, scheduledTime);
        }
    }

    public Reminder findOne(Long id) {
        return getRepository().findOne(id);
    }

    @Transactional
    public void remove(Long id) {
        getRepository().remove(id);
    }

    @Transactional(rollbackOn = Exception.class)
    public Reminder save(ReminderModel model) {
        Reminder entity;

        if (model.getId() == null) {
            entity = new Reminder();
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
    private void copyValues(Reminder entity, ReminderModel model) {
        entity.setTitle(model.getTitle());
        entity.setDescription(model.getDescription());
        entity.setStarting(model.getStarting());
        entity.setEnding(model.getEnding());
        entity.setWorkType(agroWorkTypeService.findOne(model.getWorkTypeId()));
    }

    @Transactional
    public void changeSchedule(Long id, Date start, Date end) {
        getRepository().changeSchedule(id, start, end);
    }
}
