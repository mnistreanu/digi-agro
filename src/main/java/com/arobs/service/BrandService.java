package com.arobs.service;

import com.arobs.entity.Brand;
import com.arobs.interfaces.HasRepository;
import com.arobs.model.BrandModel;
import com.arobs.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class BrandService implements HasRepository<BrandRepository> {

    @Autowired
    private BrandRepository brandRepository;

    public boolean validateName(Long id, String name) {
        if (id == -1) {
            return getRepository().countByName(name) == 0;
        }
        return getRepository().countByNameEscapeId(id, name) == 0;
    }

    public Brand findOne(Long id) {
        return getRepository().findOne(id);
    }

    public BrandModel findModelById(Long id) {
        return new BrandModel(getRepository().findOne(id));
    }

    public List<Brand> findAll() {
        return getRepository().findAll();
    }

    @Transactional
    public void remove(Long id) {
        getRepository().remove(id);
    }

    @Transactional(rollbackOn = Exception.class)
    public Brand save(BrandModel model) {
        Brand entity;

        if (model.getId() == null) {
            entity = new Brand();
        }
        else {
            entity = findOne(model.getId());
        }

        copyValues(entity, model);
        return getRepository().save(entity);
    }

    private void copyValues(Brand entity, BrandModel model) {
        entity.setName(model.getName());
    }


    @Transactional
    public Brand save(Brand item) {
        return getRepository().save(item);
    }

    @Transactional
    public Brand register(String brandName) {

        Brand brand = findByName(brandName);

        if (brand == null) {
            brand = new Brand();
            brand.setName(brandName);
            brand = save(brand);
        }

        return brand;
    }

    private Brand findByName(String name) {
        return getRepository().findByName(name);
    }

    @Override
    public BrandRepository getRepository() {
        return brandRepository;
    }
}
