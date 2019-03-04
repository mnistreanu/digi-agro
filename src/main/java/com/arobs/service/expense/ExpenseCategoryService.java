package com.arobs.service.expense;

import com.arobs.entity.ExpenseCategory;
import com.arobs.interfaces.HasRepository;
import com.arobs.model.expense.ExpenseCategoryModel;
import com.arobs.repository.ExpenseCategoryRepository;
import com.arobs.utils.StaticUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
public class ExpenseCategoryService implements HasRepository<ExpenseCategoryRepository> {

    @Autowired
    private ExpenseCategoryRepository expenseCategoryRepository;

    @Override
    public ExpenseCategoryRepository getRepository() {
        return expenseCategoryRepository;
    }

    public ExpenseCategory findOne(Long id) {
        return getRepository().findOne(id);
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
        }
        else {
            category = findOne(model.getId());
        }

        category.setParentId(model.getParentId());
        category.setName(model.getName());
        category.setDescription(model.getDescription());

        return save(category);
    }

    @Transactional
    public ExpenseCategory save(ExpenseCategory category) {
        return getRepository().save(category);
    }

    @Transactional
    public void remove(Long id) {
        getRepository().remove(id);
    }


}
