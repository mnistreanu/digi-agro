package com.arobs.service.expense;

import com.arobs.entity.ExpenseDetail;
import com.arobs.interfaces.HasRepository;
import com.arobs.repository.ExpenseDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ExpenseDetailService implements HasRepository<ExpenseDetailRepository> {

    @Autowired
    private ExpenseDetailRepository expenseNormRepository;

    @Override
    public ExpenseDetailRepository getRepository() {
        return expenseNormRepository;
    }

    public ExpenseDetail findOne(Long id) {
        return getRepository().findOne(id);
    }

    public ExpenseDetail find(Long expenseId) {
        return getRepository().find(expenseId);
    }

    @Transactional
    public ExpenseDetail save(ExpenseDetail item) {
        return getRepository().save(item);
    }

}
