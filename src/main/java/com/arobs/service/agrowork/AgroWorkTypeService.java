package com.arobs.service.agrowork;

import com.arobs.entity.AgroWorkType;
import com.arobs.repository.AgroWorkTypeRepository;
import com.arobs.service.BaseEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgroWorkTypeService extends BaseEntityService<AgroWorkType, AgroWorkTypeRepository> {

    @Autowired
    private AgroWorkTypeRepository agroWorkTypeRepository;

    @Override
    public AgroWorkTypeRepository getRepository() {
        return agroWorkTypeRepository;
    }

    public List<AgroWorkType> find() {
        return getRepository().find();
    }

    public List<AgroWorkType> find(Long tenantId) {
        return getRepository().find(tenantId);
    }
}
