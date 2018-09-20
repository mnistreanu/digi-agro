package com.arobs.service.chemicals;

import com.arobs.entity.Pesticide;
import com.arobs.interfaces.HasRepository;
import com.arobs.repository.PesticideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PesticideService implements HasRepository<PesticideRepository> {

    @Autowired
    private PesticideRepository pesticideRepository;

    public List<Pesticide> find() {
        return getRepository().find();
    }

//    public List<Pesticide> find(Long typeId) {
//        return getRepository().find(typeId);
//    }

    @Override
    public PesticideRepository getRepository() {
        return pesticideRepository;
    }
}
