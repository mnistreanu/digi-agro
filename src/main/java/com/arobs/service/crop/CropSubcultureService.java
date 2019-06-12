package com.arobs.service.crop;

import com.arobs.entity.crop.CropSubculture;
import com.arobs.model.crop.CropSubcultureModel;
import com.arobs.repository.CropSubcultureRepository;
import com.arobs.service.BaseEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CropSubcultureService extends BaseEntityService<CropSubculture, CropSubcultureRepository> {

    @Autowired
    private CropSubcultureRepository subCropRepository;
    @Autowired
    private CropService cropService;

    @Override
    public CropSubcultureRepository getRepository() {
        return subCropRepository;
    }

    public List<CropSubculture> find() {
        return getRepository().find();
    }

    public List<CropSubculture> find(Long cropId) {
        return getRepository().find(cropId);
    }

    @Transactional
    public CropSubculture save(CropSubcultureModel model) {

        CropSubculture cropSubculture;

        if (model.getId() == null) {
            cropSubculture = new CropSubculture();
        } else {
            cropSubculture = getRepository().findOne(model.getId());
        }

        cropSubculture.setNameRo(model.getNameRo());
        cropSubculture.setNameRu(model.getNameRu());
        cropSubculture.setDescriptionRo(model.getDescriptionRo());
        cropSubculture.setDescriptionRu(model.getDescriptionRu());
        cropSubculture.setCrop(cropService.findOne(model.getCropId()));

        return save(cropSubculture);
    }
}
