package com.arobs.service.expense;

import com.arobs.entity.ExpenseResource;
import com.arobs.interfaces.HasRepository;
import com.arobs.repository.ExpenseResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
