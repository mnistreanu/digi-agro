package com.arobs.service.expense;

import com.arobs.entity.CropSeason;
import com.arobs.entity.expense.Expense;
import com.arobs.entity.expense.ExpenseCategory;
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

    private List<ExpenseSeasonGroupModel> getExpenseSeasonGroupModels(Long tenantId) {
        return getRepository().getExpenseSeasonGroupModels(tenantId);
    }

    public List<ExpenseSeasonTreeModel> getExpenseSeasonTreeModels(Long tenantId) {
        List<ExpenseSeasonGroupModel> groupModels = getExpenseSeasonGroupModels(tenantId);

        List<CropSeason> cropSeasons = cropSeasonService.find(tenantId);
        Map<Long, CropSeasonModel> cropSeasonModelMap = cropSeasons.stream()
                .map(CropSeasonModel::new).collect(Collectors.toMap(CropSeasonModel::getId, m -> m));

        List<ExpenseCategory> expenseCategories = expenseCategoryService.find(tenantId);
        Map<Long, ExpenseCategory> expenseCategoryMap = expenseCategories.stream()
                .collect(Collectors.toMap(ExpenseCategory::getId, c -> c));

        List<ExpenseSeasonTreeModel> models = new ArrayList<>();
        Map<String, ExpenseSeasonTreeModel> modelMap = new HashMap<>();

        for (ExpenseSeasonGroupModel groupModel : groupModels) {
            String seasonIdentifier = "R-" + groupModel.getCropSeasonId();
            ExpenseSeasonTreeModel rootModel = buildExpenseSeasonRoot(seasonIdentifier, groupModel, models, modelMap, cropSeasonModelMap);

            ExpenseCategory ec = expenseCategoryMap.get(groupModel.getCategoryId());
            ExpenseSeasonTreeModel parentModel;

            if (ec.getParentId() != null) {
                parentModel = buildExpenseSeasonTreeModel(seasonIdentifier, null, rootModel, modelMap, ec.getParentId(), expenseCategoryMap);
            }
            else {
                parentModel = rootModel;
            }

            buildExpenseSeasonTreeModel(seasonIdentifier, groupModel, parentModel, modelMap, ec.getId(), expenseCategoryMap);
        }

        for (ExpenseSeasonTreeModel model : models) {
            adjustExpenseSeasonTreeModel(model);
        }

        return models;
    }

    private void adjustExpenseSeasonTreeModel(ExpenseSeasonTreeModel model) {
        if (model.getChildren() != null) {
            for (ExpenseSeasonTreeModel childModel : model.getChildren()) {
                adjustExpenseSeasonTreeModel(childModel);
                model.append(childModel);
            }
        }

        model.calcTotalCost();
    }

    private ExpenseSeasonTreeModel buildExpenseSeasonRoot(String identifier,
                                                          ExpenseSeasonGroupModel groupModel,
                                                          List<ExpenseSeasonTreeModel> models,
                                                          Map<String, ExpenseSeasonTreeModel> modelMap,
                                                          Map<Long, CropSeasonModel> cropSeasonModelMap) {
        ExpenseSeasonTreeModel model = modelMap.get(identifier);
        if (model == null) {
            model = new ExpenseSeasonTreeModel();
            modelMap.put(identifier, model);
            models.add(model);

            model.setCropSeasonModel(cropSeasonModelMap.get(groupModel.getCropSeasonId()));
        }
        return model;
    }

    private ExpenseSeasonTreeModel buildExpenseSeasonTreeModel(String seasonIdentifier,
                                                               ExpenseSeasonGroupModel groupModel,
                                                               ExpenseSeasonTreeModel parentModel,
                                                               Map<String, ExpenseSeasonTreeModel> modelMap,
                                                               Long expenseCategoryId,
                                                               Map<Long, ExpenseCategory> expenseCategoryMap) {

        String identifier = seasonIdentifier + "-" + expenseCategoryId;
        ExpenseSeasonTreeModel model = modelMap.get(identifier);

        if (model == null) {
            model = new ExpenseSeasonTreeModel();
            modelMap.put(identifier, model);

            ExpenseCategory ec = expenseCategoryMap.get(expenseCategoryId);

            model.setCategoryName(ec.getName());
            model.setTitle(model.getCategoryName());

            model.setValues(new HashMap<>());

            if (parentModel.getChildren() == null) {
                parentModel.setChildren(new ArrayList<>());
            }
            parentModel.getChildren().add(model);
        }

        if (groupModel != null) {
            model.getValues().put(groupModel.getPeriodIndex(), groupModel.getCost());
        }

        return model;
    }


    @Transactional
    public Expense save(ExpenseModel model, Long tenantId) {

        Expense expense;

        if (model.getId() == null) {
            expense = new Expense();
            expense.setTenant(tenantId);
            expense.setCropSeason(cropSeasonService.getOne(model.getCropSeasonId()));
        }
        else {
            expense = findOne(model.getId());
        }

        expense.setRootCategory(expenseCategoryService.getOne(model.getRootCategoryId()));
        if (model.getSubCategoryId() != null) {
            expense.setSubCategory(expenseCategoryService.getOne(model.getSubCategoryId()));
        }

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
        getRepository().delete(id);
    }


}
