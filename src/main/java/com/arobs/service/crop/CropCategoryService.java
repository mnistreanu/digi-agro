package com.arobs.service.crop;

import com.arobs.entity.CropCategory;
import com.arobs.interfaces.HasRepository;
import com.arobs.repository.CropCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CropCategoryService implements HasRepository<CropCategoryRepository> {

    @Autowired
    private CropCategoryRepository cropCategoryRepository;

    public List<CropCategory> find() {
           return getRepository().find();
    }

    @Override
    public CropCategoryRepository getRepository() {
        return cropCategoryRepository;
    }
}
