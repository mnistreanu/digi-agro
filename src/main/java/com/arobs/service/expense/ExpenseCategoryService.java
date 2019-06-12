package com.arobs.service.expense;

import com.arobs.entity.expense.ExpenseCategory;
import com.arobs.model.expense.ExpenseCategoryModel;
import com.arobs.repository.ExpenseCategoryRepository;
import com.arobs.service.BaseEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ExpenseCategoryService extends BaseEntityService<ExpenseCategory, ExpenseCategoryRepository> {

    @Autowired
    private ExpenseCategoryRepository expenseCategoryRepository;

    @Override
    public ExpenseCategoryRepository getRepository() {
        return expenseCategoryRepository;
    }

    public List<ExpenseCategory> find(Long tenantId) {
        return getRepository().find(tenantId);
    }

    public List<ExpenseCategory> findRoots(Long tenantId) {
        return getRepository().findRoots(tenantId);
    }

    @Transactional
    public ExpenseCategory save(ExpenseCategoryModel model, Long tenantId) {

        ExpenseCategory category;

        if (model.getId() == null) {
            category = new ExpenseCategory();
            category.setTenantId(tenantId);
        } else {
            category = findOne(model.getId());
        }

        category.setParentId(model.getParentId());
        category.setName(model.getName());
        category.setDescription(model.getDescription());

        return save(category);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        getRepository().softDelete(id);
    }

    public List<String> getRootNames(Long tenantId) {
        return getRepository().getRootNames(tenantId);
    }

}
