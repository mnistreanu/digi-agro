package com.arobs.service.forecast;

import com.arobs.entity.ForecastHarvest;
import com.arobs.interfaces.HasRepository;
import com.arobs.model.forecast.ForecastHarvestModel;
import com.arobs.repository.ForecastHarvestRepository;
import com.arobs.service.AuthService;
import com.arobs.utils.StaticUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ForecastHarvestService implements HasRepository<ForecastHarvestRepository> {

    @Autowired
    private AuthService authService;

    @Autowired
    private ForecastHarvestRepository harvestRepository;

    @Override
    public ForecastHarvestRepository getRepository() {
        return harvestRepository;
    }

    @Transactional
    public ForecastHarvest save(ForecastHarvest item) {
        return getRepository().save(item);
    }

    @Transactional
    public List<ForecastHarvest> save(List<ForecastHarvest> items) {
        return getRepository().save(items);
    }

    @Transactional
    public void remove(Collection<ForecastHarvest> items) {
        getRepository().delete(items);
    }

    @Transactional
    public void create(Long snapshotId, List<ForecastHarvestModel> harvestModels) {

        Map<Long, ForecastHarvest> harvestMap = find(snapshotId).stream()
                .collect(Collectors.toMap(ForecastHarvest::getId, h -> h));

        if (StaticUtil.isEmpty(harvestModels)) {
            if (!harvestMap.isEmpty()) {
                remove(harvestMap.values());
            }
            return;
        }

        List<ForecastHarvest> harvests = new ArrayList<>();
        Long userId = authService.getCurrentUser().getId();

        for (ForecastHarvestModel model : harvestModels) {

            ForecastHarvest harvest;
            if (model.getId() != null) {
                harvest = harvestMap.remove(model.getId());
            }
            else {
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
            remove(harvestMap.values());
        }

        save(harvests);
    }

    public List<ForecastHarvest> find(Long forecastSnapshotId) {
        return getRepository().find(forecastSnapshotId);
    }
}
