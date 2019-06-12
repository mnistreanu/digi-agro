package com.arobs.service;

import com.arobs.entity.EventType;
import com.arobs.model.EventTypeModel;
import com.arobs.repository.EventTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EventTypeService extends BaseEntityService<EventType, EventTypeRepository> {

    @Autowired
    private EventTypeRepository eventTypeRepository;

    @Override
    public EventTypeRepository getRepository() {
        return eventTypeRepository;
    }

    public List<EventType> find(Long tenantId) {
        return getRepository().find(tenantId);
    }

    public List<EventType> findRoots(Long tenantId) {
        return getRepository().findRoots(tenantId);
    }

    @Transactional
    public EventType save(EventTypeModel model, Long tenantId) {

        EventType eventType;

        if (model.getId() == null) {
            eventType = new EventType();
            eventType.setTenantId(tenantId);
        } else {
            eventType = findOne(model.getId());
        }

        if (model.getParentId() != null) {
            eventType.setParent(findOne(model.getParentId()));
        }

        eventType.setName(model.getName());
        eventType.setDescription(model.getDescription());

        return save(eventType);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        getRepository().softDelete(id);
    }

}
