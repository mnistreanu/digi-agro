package com.arobs.service.geo;

import com.arobs.entity.County;
import com.arobs.repository.CountyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountyService {

    @Autowired
    private CountyRepository countyRepository;

    public CountyRepository getRepository() {
        return countyRepository;
    }

    public List<County> find(String countryId) {
        return getRepository().find(countryId);
    }

    public List<County> find(String countryId, String name) {
        return getRepository().find(countryId, '%' + name + '%');
    }
}
