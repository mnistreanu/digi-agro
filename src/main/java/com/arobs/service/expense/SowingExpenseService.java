package com.arobs.service.expense;

import com.arobs.entity.*;
import com.arobs.enums.ExpenseDetailType;
import com.arobs.model.CropModel;
import com.arobs.model.CropVarietyModel;
import com.arobs.model.expense.SowingExpenseModel;
import com.arobs.service.AuthService;
import com.arobs.service.UserAccountService;
import com.arobs.service.crop.CropService;
import com.arobs.service.crop.CropVarietyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SowingExpenseService {

    @Autowired
    private ExpenseService expenseService;
    @Autowired
    private ExpenseCategoryService expenseCategoryService;
    @Autowired
    private ExpenseItemService expenseItemService;
    @Autowired
    private ExpenseNormService expenseNormService;
    @Autowired
    private ExpenseDetailService expenseDetailService;
    @Autowired
    private ExpenseResourceService expenseResourceService;
    @Autowired
    private UserAccountService userAccountService;
    @Autowired
    private CropService cropService;
    @Autowired
    private CropVarietyService cropVarietyService;
    @Autowired
    private AuthService authService;


    public SowingExpenseModel getModel(Long id) {

        Expense expense = expenseService.findOne(id);
        ExpenseItem expenseItem = expenseItemService.find(expense.getId()).get(0);
        ExpenseNorm expenseNorm = expenseNormService.find(expenseItem.getId());
        ExpenseDetail expenseDetail = expenseDetailService.find(expenseItem.getId());

        List<ExpenseResource> resources = expenseResourceService.find(expense.getId());
        List<Long> parcels = new ArrayList<>();
        Crop crop = null;
        CropVariety cropVariety = null;
        for (ExpenseResource resource : resources) {
            switch (resource.getTableName()) {
                case "parcel":
                    parcels.add(resource.getResourceId());
                    break;

                case "crop":
                    crop = cropService.findOne(resource.getResourceId());
                    break;

                case "crop_variety":
                    cropVariety = cropVarietyService.findOne(resource.getResourceId());
                    break;
            }
        }

        SowingExpenseModel model = new SowingExpenseModel();
        model.setExpenseId(expense.getId());
        model.setExpenseDate(expense.getExpenseDate());
        model.setParcels(parcels);
        model.setUnitOfMeasure(expenseItem.getUnitOfMeasure());
        model.setArea(Double.valueOf(expenseDetail.getItemValue()));
        model.setNormSown1Ha(expenseNorm.getPerUnit());
        model.setActualSown1Ha(expenseItem.getActualPerUnit());
        model.setUnitPrice(expenseItem.getUnitCost());

        if (crop != null) {
           model.setCropModel(new CropModel(crop));
            model.setCropId(crop.getId());
        }

        if (cropVariety != null) {
            model.setCropVarietyModel(new CropVarietyModel(cropVariety));
            model.setCropVarietyId(cropVariety.getId());
        }

        model.setCreatedAt(expense.getCreatedAt());
        model.setCreatedBy(userAccountService.findOne(expense.getCreatedBy()).getFullName());

        return model;
    }

    public List<SowingExpenseModel> get(Long tenantId) {

        List<Expense> expenses = expenseService.find(tenantId, ExpenseCategory.SOWING);
        List<SowingExpenseModel> models = new ArrayList<>();
        for (Expense expense : expenses) {
            models.add(getModel(expense.getId()));
        }

        return models;
    }


    @Transactional
    public SowingExpenseModel save(SowingExpenseModel model, Long tenant) {

        Expense expense = saveExpense(model, tenant);
        ExpenseItem item = saveExpenseItem(model, expense);
        saveExpenseNorm(model, item);
        saveExpenseDetail(model, item);
        saveResources(model, expense);

        return getModel(expense.getId());
    }

    private Expense saveExpense(SowingExpenseModel model, Long tenant) {
        Expense expense;
        if (model.getExpenseId() == null) {
            expense = new Expense();
            expense.setTenantId(tenant);
            expense.setCreatedAt(new Date());
            expense.setCreatedBy(authService.getCurrentUser().getId());
        }
        else {
            expense = expenseService.findOne(model.getExpenseId());
        }

        ExpenseCategory expenseCategory = expenseCategoryService.findDefault(ExpenseCategory.SOWING);
        expense.setCategoryId(expenseCategory.getId());

        expense.setExpenseDate(model.getExpenseDate());

        return expenseService.save(expense);
    }

    private ExpenseItem saveExpenseItem(SowingExpenseModel model, Expense expense) {
        List<ExpenseItem> items = expenseItemService.find(expense.getId());
        ExpenseItem expenseItem;
        if (items.isEmpty()) {
            expenseItem = new ExpenseItem();
            expenseItem.setExpenseId(expense.getId());
        }
        else {
            expenseItem = items.get(0);
        }


        expenseItem.setUnitOfMeasure(model.getUnitOfMeasure());
        expenseItem.setActualPerUnit(model.getActualSown1Ha());
        expenseItem.setActualQuantity(model.getActualSownTotal());
        expenseItem.setUnitCost(model.getUnitPrice());
        expenseItem.setTotalCost(model.getTotalAmount());

        return expenseItemService.save(expenseItem);
    }

    private ExpenseNorm saveExpenseNorm(SowingExpenseModel model, ExpenseItem item) {
        ExpenseNorm norm = expenseNormService.find(item.getId());

        if (norm == null) {
            norm = new ExpenseNorm();
            norm.setExpenseItemId(item.getId());
        }

        norm.setPerUnit(model.getNormSown1Ha());
        norm.setTotal(model.getNormSownTotal());

        return expenseNormService.save(norm);
    }

    private ExpenseDetail saveExpenseDetail(SowingExpenseModel model, ExpenseItem item) {
        ExpenseDetail detail = expenseDetailService.find(item.getId());

        if (detail == null) {
            detail = new ExpenseDetail();
            detail.setExpenseItemId(item.getId());
        }

        detail.setItemKey(ExpenseDetailType.AREA);
        detail.setItemValue(model.getArea().toString());

        return expenseDetailService.save(detail);
    }

    private void saveResources(SowingExpenseModel model, Expense expense) {
        List<Long> crops = new ArrayList<>();
        crops.add(model.getCropId());
        List<Long> cropVarieties = new ArrayList<>();
        if (model.getCropVarietyId() != null) {
            cropVarieties.add(model.getCropVarietyId());
        }
        expenseResourceService.save(expense.getId(), model.getParcels(), "parcel");
        expenseResourceService.save(expense.getId(), crops, "crop");
        expenseResourceService.save(expense.getId(), cropVarieties, "crop_variety");
    }

    @Transactional
    public void remove(Long expenseId) {
        expenseService.remove(expenseId);
    }
}
