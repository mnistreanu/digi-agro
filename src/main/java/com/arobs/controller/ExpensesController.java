package com.arobs.controller;

import com.arobs.entity.AgroWork;
import com.arobs.entity.AgroWorkType;
import com.arobs.entity.Crop;
import com.arobs.entity.ExpenseCategory;
import com.arobs.enums.UnitOfMeasure;
import com.arobs.model.PayloadModel;
import com.arobs.model.agrowork.OtherWorksModel;
import com.arobs.model.expense.ExpenseCategoryModel;
import com.arobs.service.agrowork.AgroWorkService;
import com.arobs.service.agrowork.AgroWorkTypeService;
import com.arobs.service.crop.CropService;
import com.arobs.service.expense.ExpenseCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/expenses")
public class ExpensesController {

    @Autowired
    private ExpenseCategoryService categoryService;

    @RequestMapping(value = "/categories-tree", method = RequestMethod.GET)
    public ResponseEntity<PayloadModel> getCategoriesTree(HttpSession session) {
        Long tenantId = (Long) session.getAttribute("tenant");

        List<ExpenseCategoryModel> list = new ArrayList<>();
        PayloadModel<ExpenseCategoryModel> payloadModel = new PayloadModel<>();

        List<ExpenseCategory> categories = categoryService.find(tenantId);
        for (ExpenseCategory mainCategory : categories) {
            if (mainCategory.getParentId() == null) {
                ExpenseCategoryModel parentModel = new ExpenseCategoryModel(mainCategory);
                parentModel.setChildren(new ArrayList<>());

                for (ExpenseCategory subCategory : categories) {
                    if (subCategory.getParentId() != null && subCategory.getParentId().equals(mainCategory.getId())) {
                        ExpenseCategoryModel childModel = new ExpenseCategoryModel(subCategory);
                        parentModel.getChildren().add(childModel);
                    }
                }

                list.add(parentModel);
            }
        }

        ExpenseCategoryModel[] arr = new ExpenseCategoryModel[list.size()];
        payloadModel.setPayload(list.toArray(arr));
        payloadModel.setStatus(PayloadModel.STATUS_SUCCESS);
        return ResponseEntity.ok(payloadModel);
    }
}
