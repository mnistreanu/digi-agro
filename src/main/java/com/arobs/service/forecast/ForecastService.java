package com.arobs.service.forecast;

import com.arobs.entity.forecast.Forecast;
import com.arobs.model.forecast.ForecastModel;
import com.arobs.repository.ForecastRepository;
import com.arobs.service.AuthService;
import com.arobs.service.BaseEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class ForecastService extends BaseEntityService<Forecast, ForecastRepository> {

    @Autowired
    private AuthService authService;
    @Autowired
    private ForecastRepository forecastRepository;
    @Autowired
    private ForecastSnapshotService forecastSnapshotService;

    @Override
    public ForecastRepository getRepository() {
        return forecastRepository;
    }

    public List<Forecast> find(Long tenantId, boolean onlyActive) {
        if (onlyActive) {
            return getRepository().findActive(tenantId);
        } else {
            return getRepository().findAll(tenantId);
        }
    }

    @Transactional
    public Forecast create(ForecastModel model, Long tenantId) {

        Date now = new Date();
        Forecast forecast;

        if (model.getId() == null) {
            forecast = new Forecast();
            forecast.setTenantId(tenantId);
            forecast.setCreatedAt(now);
            forecast.setCreatedBy(authService.getCurrentUser().getId());
        } else {
            forecast = findOne(model.getId());
        }

        forecast.setName(model.getName());
        forecast.setDescription(model.getDescription());

        forecast.setCropCategoryId(model.getCropCategoryId());
        forecast.setCropId(model.getCropId());
        forecast.setCropVarietyId(model.getCropVarietyId());
        forecast.setHarvestingYear(model.getHarvestingYear());

        forecast = save(forecast);
        forecastSnapshotService.create(forecast.getId(), model.getSnapshot(), now);

        return forecast;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Long userId = authService.getCurrentUser().getId();
        Date now = new Date();
        getRepository().softDelete(id, userId, now);
    }

    public ForecastModel getModel(Long id) {
        Forecast forecast = findOne(id);
        ForecastModel forecastModel = new ForecastModel(forecast);
        forecastModel.setSnapshot(forecastSnapshotService.getModel(id));
        return forecastModel;
    }

}
