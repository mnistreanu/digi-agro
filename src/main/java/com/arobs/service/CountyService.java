package com.arobs.service;

import com.arobs.entity.County;
import com.arobs.interfaces.HasRepository;
import com.arobs.repository.CountyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountyService implements HasRepository<CountyRepository> {

    @Autowired
    private CountyRepository countyRepository;

    private List<County> find(String countyId) {
        return getRepository().find(countyId);
    }

    private List<County> find(String countyId, String name) {
        return getRepository().find(countyId, '%' + name + '%');
    }

    @Override
    public CountyRepository getRepository() {
        return countyRepository;
    }
}
