package com.arobs.service.forecast;

import com.arobs.entity.ForecastHarvest;
import com.arobs.model.forecast.ForecastHarvestModel;
import com.arobs.repository.ForecastHarvestRepository;
import com.arobs.service.AuthService;
import com.arobs.service.BaseEntityService;
import com.arobs.utils.StaticUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ForecastHarvestService extends BaseEntityService<ForecastHarvest, ForecastHarvestRepository> {

    @Autowired
    private AuthService authService;
    @Autowired
    private ForecastHarvestRepository harvestRepository;

    @Override
    public ForecastHarvestRepository getRepository() {
        return harvestRepository;
    }

    @Transactional
    public void create(Long snapshotId, List<ForecastHarvestModel> harvestModels) {

        Map<Long, ForecastHarvest> harvestMap = find(snapshotId).stream()
                .collect(Collectors.toMap(ForecastHarvest::getId, h -> h));

        if (StaticUtil.isEmpty(harvestModels)) {
            if (!harvestMap.isEmpty()) {
                delete(harvestMap.values());
            }
            return;
        }

        List<ForecastHarvest> harvests = new ArrayList<>();
        Long userId = authService.getCurrentUser().getId();

        for (ForecastHarvestModel model : harvestModels) {

            ForecastHarvest harvest;
            if (model.getId() != null) {
                harvest = harvestMap.remove(model.getId());
            } else {
                harvest = new ForecastHarvest();
                harvest.setForecastSnapshotId(snapshotId);
                harvest.setCreatedAt(model.getCreatedAt());
                harvest.setCreatedBy(userId);
            }

            harvests.add(harvest);
            harvest.setFactorName(model.getFactorName());
            harvest.setQuantity(model.getQuantity());
        }

        if (!harvestMap.isEmpty()) {
            delete(harvestMap.values());
        }

        save(harvests);
    }

    public List<ForecastHarvest> find(Long forecastSnapshotId) {
        return getRepository().find(forecastSnapshotId);
    }
}
