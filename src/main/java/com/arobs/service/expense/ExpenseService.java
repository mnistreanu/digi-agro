package com.arobs.service.expense;

import com.arobs.entity.Expense;
import com.arobs.interfaces.HasRepository;
import com.arobs.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService implements HasRepository<ExpenseRepository> {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Override
    public ExpenseRepository getRepository() {
        return expenseRepository;
    }

    public List<Expense> find(Long tenantId) {
        return getRepository().find(tenantId);
    }

    public Expense findOne(Long id) {
        return getRepository().findOne(id);
    }
}
