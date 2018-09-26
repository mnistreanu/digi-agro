package com.arobs.service.expense;

import com.arobs.entity.ExpenseCategory;
import com.arobs.interfaces.HasRepository;
import com.arobs.model.expense.ExpenseCategoryModel;
import com.arobs.repository.ExpenseCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ExpenseCategoryService implements HasRepository<ExpenseCategoryRepository> {

    @Autowired
    private ExpenseCategoryRepository expenseCategoryRepository;

    public ExpenseCategory findOne(Long id) {
        return getRepository().findOne(id);
    }

    public List<ExpenseCategory> findDefault() {
           return getRepository().findDefault();
    }

    public List<ExpenseCategory> find(Long tenantId) {
        return getRepository().find(tenantId);
    }

    //    public List<ListItemModel> fetchItems() {
//        return getRepository().fetchItems();
//    }

    @Override
    public ExpenseCategoryRepository getRepository() {
        return expenseCategoryRepository;
    }

    public ExpenseCategory findOne(Long id) {
        return getRepository().findOne(id);
    }

    public ExpenseCategoryModel findOneModel(Long id) {
        ExpenseCategoryModel model = new ExpenseCategoryModel(getRepository().findOne(id));
        return model;
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

        return save(category);
    }

    @Transactional
    public ExpenseCategory save(ExpenseCategory category) {
        return getRepository().save(category);
    }

//    @Transactional
//    public void remove(Long id) {
//        getRepository().remove(id);
//    }


}
