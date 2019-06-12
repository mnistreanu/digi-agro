package com.arobs.service.geo;

import com.arobs.entity.Country;
import com.arobs.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    public CountryRepository getRepository() {
        return countryRepository;
    }

    public List<Country> findAll() {
        return getRepository().findAll();
    }

}
