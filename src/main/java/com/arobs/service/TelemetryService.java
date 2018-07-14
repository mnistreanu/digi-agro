package com.arobs.service;

import com.arobs.entity.Telemetry;
import com.arobs.interfaces.HasRepository;
import com.arobs.model.UpdateFieldModel;
import com.arobs.model.telemetry.TelemetryModel;
import com.arobs.repository.TelemetryRepository;
import com.arobs.repository.custom.TelemetryCustomRepository;
import com.arobs.utils.NumericUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TelemetryService implements HasRepository<TelemetryRepository> {

    @Autowired
    private TelemetryRepository telemetryRepository;
    @Autowired
    private TelemetryCustomRepository telemetryCustomRepository;
    @Autowired
    private UserAccountService userAccountService;
    @Autowired
    private MachineService machineService;


    public Telemetry findOne(Long id) {
        return getRepository().findOne(id);
    }

    public TelemetryModel findModelById(Long id) {
        return new TelemetryModel(getRepository().findOne(id));
    }

    public List<Telemetry> findAll() {
        return getRepository().findAll();
    }

    public List<TelemetryModel> findByMachineIdentifierAndUsername(String machineIdentifier, String username) {
        List<Telemetry> items = getRepository().findByMachineIdentifierAndUsername(machineIdentifier, username);
        return items.stream().map(TelemetryModel::new).collect(Collectors.toList());
    }

    @Transactional
    public void remove(Long id) {
        getRepository().remove(id);
    }

    @Transactional(rollbackOn = Exception.class)
    public Telemetry save(TelemetryModel model) {
        Telemetry entity;

        if (model.getId() == null) {
            entity = new Telemetry();
        }
        else {
            entity = findOne(model.getId());
        }

        copyValues(entity, model);
        return getRepository().save(entity);
    }

    private void copyValues(Telemetry entity, TelemetryModel model) {
        entity.setLongitude(model.getLongitude());
        entity.setLatitude(model.getLatitude());

        if (entity.getCreatedAt() == null) {
            entity.setCreatedAt(new Date());
        }

        entity.setUserAccount(userAccountService.findByUsername(model.getUsername()));
        entity.setMachine(machineService.findByIdentifier(model.getMachineIdentifier()));


    }

    @Transactional
    public void updateCoordinate(UpdateFieldModel model) {
        telemetryCustomRepository.updateCoordinate(model.getId(), model.getField(), NumericUtil.convertToBigDecimal(model.getValue()));
    }

    @Override
    public TelemetryRepository getRepository() {
        return telemetryRepository;
    }
}
