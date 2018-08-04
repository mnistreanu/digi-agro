package com.arobs.service;

import com.arobs.entity.MachineTelemetry;
import com.arobs.interfaces.HasRepository;
import com.arobs.model.UpdateFieldModel;
import com.arobs.model.telemetry.MachineTelemetryModel;
import com.arobs.repository.MachineTelemetryRepository;
import com.arobs.repository.custom.MachineTelemetryCustomRepository;
import com.arobs.utils.NumericUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class TelemetryService implements HasRepository<MachineTelemetryRepository> {

    @Autowired
    private MachineTelemetryRepository machineTelemetryRepository;
    @Autowired
    private MachineTelemetryCustomRepository machineTelemetryCustomRepository;
    @Autowired
    private UserAccountService userAccountService;
    @Autowired
    private MachineService machineService;


    public MachineTelemetry findOne(Long id) {
        return getRepository().findOne(id);
    }

    public List<MachineTelemetry> findAll() {
        return getRepository().findAll();
    }


    public List<MachineTelemetry> find(String machineIdentifier, Long userId) {
        return getRepository().find(machineIdentifier, userId);
    }

    @Transactional
    public void remove(Long id) {
        getRepository().remove(id);
    }

    @Transactional(rollbackOn = Exception.class)
    public MachineTelemetry save(MachineTelemetryModel model, Long userId) {
        MachineTelemetry entity;

        if (model.getId() == null) {
            entity = new MachineTelemetry();
            entity.setUserAccount(userAccountService.findOne(userId));
        }
        else {
            entity = findOne(model.getId());
        }

        copyValues(entity, model);
        return getRepository().save(entity);
    }

    private void copyValues(MachineTelemetry entity, MachineTelemetryModel model) {
        entity.setLongitude(model.getLongitude());
        entity.setLatitude(model.getLatitude());

        if (entity.getCreatedAt() == null) {
            entity.setCreatedAt(new Date());
        }

        entity.setMachine(machineService.findByIdentifier(model.getMachineIdentifier()));
    }

    @Transactional
    public void updateCoordinate(UpdateFieldModel model) {
        machineTelemetryCustomRepository.updateCoordinate(model.getId(), model.getField(), NumericUtil.convertToBigDecimal(model.getValue()));
    }

    @Override
    public MachineTelemetryRepository getRepository() {
        return machineTelemetryRepository;
    }
}
