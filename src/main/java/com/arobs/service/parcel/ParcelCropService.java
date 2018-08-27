package com.arobs.service.parcel;

import com.arobs.entity.ParcelCrop;
import com.arobs.interfaces.HasRepository;
import com.arobs.repository.ParcelCropRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ParcelCropService implements HasRepository<ParcelCropRepository> {

    @Autowired
    private ParcelCropRepository parcelCropRepository;

    public ParcelCropRepository getRepository() {
        return parcelCropRepository;
    }

    public Date getLastPlantedDate(Long parcelId) {
        return getRepository().getLastPlantedDate(parcelId);
    }

    public ParcelCrop find(Long parcelId) {
        Date plantedDate = getLastPlantedDate(parcelId);
        return getRepository().find(parcelId, plantedDate);
    }

}
