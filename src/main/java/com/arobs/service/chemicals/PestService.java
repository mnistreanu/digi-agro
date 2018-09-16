package com.arobs.service.chemicals;

import com.arobs.entity.Pest;
import com.arobs.interfaces.HasRepository;
import com.arobs.repository.PestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PestService implements HasRepository<PestRepository> {

    @Autowired
    private PestRepository pestRepository;

    public List<Pest> find() {
        return getRepository().find();
    }

    @Override
    public PestRepository getRepository() {
        return pestRepository;
    }
}
