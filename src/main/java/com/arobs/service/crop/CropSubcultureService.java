package com.arobs.service.crop;

import com.arobs.entity.CropSubculture;
import com.arobs.interfaces.HasRepository;
import com.arobs.model.crop.CropSubcultureModel;
import com.arobs.repository.CropSubcultureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CropSubcultureService implements HasRepository<CropSubcultureRepository> {

    @Autowired
    private CropSubcultureRepository subCropRepository;

    @Autowired
    private CropService cropService;

    public List<CropSubculture> find() {
        return getRepository().find();
    }

    public List<CropSubculture> find(Long cropId) {
        return getRepository().find(cropId);
    }

    public CropSubculture findOne(Long id) {
        return getRepository().findOne(id);
    }

    @Transactional
    public void delete(Long id) {
        getRepository().delete(id);
    }

    @Transactional
    public CropSubculture save(CropSubculture subCrop) {
        return getRepository().save(subCrop);
    }

    @Transactional
    public CropSubculture save(CropSubcultureModel model) {

        CropSubculture cropSubculture;

        if (model.getId() == null) {
            cropSubculture = new CropSubculture();
        }
        else {
            cropSubculture = getRepository().findOne(model.getId());
        }

        cropSubculture.setNameRo(model.getNameRo());
        cropSubculture.setNameRu(model.getNameRu());
        cropSubculture.setDescriptionRo(model.getDescriptionRo());
        cropSubculture.setDescriptionRu(model.getDescriptionRu());
        cropSubculture.setCrop(cropService.findOne(model.getCropId()));

        return save(cropSubculture);
    }

    @Override
    public CropSubcultureRepository getRepository() {
        return subCropRepository;
    }
}
