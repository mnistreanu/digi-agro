package com.arobs.service.expense;

import com.arobs.entity.Expense;
import com.arobs.entity.ExpenseCategory;
import com.arobs.entity.ExpenseItem;
import com.arobs.entity.ExpenseResource;
import com.arobs.interfaces.HasRepository;
import com.arobs.model.expense.ExpenseItemModel;
import com.arobs.model.expense.ExpenseModel;
import com.arobs.model.expense.FuelExpenseModel;
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
    @Autowired
    private ExpenseItemService expenseItemService;
    @Autowired
    private ExpenseResourceService expenseResourceService;
    @Autowired
    private ExpenseCategoryService expenseCategoryService;

    @Override
    public ExpenseRepository getRepository() {
        return expenseRepository;
    }

    public List<Expense> find(Long tenantId) {
        return getRepository().find(tenantId);
    }

    public List<Expense> find(Long tenantId, Long categoryId) {
        return getRepository().find(tenantId, categoryId);
    }

    public List<Expense> find(Long tenantId, Long categoryId, Date dateFrom, Date dateTo) {
        return getRepository().find(tenantId, categoryId, dateFrom, dateTo);
    }

    public Expense findOne(Long id) {
        return getRepository().findOne(id);
    }


    public ExpenseModel getModel(Expense expense) {
        ExpenseModel expenseModel = new ExpenseModel(expense);
        List<ExpenseItem> expenseItems = expenseItemService.find(expense.getId());
        for (ExpenseItem expenseItem : expenseItems) {
            expenseModel.getExpenseItems().add(new ExpenseItemModel(expenseItem));
        }

        List<ExpenseResource> expenseResources = expenseResourceService.find(expense.getId());
        for (ExpenseResource expenseResource : expenseResources) {
            if (expenseResource.getTableName().equals("machine")) {
                expenseModel.getMachines().add(expenseResource.getResourceId());
            }
            else if (expenseResource.getTableName().equals("employee")) {
                expenseModel.getEmployees().add(expenseResource.getResourceId());
            }
        }

        return expenseModel;
    }


    @Transactional
    public Expense save(ExpenseModel model, Long tenant, Long defaultCategoryId) {

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

        ExpenseCategory expenseCategory = expenseCategoryService.findDefault(defaultCategoryId);
        expense.setCategoryId(expenseCategory.getId());

        expense.setTitle(model.getTitle());
        expense.setExpenseDate(model.getExpenseDate());

        return save(expense);
    }


    public ExpenseModel findOneModel(Long expenseId) {
        Expense expense = this.findOne(expenseId);
        return this.getModel(expense);
    }

    @Transactional
    public ExpenseModel saveModel(ExpenseModel model, Long tenant, Long defaultCategoryId) {

        Expense expense = save(model, tenant, defaultCategoryId);
        expenseItemService.save(model.getExpenseItems(), expense);
        expenseResourceService.save(expense.getId(), model.getMachines(), "machine");
        expenseResourceService.save(expense.getId(), model.getEmployees(), "employee");

        return getModel(expense);
    }

//    @Transactional
//    public void remove(Long expenseId) {
//        expenseService.remove(expenseId);
//    }
//
    @Transactional
    public Expense save(Expense expense) {
        return getRepository().save(expense);
    }

    @Transactional
    public void remove(Long id) {
        getRepository().remove(id, new Date());
    }
}
