package com.arobs.service.expense;

import com.arobs.entity.ExpenseNorm;
import com.arobs.interfaces.HasRepository;
import com.arobs.repository.ExpenseNormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ExpenseNormService implements HasRepository<ExpenseNormRepository> {

    @Autowired
    private ExpenseNormRepository expenseNormRepository;

    @Override
    public ExpenseNormRepository getRepository() {
        return expenseNormRepository;
    }

    public ExpenseNorm findOne(Long id) {
        return getRepository().findOne(id);
    }

    public ExpenseNorm find(Long expenseId) {
        return getRepository().find(expenseId);
    }

    @Transactional
    public ExpenseNorm save(ExpenseNorm item) {
        return getRepository().save(item);
    }
}
