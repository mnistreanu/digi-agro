package com.arobs.service;

import com.arobs.entity.WorkType;
import com.arobs.enums.WorkName;
import com.arobs.interfaces.HasRepository;
import com.arobs.repository.WorkTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
@Deprecated
public class WorkTypeService implements HasRepository<WorkTypeRepository> {

    @Autowired
    private WorkTypeRepository workTypeRepository;

    public WorkType findOne(Long id) {
        return getRepository().findOne(id);
    }

    public Collection<WorkType> findByNames(List<WorkName> workNames) {
        if (workNames == null || workNames.isEmpty()) {
            return Collections.emptyList();
        }
        return getRepository().findByNames(workNames);
    }

    @Override
    public WorkTypeRepository getRepository() {
        return workTypeRepository;
    }
}
