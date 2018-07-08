package com.arobs.service;

import com.arobs.entity.MapEvent;
import com.arobs.interfaces.HasRepository;
import com.arobs.model.MapEventModel;
import com.arobs.model.UpdateFieldModel;
import com.arobs.repository.MapEventRepository;
import com.arobs.repository.custom.MapEventCustomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MapEventService implements HasRepository<MapEventRepository> {

    @Autowired
    private MapEventRepository mapEventRepository;
    @Autowired
    private MapEventCustomRepository mapEventCustomRepository;
    @Autowired
    private UserAccountService userAccountService;
    @Autowired
    private MachineService machineService;


    public MapEvent findOne(Long id) {
        return getRepository().findOne(id);
    }

    public MapEventModel findModelById(Long id) {
        return new MapEventModel(getRepository().findOne(id));
    }

    public List<MapEventModel> findByMachineIdentifierAndUsername(String machineIdentifier, String username) {
        List<MapEvent> items = getRepository().findByMachineIdentifierAndUsername(machineIdentifier, username);
        return items.stream().map(MapEventModel::new).collect(Collectors.toList());
    }

    @Transactional
    public void remove(Long id) {
        getRepository().remove(id);
    }

    @Transactional(rollbackOn = Exception.class)
    public MapEvent save(MapEventModel model) {
        MapEvent entity;

        if (model.getId() == null) {
            entity = new MapEvent();
        }
        else {
            entity = findOne(model.getId());
        }

        copyValues(entity, model);
        return getRepository().save(entity);
    }

    private void copyValues(MapEvent entity, MapEventModel model) {
        entity.setLongitude(model.getLongitude());
        entity.setLatitude(model.getLatitude());

        if (entity.getCreatedAt() == null) {
            entity.setCreatedAt(new Date());
        }

        entity.setMessage(model.getMessage());

        entity.setUserAccount(userAccountService.findByUsername(model.getUsername()));
        entity.setMachine(machineService.findByIdentifier(model.getMachineIdentifier()));

    }

    @Transactional
    public void update(UpdateFieldModel model) {
        mapEventCustomRepository.update(model);
    }

    @Override
    public MapEventRepository getRepository() {
        return mapEventRepository;
    }
}
