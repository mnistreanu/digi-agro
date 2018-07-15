package com.arobs.service;

import com.arobs.entity.Brand;
import com.arobs.entity.County;
import com.arobs.interfaces.HasRepository;
import com.arobs.model.BrandModel;
import com.arobs.repository.BrandRepository;
import com.arobs.repository.CountyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CountyService implements HasRepository<CountyRepository> {

    @Autowired
    private CountyRepository countyRepository;

    private List<County> findByCountyId(String countyId) {
        return getRepository().findByCountryId(countyId);
    }

    private List<County> findByNameAndCountyId(String countyId, String name) {
        return getRepository().findByNameAndCountryId(countyId, '%' + name + '%');
    }

    @Override
    public CountyRepository getRepository() {
        return countyRepository;
    }
}
