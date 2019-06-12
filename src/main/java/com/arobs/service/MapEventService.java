package com.arobs.service;

import com.arobs.entity.MapEvent;
import com.arobs.model.MapEventModel;
import com.arobs.model.UpdateFieldModel;
import com.arobs.repository.MapEventRepository;
import com.arobs.repository.custom.MapEventCustomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class MapEventService extends BaseEntityService<MapEvent, MapEventRepository> {

    @Autowired
    private MapEventRepository mapEventRepository;
    @Autowired
    private MapEventCustomRepository mapEventCustomRepository;
    @Autowired
    private UserAccountService userAccountService;

    @Override
    public MapEventRepository getRepository() {
        return mapEventRepository;
    }

    public List<MapEvent> find(Long userId) {
        return getRepository().find(userId);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        getRepository().softDelete(id);
    }

    @Transactional(rollbackOn = Exception.class)
    public MapEvent save(MapEventModel model, Long userId) {
        MapEvent entity;

        if (model.getId() == null) {
            entity = new MapEvent();
            entity.setUserAccount(userAccountService.findOne(userId));
        } else {
            entity = findOne(model.getId());
        }

        copyValues(entity, model);
        return save(entity);
    }

    private void copyValues(MapEvent entity, MapEventModel model) {
        entity.setLongitude(model.getLongitude());
        entity.setLatitude(model.getLatitude());

        if (entity.getCreatedAt() == null) {
            entity.setCreatedAt(new Date());
        }

        entity.setMessage(model.getMessage());
    }

    @Transactional
    public void update(UpdateFieldModel model) {
        mapEventCustomRepository.update(model);
    }
}
