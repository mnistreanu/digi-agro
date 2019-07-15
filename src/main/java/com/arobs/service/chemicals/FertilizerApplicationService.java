package com.arobs.service.chemicals;

import com.arobs.entity.FertilizerApplication;
import com.arobs.model.chemicals.FertilizerApplicationModel;
import com.arobs.repository.FertilizerApplicationRepository;
import com.arobs.service.BaseEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class FertilizerApplicationService extends BaseEntityService<FertilizerApplication, FertilizerApplicationRepository> {

    @Autowired
    private FertilizerApplicationRepository fertilizerAppRepository;

    @Override
    public FertilizerApplicationRepository getRepository() {
        return fertilizerAppRepository;
    }

    public List<FertilizerApplication> find(Long parcelId) {
        return getRepository().find(parcelId);
    }

    @Transactional
    public FertilizerApplication save(FertilizerApplicationModel model) {
        FertilizerApplication entity;

        if (model.getId() == null) {
            entity = new FertilizerApplication();
        } else {
            entity = this.findOne(model.getId());
        }

        this.copyValues(entity, model);
        return getRepository().save(entity);
    }

    private void copyValues(FertilizerApplication entity, FertilizerApplicationModel model) {
//        entity.setFertilizerType(model.getFertilizerType());
//        entity.setNameRo(model.getNameRo());
//        entity.setNameRu(model.getNameRu());
//        entity.setDescriptionRo(model.getDescriptionRo());
//        entity.setDescriptionRu(model.getDescriptionRu());
    }

//    @Override
//    @Transactional
//    public void delete(Long id) {
//        getRepository().softDelete(id);
//    }
}
