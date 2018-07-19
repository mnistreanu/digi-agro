package com.arobs.service;

import com.arobs.entity.AgroWorkType;
import com.arobs.interfaces.HasRepository;
import com.arobs.repository.AgroWorkTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgroWorkTypeService implements HasRepository<AgroWorkTypeRepository> {

    @Autowired
    private AgroWorkTypeRepository agroWorkTypeRepository;

    private List<AgroWorkType> find() {
        return getRepository().find();
    }

    private List<AgroWorkType> find(Long tenantId) {
        return getRepository().find(tenantId);
    }

    @Override
    public AgroWorkTypeRepository getRepository() {
        return agroWorkTypeRepository;
    }
}
