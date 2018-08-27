package com.arobs.service.parcel;

import com.arobs.entity.ParcelCropWork;
import com.arobs.interfaces.HasRepository;
import com.arobs.repository.ParcelCropWorkRepository;
import com.arobs.repository.custom.ParcelCropWorkCustomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParcelCropWorkService implements HasRepository<ParcelCropWorkRepository> {

    @Autowired
    private ParcelCropWorkRepository parcelCropWorkRepository;
    @Autowired
    private ParcelCropWorkCustomRepository parcelCropWorkCustomRepository;

    public ParcelCropWorkRepository getRepository() {
        return parcelCropWorkRepository;
    }

    public ParcelCropWork findOne(Long id) {
        return getRepository().findOne(id);
    }

    public ParcelCropWork findLast(Long parcelCropId) {
        return parcelCropWorkCustomRepository.findLast(parcelCropId);
    }

}
