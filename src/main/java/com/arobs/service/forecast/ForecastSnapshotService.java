package com.arobs.service.forecast;

import com.arobs.entity.ForecastParcel;
import com.arobs.entity.ForecastSnapshot;
import com.arobs.model.forecast.ForecastHarvestModel;
import com.arobs.model.forecast.ForecastSnapshotModel;
import com.arobs.repository.ForecastSnapshotRepository;
import com.arobs.repository.custom.ForecastSnapshotCustomRepository;
import com.arobs.service.BaseEntityService;
import com.arobs.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ForecastSnapshotService extends BaseEntityService<ForecastSnapshot, ForecastSnapshotRepository> {


    @Autowired
    private ForecastSnapshotRepository forecastSnapshotRepository;
    @Autowired
    private ForecastSnapshotCustomRepository forecastSnapshotCustomRepository;
    @Autowired
    private ForecastParcelService forecastParcelService;
    @Autowired
    private ForecastHarvestService forecastHarvestService;

    @Override
    public ForecastSnapshotRepository getRepository() {
        return forecastSnapshotRepository;
    }

    @Transactional
    public void create(Long forecastId, ForecastSnapshotModel model, Date now) {
        ForecastSnapshot lastSnapshot = getLastSnapshot(forecastId);

        ForecastSnapshot snapshot;
        if (lastSnapshot != null && DateUtil.isSameYearAndMonth(now, lastSnapshot.getCreatedAt())) {
            snapshot = lastSnapshot;
        } else {
            snapshot = new ForecastSnapshot();
            snapshot.setForecastId(forecastId);
            snapshot.setCreatedAt(now);
        }

        snapshot.setUnitOfMeasure(model.getUnitOfMeasure());
        snapshot.setCurrency(model.getCurrency());
        snapshot.setUnitPrice(model.getUnitPrice());

        snapshot = save(snapshot);

        forecastParcelService.create(snapshot.getId(), model.getParcels());
        forecastHarvestService.create(snapshot.getId(), model.getHarvests());
    }

    private ForecastSnapshot getLastSnapshot(Long forecastId) {
        return forecastSnapshotCustomRepository.getLastSnapshot(forecastId);
    }

    public ForecastSnapshotModel getModel(Long forecastId) {
        ForecastSnapshot snapshot = getLastSnapshot(forecastId);
        List<Long> parcels = forecastParcelService.find(snapshot.getId()).stream().map(ForecastParcel::getParcelId).collect(Collectors.toList());
        List<ForecastHarvestModel> forecastHarvestModels = forecastHarvestService.find(snapshot.getId()).stream().map(ForecastHarvestModel::new).collect(Collectors.toList());
        return new ForecastSnapshotModel(snapshot, parcels, forecastHarvestModels);
    }
}
