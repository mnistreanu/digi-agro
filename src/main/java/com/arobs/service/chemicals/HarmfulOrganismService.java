package com.arobs.service.chemicals;

import com.arobs.entity.HarmfulOrganism;
import com.arobs.interfaces.HasRepository;
import com.arobs.repository.HarmfulOrganismRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HarmfulOrganismService implements HasRepository<HarmfulOrganismRepository> {

    @Autowired
    private HarmfulOrganismRepository harmfulOrganismRepository;

    public List<HarmfulOrganism> find() {
        return getRepository().find();
    }

    @Override
    public HarmfulOrganismRepository getRepository() {
        return harmfulOrganismRepository;
    }
}
