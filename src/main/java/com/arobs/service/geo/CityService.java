package com.arobs.service.geo;

import com.arobs.entity.City;
import com.arobs.repository.CityRepository;
import com.arobs.service.BaseEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService extends BaseEntityService<City, CityRepository> {

    @Autowired
    private CityRepository cityRepository;

    @Override
    public CityRepository getRepository() {
        return cityRepository;
    }

    /**
     * Cauta lista de sate si orase dupa tara si raion/judet
     *
     * @param countryId
     * @param countyId
     * @return
     */
    public List<City> find(String countryId, String countyId) {
        return getRepository().find(countryId, countyId);
    }

    /**
     * Cauta lista de sate si orase dupa tara, raion/judet si nume
     *
     * @param countryId
     * @param countyId
     * @param name
     * @return
     */
    public List<City> find(String countryId, String countyId, String name) {
        return getRepository().find(countryId, countyId, '%' + name + '%');
    }

}
