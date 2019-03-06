package com.arobs.service.expense;

import com.arobs.entity.Expense;
import com.arobs.interfaces.HasRepository;
import com.arobs.model.expense.ExpenseModel;
import com.arobs.repository.ExpenseRepository;
import com.arobs.service.crop.CropSeasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ExpenseService implements HasRepository<ExpenseRepository> {

    @Autowired
    private ExpenseRepository expenseRepository;
    @Autowired
    private CropSeasonService cropSeasonService;

    @Override
    public ExpenseRepository getRepository() {
        return expenseRepository;
    }

    public Expense findOne(Long id) {
        return getRepository().findOne(id);
    }

    public List<Expense> find(Long tenantId, Long cropSeasonId) {
        return getRepository().find(tenantId, cropSeasonId);
    }

    @Transactional
    public Expense save(ExpenseModel model, Long tenantId) {

        Expense expense;

        if (model.getId() == null) {
            expense = new Expense();
            expense.setTenant(tenantId);
            expense.setCropSeason(cropSeasonService.findOne(model.getCropSeasonId()));
        }
        else {
            expense = findOne(model.getId());
        }

        expense.setCategoryId(model.getCategoryId());

        expense.setDate(model.getDate());
        expense.setTitle(model.getTitle());
        expense.setDescription(model.getDescription());
        expense.setCost(model.getCost());

        return save(expense);
    }

    @Transactional
    public Expense save(Expense expense) {
        return getRepository().save(expense);
    }

    @Transactional
    public void remove(Long id) {
        getRepository().remove(id);
    }


}
