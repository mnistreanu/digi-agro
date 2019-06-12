package com.arobs.service.forecast;

import com.arobs.entity.forecast.ForecastParcel;
import com.arobs.repository.ForecastParcelRepository;
import com.arobs.service.BaseEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class ForecastParcelService extends BaseEntityService<ForecastParcel, ForecastParcelRepository> {

    @Autowired
    private ForecastParcelRepository forecastParcelRepository;

    @Override
    public ForecastParcelRepository getRepository() {
        return forecastParcelRepository;
    }

    @Transactional
    public void delete(Long snapshotId) {
        getRepository().delete(snapshotId);
    }

    @Transactional
    public void create(Long snapshotId, List<Long> parcels) {
        delete(snapshotId);
        List<ForecastParcel> forecastParcels = new ArrayList<>();
        for (Long parcelId : parcels) {
            ForecastParcel fp = new ForecastParcel();
            fp.setForecastSnapshotId(snapshotId);
            fp.setParcelId(parcelId);
            forecastParcels.add(fp);
        }
        save(forecastParcels);
    }

    public List<ForecastParcel> find(Long snapshotId) {
        return getRepository().find(snapshotId);
    }
}
