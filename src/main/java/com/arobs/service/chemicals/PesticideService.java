package com.arobs.service.chemicals;

import com.arobs.entity.Pesticide;
import com.arobs.model.chemicals.PesticideModel;
import com.arobs.repository.PesticideRepository;
import com.arobs.service.BaseEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PesticideService extends BaseEntityService<Pesticide, PesticideRepository> {

    @Autowired
    private PesticideRepository pesticideRepository;

    @Override
    public PesticideRepository getRepository() {
        return pesticideRepository;
    }

    public List<Pesticide> find() {
        return getRepository().find();
    }

    // todo: refactor
    @Deprecated
    public PesticideModel findOneModel(Long id) {
        return new PesticideModel(getRepository().findOne(id));
    }

    @Transactional
    public Pesticide save(PesticideModel model) {

        Pesticide pesticide;

        if (model.getId() == null) {
            pesticide = new Pesticide();
        } else {
            pesticide = findOne(model.getId());
        }

        pesticide.setPesticideType(model.getPesticideType());
        pesticide.setNameRo(model.getNameRo());
        pesticide.setNameRu(model.getNameRu());
        pesticide.setDescriptionRo(model.getDescriptionRo());
        pesticide.setDescriptionRu(model.getDescriptionRu());
        pesticide.setPestsRo(model.getPestsRo());
        pesticide.setPestsRu(model.getPestsRu());
        pesticide.setActiveSubstance(model.getActiveSubstance());
        pesticide.setToxicityGroup(model.getToxicityGroup());

        return save(pesticide);
    }
}
