package com.arobs.service.crop;

import com.arobs.entity.CropCategory;
import com.arobs.repository.CropCategoryRepository;
import com.arobs.service.BaseEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CropCategoryService extends BaseEntityService<CropCategory, CropCategoryRepository> {

    @Autowired
    private CropCategoryRepository cropCategoryRepository;

    @Override
    public CropCategoryRepository getRepository() {
        return cropCategoryRepository;
    }

    public List<CropCategory> find() {
        return getRepository().find();
    }
}
