package com.arobs.service;

import com.arobs.entity.Employee;
import com.arobs.model.EmployeeModel;
import com.arobs.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class EmployeeService extends BaseEntityService<Employee, EmployeeRepository> {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private TenantService tenantService;

    @Override
    public EmployeeRepository getRepository() {
        return employeeRepository;
    }

    public List<Employee> find(Long tenantId) {
        return getRepository().find(tenantId);
    }

    @Transactional
    public Employee save(EmployeeModel model, Long tenantId) {
        Employee entity;

        if (model.getId() == null) {
            entity = new Employee();
            entity.setTenant(tenantService.getOne(tenantId));
        } else {
            entity = findOne(model.getId());
        }

        copyValues(entity, model);
        return save(entity);
    }

    private void copyValues(Employee entity, EmployeeModel model) {
        entity.setTitle(model.getTitle());
        entity.setFirstName(model.getFirstName());
        entity.setLastName(model.getLastName());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        getRepository().softDelete(id);
    }
}
