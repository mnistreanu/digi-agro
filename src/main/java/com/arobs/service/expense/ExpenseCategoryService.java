package com.arobs.service.expense;

import com.arobs.entity.ExpenseCategory;
import com.arobs.interfaces.HasRepository;
import com.arobs.model.ListItemModel;
import com.arobs.repository.ExpenseCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
