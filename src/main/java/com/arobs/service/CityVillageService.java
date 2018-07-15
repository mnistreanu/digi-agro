package com.arobs.service;

import com.arobs.entity.CityVillage;
import com.arobs.interfaces.HasRepository;
import com.arobs.repository.CityVillageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityVillageService implements HasRepository<CityVillageRepository> {

    @Autowired
    private CityVillageRepository CityVillageRepository;

    private List<CityVillage> findByCountryIdAndCountyId(String countryId, String countyId) {
        return getRepository().findByCountryIdAndCountyId(countryId, countyId);
    }

    private List<CityVillage> findByNameAndCountryIdAndCountyId(String countryId, String countyId, String name) {
        return getRepository().findByNameAndCountryIdAndCountyId(countryId, countyId, '%' + name + '%');
    }

    @Override
    public CityVillageRepository getRepository() {
        return CityVillageRepository;
    }
}
