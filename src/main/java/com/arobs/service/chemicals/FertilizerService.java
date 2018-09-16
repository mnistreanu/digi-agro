package com.arobs.service.chemicals;

import com.arobs.entity.Fertilizer;
import com.arobs.interfaces.HasRepository;
import com.arobs.repository.FertilizerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FertilizerService implements HasRepository<FertilizerRepository> {

    @Autowired
    private FertilizerRepository fertilizerRepository;

    public List<Fertilizer> find() {
        return getRepository().find();
    }

    public List<Fertilizer> find(Long typeId) {
        return getRepository().find(typeId);
    }

    @Override
    public FertilizerRepository getRepository() {
        return fertilizerRepository;
    }
}
