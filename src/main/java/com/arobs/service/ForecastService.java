package com.arobs.service;

import com.arobs.entity.*;
import com.arobs.interfaces.HasRepository;
import com.arobs.model.forecast.ForecastModel;
import com.arobs.repository.*;
import com.arobs.repository.custom.ForecastSnapshotCustomRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class ForecastService implements HasRepository<ForecastRepository> {

    @Autowired
    private AuthService authService;

    @Autowired
    private ForecastRepository forecastRepository;

    @Autowired
    private ForecastSnapshotRepository forecastSnapshotRepository;

    @Autowired
    private ForecastSnapshotCustomRepository forecastSnapshotCustomRepository;

    @Autowired
    private ForecastParcelRepository forecastParcelRepository;

    @Autowired
    private ForecastHarvestRepository harvestRepository;

    @Autowired
    private CropRepository cropRepository;

    @Autowired
    private CropVarietyRepository cropVarietyRepository;

    @Override
    public ForecastRepository getRepository() {
        return forecastRepository;
    }

    public List<Forecast> findForecasts(Long tenantId) {
        return getRepository().find(tenantId);
    }

    public List<ForecastSnapshot> findSnapshots(Long forecastId) {
        return forecastSnapshotRepository.find(forecastId);
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

    @Transactional(rollbackOn = Exception.class)
    public void create(ForecastModel model, Long tenantId) {

        Forecast forecast = new Forecast();
        forecast.setTenantId(tenantId);

        Date now = new Date();
        forecast.setHarvestingYear(model.getHarvestingYear());
        forecast.setCreatedAt(now);
        forecast.setCreatedBy(authService.getCurrentUser().getId());

        forecast.setCropId(model.getCropId());
        forecast.setCropVarietyId(model.getCropVarietyId());

        forecast.setName(model.getForecastName());
        forecast.setDescription(model.getDescription());

        forecast = saveForecast(forecast);

        createSnapshot(forecast.getId(), model, now);
    }

    @Transactional
    public void createSnapshot(Long forecastId, ForecastModel forecastModel, Date date) {
        ForecastSnapshot lastSnapshot = getLastSnapshot(forecastId);

        ForecastSnapshot snapshot = new ForecastSnapshot();

        if (lastSnapshot == null) {
            snapshot.setForecastId(forecastId);
        }
        else {
            BeanUtils.copyProperties(lastSnapshot, snapshot);
            snapshot.setId(null);
        }

        snapshot.setUnitOfMeasure(forecastModel.getUnitOfMeasure());
        snapshot.setUnitPrice(forecastModel.getUnitPrice());
        snapshot.setQuantityHectar(forecastModel.getQuantityHectar());
        snapshot.setCreatedAt(date);

        snapshot = saveSnapshot(snapshot);

        List<ForecastParcel> forecastParcels = new ArrayList<>();
        for (Long parcelId : forecastModel.getParcels()) {
            ForecastParcel fp = new ForecastParcel();
            fp.setForecastSnapshotId(snapshot.getId());
            fp.setParcelId(parcelId);
            forecastParcels.add(fp);
        }

        saveForecastParcels(forecastParcels);
    }

    private ForecastSnapshot getLastSnapshot(Long forecastId) {
        return forecastSnapshotCustomRepository.getLastSnapshot(forecastId);
    }

    @Transactional
    public Forecast saveForecast(Forecast forecast) {
        return getRepository().save(forecast);
    }

    @Transactional
    public ForecastSnapshot saveSnapshot(ForecastSnapshot snapshot) {
        return forecastSnapshotRepository.save(snapshot);
    }

    @Transactional
    public List<ForecastParcel> saveForecastParcels(List<ForecastParcel> items) {
        return forecastParcelRepository.save(items);
    }

}
