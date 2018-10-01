package com.arobs.service.expense;

import com.arobs.entity.Expense;
import com.arobs.entity.ExpenseItem;
import com.arobs.interfaces.HasRepository;
import com.arobs.model.expense.ExpenseItemModel;
import com.arobs.model.expense.MachineryExpenseModel;
import com.arobs.repository.ExpenseItemRepository;
import com.arobs.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExpenseItemService implements HasRepository<ExpenseItemRepository> {

    @Autowired
    private ExpenseItemRepository expenseItemRepository;
    @Autowired
    private ExpenseCategoryService expenseCategoryService;

    @Override
    public ExpenseItemRepository getRepository() {
        return expenseItemRepository;
    }

    public ExpenseItem findOne(Long id) {
        return getRepository().findOne(id);
    }

    public List<ExpenseItem> find(Long expenseId) {
        return getRepository().find(expenseId);
    }

    @Transactional
    public void save(List<ExpenseItemModel> models, Expense expense) {

        List<ExpenseItem> items = new ArrayList<>();
        List<Long> removableItems = new ArrayList<>();

        for (ExpenseItemModel model : models) {
            ExpenseItem expenseItem;
            if (model.getId() == null) {
                expenseItem = new ExpenseItem();
                expenseItem.setExpseneId(expense.getId());
            }
            else {
                expenseItem = findOne(model.getId());
            }

            if (model.isDeleted()) {
                removableItems.add(model.getId());
                continue;
            }

            if (model.getCategoryId() != null) {
                expenseItem.setCategory(expenseCategoryService.findOne(model.getCategoryId()));
            }

            expenseItem.setTitle(model.getTitle());
            expenseItem.setTotalCost(model.getTotalCost());
            expenseItem.setActualQuantity(model.getQuantity());
            items.add(expenseItem);
        }

        if (!removableItems.isEmpty()) {
            remove(removableItems);
        }

        save(items);
    }

    @Transactional
    public void remove(List<Long> ids) {
        getRepository().remove(ids);
    }

    @Transactional
    public void save(List<ExpenseItem> items) {
        getRepository().save(items);
    }
}
