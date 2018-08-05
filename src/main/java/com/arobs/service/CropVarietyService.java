package com.arobs.service;

import com.arobs.entity.CropVariety;
import com.arobs.interfaces.HasRepository;
import com.arobs.repository.CropVarietyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CropVarietyService implements HasRepository<CropVarietyRepository> {

    @Autowired
    private CropVarietyRepository cropVarietyRepository;

    public List<CropVariety> find(Long cropId) {
        return getRepository().find(cropId);
    }

    @Override
    public CropVarietyRepository getRepository() {
        return cropVarietyRepository;
    }
}
