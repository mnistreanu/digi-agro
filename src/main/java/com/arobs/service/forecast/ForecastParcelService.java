package com.arobs.service.forecast;

import com.arobs.entity.ForecastParcel;
import com.arobs.interfaces.HasRepository;
import com.arobs.repository.ForecastParcelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class ForecastParcelService implements HasRepository<ForecastParcelRepository> {

    @Autowired
    private ForecastParcelRepository forecastParcelRepository;

    @Override
    public ForecastParcelRepository getRepository() {
        return forecastParcelRepository;
    }

    @Transactional
    public List<ForecastParcel> save(List<ForecastParcel> items) {
        return getRepository().save(items);
    }

    @Transactional
    public void remove(Long snapshotId) {
        getRepository().remove(snapshotId);
    }

    @Transactional
    public void create(Long snapshotId, List<Long> parcels) {
        remove(snapshotId);
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
