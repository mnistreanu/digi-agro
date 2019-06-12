package com.arobs.service.parcel;

import com.arobs.entity.ParcelCropSeason;
import com.arobs.repository.ParcelCropSeasonRepository;
import com.arobs.service.BaseEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParcelHarvestSummaryService extends BaseEntityService<ParcelCropSeason, ParcelCropSeasonRepository> {

    @Autowired
    private ParcelCropSeasonRepository parcelCropRepository;

    public ParcelCropSeasonRepository getRepository() {
        return parcelCropRepository;
    }

}
