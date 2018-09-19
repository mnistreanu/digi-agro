package com.arobs.service.expense;

import com.arobs.entity.Expense;
import com.arobs.interfaces.HasRepository;
import com.arobs.model.expense.MachineryExpenseModel;
import com.arobs.repository.ExpenseRepository;
import com.arobs.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class ExpenseService implements HasRepository<ExpenseRepository> {

    @Autowired
    private ExpenseRepository expenseRepository;
    @Autowired
    private AuthService authService;

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

    @Transactional
    public Expense save(MachineryExpenseModel model, Long tenant) {

        Expense expense;

        if (model.getId() == null) {
            expense = new Expense();
            expense.setTenantId(tenant);
            expense.setCreatedAt(new Date());
            expense.setCreatedBy(authService.getCurrentUser().getId());
        }
        else {
            expense = findOne(model.getId());
        }

        expense.setTitle(model.getTitle());
        expense.setExpenseDate(model.getExpenseDate());

        return save(expense);
    }

    @Transactional
    public Expense save(Expense expense) {
        return getRepository().save(expense);
    }

    @Transactional
    public void remove(Long id) {
        getRepository().remove(id, new Date());
    }
}
