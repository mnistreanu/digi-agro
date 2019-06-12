package com.arobs.service;

import com.arobs.entity.MachineTelemetry;
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
public class TelemetryService extends BaseEntityService<MachineTelemetry, MachineTelemetryRepository> {

    @Autowired
    private MachineTelemetryRepository machineTelemetryRepository;
    @Autowired
    private MachineTelemetryCustomRepository machineTelemetryCustomRepository;
    @Autowired
    private UserAccountService userAccountService;
    @Autowired
    private MachineService machineService;

    @Override
    public MachineTelemetryRepository getRepository() {
        return machineTelemetryRepository;
    }

    public List<MachineTelemetry> find(Long machineId, Long userId) {
        return getRepository().find(machineId, userId);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        getRepository().softDelete(id);
    }

    @Transactional(rollbackOn = Exception.class)
    public MachineTelemetry save(MachineTelemetryModel model, Long userId) {
        MachineTelemetry entity;

        if (model.getId() == null) {
            entity = new MachineTelemetry();
            entity.setUserAccount(userAccountService.findOne(userId));
            entity.setMachine(machineService.findOne(model.getMachineId()));
            entity.setCreatedAt(new Date());
        } else {
            entity = findOne(model.getId());
        }

        copyValues(entity, model);
        return save(entity);
    }

    private void copyValues(MachineTelemetry entity, MachineTelemetryModel model) {
        entity.setLongitude(model.getLongitude());
        entity.setLatitude(model.getLatitude());
    }

    @Transactional
    public void updateCoordinate(UpdateFieldModel model) {
        machineTelemetryCustomRepository.updateCoordinate(model.getId(), model.getField(), NumericUtil.convertToBigDecimal(model.getValue()));
    }
}
