package com.arobs.service.agrowork;

import com.arobs.entity.AgroWork;
import com.arobs.repository.AgroWorkRepository;
import com.arobs.service.BaseEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgroWorkService extends BaseEntityService<AgroWork, AgroWorkRepository> {

    @Autowired
    private AgroWorkRepository agroWorkRepository;

    public AgroWorkRepository getRepository() {
        return agroWorkRepository;
    }

    public List<AgroWork> find(Long parcelCropId) {
        return agroWorkRepository.find(parcelCropId);
    }

}
