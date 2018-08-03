package com.arobs.service;

import com.arobs.entity.*;
import com.arobs.interfaces.HasRepository;
import com.arobs.model.reminder.ReminderModel;
import com.arobs.repository.ForecastHarvestRepository;
import com.arobs.repository.ForecastParcelRepository;
import com.arobs.repository.ForecastRepository;
import com.arobs.repository.ForecastSnapshotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class ForecastService implements HasRepository<ForecastRepository> {

    @Autowired
    private AuthService authService;

    @Autowired
    private ForecastRepository forecastRepository;

    @Autowired
    private ForecastSnapshotRepository snapshotRepository;

    @Autowired
    private ForecastParcelRepository forecastParcelRepository;

    @Autowired
    private ForecastHarvestRepository harvestRepository;

    @Override
    public ForecastRepository getRepository() {
        return forecastRepository;
    }

    public List<Forecast> findForecasts(Long tenantId) {
        return getRepository().find(tenantId);
    }

    public List<ForecastSnapshot> findSnapshots(Long forecastId) {
        return snapshotRepository.find(forecastId);
    }

    public List<ForecastParcel> findSnapshotParcels(Long forecastSnapshotId) {
        return forecastParcelRepository.find(forecastSnapshotId);
    }

    public List<ForecastHarvest> findHarvests(Long forecastSnapshotId) {
        return harvestRepository.find(forecastSnapshotId);
    }

    public Forecast findOne(Long id) {
        return getRepository().findOne(id);
    }
//
//    @Transactional
//    public void remove(Long id) {
//        getRepository().remove(id);
//    }
//
//    @Transactional(rollbackOn = Exception.class)
//    public Reminder save(ReminderModel model) {
//        Reminder entity;
//
//        if (model.getId() == null) {
//            entity = new Reminder();
//            entity.setCreatedBy(authService.getCurrentUser().getId());
//            entity.setTenantId(model.getTenantId());
//        }
//        else {
//            entity = findOne(model.getId());
//        }
//
//        copyValues(entity, model);
//
//        return getRepository().save(entity);
//    }
//
//    @Transactional
//    private void copyValues(Reminder entity, ReminderModel model) {
//        entity.setTitle(model.getTitle());
//        entity.setDescription(model.getDescription());
//        entity.setStarting(model.getStarting());
//        entity.setEnding(model.getEnding());
//        entity.setWorkType(agroWorkTypeService.findOne(model.getWorkTypeId()));
//    }
//
//    @Transactional
//    public void changeSchedule(Long id, Date start, Date end) {
//        getRepository().changeSchedule(id, start, end);
//    }
}
