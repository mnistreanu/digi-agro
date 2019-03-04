package com.arobs.controller.expense;

import com.arobs.entity.ExpenseCategory;
import com.arobs.model.PayloadModel;
import com.arobs.model.expense.ExpenseCategoryModel;
import com.arobs.service.expense.ExpenseCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/expense-category")
public class ExpenseCategoryController {

    @Autowired
    private ExpenseCategoryService expenseCategoryService;

    @RequestMapping(value = "/tree", method = RequestMethod.GET)
    public ResponseEntity<PayloadModel> getTree(HttpSession session) {
        Long tenantId = (Long) session.getAttribute("tenant");

        List<ExpenseCategoryModel> models = new ArrayList<>();
        Map<Long, ExpenseCategoryModel> parentMap = new HashMap<>();

        List<ExpenseCategory> categories = expenseCategoryService.find(tenantId);

        // process parents
        for (ExpenseCategory category : categories) {
            if (category.getParentId() != null) {
                continue;
            }

            ExpenseCategoryModel model = new ExpenseCategoryModel(category);
            parentMap.put(category.getId(), model);
            models.add(model);
        }

        // process children
        for (ExpenseCategory category : categories) {
            if (category.getParentId() == null) {
                continue;
            }
            ExpenseCategoryModel model = new ExpenseCategoryModel(category);
            ExpenseCategoryModel parent = parentMap.get(category.getParentId());
            if (parent == null) {
                continue;
            }
            if (parent.getChildren() == null) {
                parent.setChildren(new ArrayList<>());
            }
            parent.getChildren().add(model);
        }

        PayloadModel<ExpenseCategoryModel> payloadModel = new PayloadModel<>();
        payloadModel.setPayload(models);
        payloadModel.setStatus(PayloadModel.STATUS_SUCCESS);
        return ResponseEntity.ok(payloadModel);
    }

    @RequestMapping(value = "/roots", method = RequestMethod.GET)
    public ResponseEntity<List<ExpenseCategoryModel>> getRoots(HttpSession session) {
        Long tenantId = (Long) session.getAttribute("tenant");
        List<ExpenseCategory> roots = expenseCategoryService.findRoots(tenantId);
        List<ExpenseCategoryModel> models = roots.stream().map(ExpenseCategoryModel::new).collect(Collectors.toList());
        return ResponseEntity.ok(models);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ExpenseCategoryModel> getModel(@PathVariable Long id) {
        ExpenseCategory category = expenseCategoryService.findOne(id);
        return ResponseEntity.ok(new ExpenseCategoryModel(category));
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<ExpenseCategory> save(@RequestBody ExpenseCategoryModel model,
                                                HttpSession session) {
        Long tenantId = (Long) session.getAttribute("tenant");
        return ResponseEntity.ok(expenseCategoryService.save(model, tenantId));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void remove(@PathVariable Long id) {
        expenseCategoryService.remove(id);
    }

}
