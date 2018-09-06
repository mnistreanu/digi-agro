package com.arobs.service;

import com.arobs.entity.Employee;
import com.arobs.interfaces.HasRepository;
import com.arobs.model.EmployeeModel;
import com.arobs.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

@Service
public class EmployeeService implements HasRepository<EmployeeRepository> {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private TenantService tenantService;

    @Override
    public EmployeeRepository getRepository() {
        return employeeRepository;
    }

    public Employee findOne(Long id) {
        return getRepository().findOne(id);
    }

    public List<Employee> find(Long tenantId) {
        return getRepository().find(tenantId);
    }

    public List<Employee> findAll(List<Long> ids) {
        return getRepository().findAll(ids);
    }

    @Transactional
    public Employee save(EmployeeModel model, Long tenantId) {
        Employee entity;

        if (model.getId() == null) {
            entity = new Employee();
            entity.setTenant(tenantService.findOne(tenantId));
        }
        else {
            entity = findOne(model.getId());
        }

        copyValues(entity, model);
        return getRepository().save(entity);
    }

    private void copyValues(Employee entity, EmployeeModel model) {
        entity.setTitle(model.getTitle());
        entity.setFirstName(model.getFirstName());
        entity.setLastName(model.getLastName());
    }

    @Transactional
    public void remove(Long id) {
        getRepository().remove(id);
    }
}
