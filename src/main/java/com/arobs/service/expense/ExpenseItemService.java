package com.arobs.service.expense;

import com.arobs.entity.ExpenseItem;
import com.arobs.interfaces.HasRepository;
import com.arobs.repository.ExpenseItemRepository;
import com.arobs.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseItemService implements HasRepository<ExpenseItemRepository> {

    @Autowired
    private ExpenseItemRepository expenseItemRepository;

    @Override
    public ExpenseItemRepository getRepository() {
        return expenseItemRepository;
    }


    public List<ExpenseItem> find(Long expenseId) {
        return getRepository().find(expenseId);
    }
}
