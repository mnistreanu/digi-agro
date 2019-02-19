package com.arobs.service.parcel;

import com.arobs.entity.ParcelCropSeason;
import com.arobs.interfaces.HasRepository;
import com.arobs.repository.ParcelCropSeasonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class ParcelCropSeasonService implements HasRepository<ParcelCropSeasonRepository> {

    @Autowired
    private ParcelCropSeasonRepository parcelCropRepository;

    public ParcelCropSeasonRepository getRepository() {
        return parcelCropRepository;
    }

    public List<ParcelCropSeason> find(Long parcelId) {
        return getRepository().find(parcelId);
    }

    public ParcelCropSeason find(Long parcelId, int harvestYear) {
        return getRepository().find(parcelId, harvestYear);
    }

    public ParcelCropSeason findLast(Long parcelId) {
        int harvestYear = 0;
        List<ParcelCropSeason> list = this.find(parcelId);
        for (ParcelCropSeason pcs:list) {
            if (harvestYear < pcs.getCropSeason().getHarvestYear()) {
                harvestYear = pcs.getCropSeason().getHarvestYear();
            }
        }
        return getRepository().find(parcelId, harvestYear);
    }


}
