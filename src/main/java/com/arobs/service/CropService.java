package com.arobs.service;

import com.arobs.entity.Crop;
import com.arobs.interfaces.HasRepository;
import com.arobs.repository.CropRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CropService implements HasRepository<CropRepository> {

    @Autowired
    private CropRepository cropRepository;

    public List<Crop> find(Long categoryId) {
        if (categoryId != null) {
            return getRepository().find(categoryId);
        } else {
            return getRepository().find();
        }
    }

    @Override
    public CropRepository getRepository() {
        return cropRepository;
    }
}
