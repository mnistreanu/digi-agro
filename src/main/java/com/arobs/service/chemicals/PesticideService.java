package com.arobs.service.chemicals;

import com.arobs.entity.Expense;
import com.arobs.entity.Pesticide;
import com.arobs.interfaces.HasRepository;
import com.arobs.model.chemicals.PesticideModel;
import com.arobs.model.expense.ExpenseModel;
import com.arobs.repository.PesticideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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

    public Pesticide findOne(Long id) {
        return getRepository().findOne(id);
    }

    public PesticideModel findOneModel(Long id) {
        PesticideModel model = new PesticideModel(getRepository().findOne(id));
        return model;
    }

    @Transactional
    public Pesticide save(PesticideModel model) {

        Pesticide pesticide;

        if (model.getId() == null) {
            pesticide = new Pesticide();
        }
        else {
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

    @Transactional
    public Pesticide save(Pesticide pesticide) {
        return getRepository().save(pesticide);
    }

//    @Transactional
//    public void remove(Long id) {
//        getRepository().remove(id);
//    }

}
