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

    public Date getLastPlantedDate(Long parcelId) {
        return getRepository().getLastPlantedDate(parcelId);
    }

    public ParcelCropSeason find(Long parcelId) {
        Integer harvestYear = Calendar.getInstance().get(Calendar.YEAR);
        Date plantedDate = getLastPlantedDate(parcelId);
        if (plantedDate != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(plantedDate);
            harvestYear = cal.get(Calendar.YEAR);
        }
        return getRepository().find(parcelId, harvestYear);
    }

    public List<ParcelCropSeason> findAll(Long parcelId) {
        return getRepository().find(parcelId);
    }

}
