package com.arobs.service;

import com.arobs.entity.Brand;
import com.arobs.model.BrandModel;
import com.arobs.repository.BrandRepository;
import com.arobs.repository.custom.CommonCustomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class BrandService extends BaseEntityService<Brand, BrandRepository> {

    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private CommonCustomRepository commonCustomRepository;

    @Override
    public BrandRepository getRepository() {
        return brandRepository;
    }

    public boolean isUnique(Long currentId, String field, String value) {
        return commonCustomRepository.isUnique("Brand", currentId, field, value);
    }

    @Transactional(rollbackOn = Exception.class)
    public Brand save(BrandModel model) {
        Brand entity;

        if (model.getId() == null) {
            entity = new Brand();
        } else {
            entity = findOne(model.getId());
        }

        copyValues(entity, model);
        return save(entity);
    }

    private void copyValues(Brand entity, BrandModel model) {
        entity.setName(model.getName());
    }

    @Transactional
    public Brand register(String brandName) {

        Brand brand = find(brandName);

        if (brand == null) {
            brand = new Brand();
            brand.setName(brandName);
            brand = save(brand);
        }

        return brand;
    }

    private Brand find(String name) {
        return getRepository().find(name);
    }
}
