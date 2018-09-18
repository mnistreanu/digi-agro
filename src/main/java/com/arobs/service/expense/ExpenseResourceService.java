package com.arobs.service.expense;

import com.arobs.entity.ExpenseResource;
import com.arobs.interfaces.HasRepository;
import com.arobs.repository.ExpenseResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExpenseResourceService implements HasRepository<ExpenseResourceRepository> {

    @Autowired
    private ExpenseResourceRepository expenseResourceRepository;

    @Override
    public ExpenseResourceRepository getRepository() {
        return expenseResourceRepository;
    }

    public List<ExpenseResource> find(Long expenseId) {
        return getRepository().find(expenseId);
    }

    @Transactional
    public void remove(Long expenseId, String tableName) {
        getRepository().remove(expenseId, tableName);
    }

    @Transactional
    public void save(Long expenseId, List<Long> resources, String tableName) {
        remove(expenseId, tableName);
        List<ExpenseResource> items = new ArrayList<>();
        for (Long resource : resources) {
            ExpenseResource expenseResource = new ExpenseResource();
            items.add(expenseResource);
            expenseResource.setExpenseId(expenseId);
            expenseResource.setResourceId(resource);
            expenseResource.setTableName(tableName);
        }

        save(items);
    }

    @Transactional
    public void save(List<ExpenseResource> items) {
        getRepository().save(items);
    }
}
