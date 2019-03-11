package com.arobs.service.expense;

import com.arobs.entity.CropSeason;
import com.arobs.entity.Expense;
import com.arobs.interfaces.HasRepository;
import com.arobs.model.crop.CropSeasonModel;
import com.arobs.model.expense.ExpenseModel;
import com.arobs.model.expense.ExpenseSeasonGroupModel;
import com.arobs.model.expense.ExpenseSeasonTreeModel;
import com.arobs.repository.ExpenseRepository;
import com.arobs.service.crop.CropSeasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ExpenseService implements HasRepository<ExpenseRepository> {

    @Autowired
    private ExpenseRepository expenseRepository;
    @Autowired
    private CropSeasonService cropSeasonService;
    @Autowired
    private ExpenseCategoryService expenseCategoryService;

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

    public List<ExpenseSeasonGroupModel> getExpenseSeasonGroupModels(Long tenantId) {
        return getRepository().getExpenseSeasonGroupModels(tenantId);
    }

    public List<ExpenseSeasonTreeModel> getExpenseSeasonTreeModels(Long tenantId) {
        List<ExpenseSeasonGroupModel> groupModels = getExpenseSeasonGroupModels(tenantId);

        List<CropSeason> cropSeasons = cropSeasonService.find(tenantId);
        Map<Long, CropSeasonModel> cropSeasonModelMap = cropSeasons.stream()
                .map(CropSeasonModel::new).collect(Collectors.toMap(CropSeasonModel::getId, m -> m));

        List<ExpenseSeasonTreeModel> models = new ArrayList<>();
        Map<String, ExpenseSeasonTreeModel> modelMap = new HashMap<>();

        for (ExpenseSeasonGroupModel groupModel : groupModels) {
            String rootIdentifier = "R-" + groupModel.getCropSeasonId();
            ExpenseSeasonTreeModel rootModel = modelMap.get(rootIdentifier);
            if (rootModel == null) {
                rootModel = new ExpenseSeasonTreeModel();
                modelMap.put(rootIdentifier, rootModel);
                models.add(rootModel);

                rootModel.setCropSeasonModel(cropSeasonModelMap.get(groupModel.getCropSeasonId()));
                rootModel.setChildren(new ArrayList<>());
            }

            String categoryIdentifier = rootIdentifier + "-C-" + groupModel.getCategoryId();
            ExpenseSeasonTreeModel categoryModel = modelMap.get(categoryIdentifier);
            if (categoryModel == null) {
                categoryModel = new ExpenseSeasonTreeModel();
                modelMap.put(categoryIdentifier, categoryModel);

                categoryModel.setCategoryName(groupModel.getCategoryName());
                categoryModel.setValues(new HashMap<>());
                rootModel.getChildren().add(categoryModel);
            }
            categoryModel.getValues().put(groupModel.getPeriodIndex(), groupModel.getCost());
        }

        // recalculate values (root + formulas)
        for (ExpenseSeasonTreeModel model : models) {
            for (ExpenseSeasonTreeModel child : model.getChildren()) {
                child.calcTotalCost();
                model.append(child);
            }
            model.calcTotalCost();
        }

        return models;
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

        expense.setCategory(expenseCategoryService.findOne(model.getCategoryId()));

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
