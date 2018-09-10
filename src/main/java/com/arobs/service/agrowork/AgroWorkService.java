package com.arobs.service.agrowork;

import com.arobs.entity.AgroWork;
import com.arobs.interfaces.HasRepository;
import com.arobs.repository.AgroWorkRepository;
import com.arobs.repository.custom.AgroWorkCustomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgroWorkService implements HasRepository<AgroWorkRepository> {

    @Autowired
    private AgroWorkRepository agroWorkRepository;
    @Autowired
    private AgroWorkCustomRepository agroWorkCustomRepository;

    public AgroWorkRepository getRepository() {
        return agroWorkRepository;
    }

    public AgroWork findOne(Long id) {
        return getRepository().findOne(id);
    }

    public List<AgroWork> find(Long parcelCropId) {
        return agroWorkRepository.find(parcelCropId);
    }

    public AgroWork findLast(Long parcelCropId) {
        return agroWorkCustomRepository.findLast(parcelCropId);
    }

}
