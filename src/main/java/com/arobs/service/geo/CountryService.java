package com.arobs.service.geo;

import com.arobs.entity.Country;
import com.arobs.interfaces.HasRepository;
import com.arobs.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService implements HasRepository<CountryRepository> {

    @Autowired
    private CountryRepository countryRepository;

    @Override
    public CountryRepository getRepository() {
        return countryRepository;
    }

    public List<Country> findAll() {
        return getRepository().findAll();
    }

}
