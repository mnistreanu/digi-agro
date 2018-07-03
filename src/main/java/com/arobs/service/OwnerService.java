package com.arobs.service;

import com.arobs.entity.Owner;
import com.arobs.interfaces.HasRepository;
import com.arobs.model.OwnerModel;
import com.arobs.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class OwnerService implements HasRepository<OwnerRepository> {

    @Autowired
    private OwnerRepository ownerRepository;
    
    public boolean checkNameUnique(Long id, String name) {
        if (id == -1) {
            return getRepository().countByName(name) == 0;
        }
        return getRepository().countByNameEscapeId(id, name) == 0;
    }

    public Owner findOne(Long id) {
        return getRepository().findOne(id);
    }

    public OwnerModel findModelById(Long id) {
        return new OwnerModel(getRepository().findOne(id));
    }

    public List<Owner> findAll() {
        return getRepository().findAll();
    }

    @Transactional
    public void remove(Long id) {
        getRepository().remove(id);
    }

    @Transactional(rollbackOn = Exception.class)
    public Owner save(OwnerModel model) {
        Owner entity;

        if (model.getId() == null) {
            entity = new Owner();
        }
        else {
            entity = findOne(model.getId());
        }

        copyValues(entity, model);
        return getRepository().save(entity);
    }

    private void copyValues(Owner entity, OwnerModel model) {
        entity.setName(model.getName());
    }

    @Transactional
    public Owner save(Owner item) {
        return getRepository().save(item);
    }

    @Transactional
    public Owner register(String ownerName) {

        Owner owner = findByName(ownerName);

        if (owner == null) {
            owner = new Owner();
            owner.setName(ownerName);
            owner = save(owner);
        }

        return owner;
    }

    private Owner findByName(String name) {
        return ownerRepository.findByName(name);
    }

    @Override
    public OwnerRepository getRepository() {
        return ownerRepository;
    }
}
