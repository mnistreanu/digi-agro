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
    private CityVillageRepository cityVillageRepository;

    /**
     * Cauta lista de sate si orase dupa tara si raion/judet
     * @param countryId
     * @param countyId
     * @return
     */
    private List<CityVillage> find(String countryId, String countyId) {
        return getRepository().find(countryId, countyId);
    }

    /**
     * Cauta lista de sate si orase dupa tara, raion/judet si nume
     * @param countryId
     * @param countyId
     * @param name
     * @return
     */
    private List<CityVillage> find(String countryId, String countyId, String name) {
        return getRepository().find(countryId, countyId, '%' + name + '%');
    }

    @Override
    public CityVillageRepository getRepository() {
        return cityVillageRepository;
    }
}
