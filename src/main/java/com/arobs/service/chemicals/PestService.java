package com.arobs.service.chemicals;

import com.arobs.entity.Pest;
import com.arobs.repository.PestRepository;
import com.arobs.service.BaseEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PestService extends BaseEntityService<Pest, PestRepository> {

    @Autowired
    private PestRepository pestRepository;

    @Override
    public PestRepository getRepository() {
        return pestRepository;
    }

    public List<Pest> find() {
        return getRepository().find();
    }
}
