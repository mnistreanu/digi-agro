package com.arobs.controller.expense;

import com.arobs.entity.Expense;
import com.arobs.entity.ExpenseCategory;
import com.arobs.model.expense.ExpenseModel;
import com.arobs.model.expense.ExpenseSeasonTreeModel;
import com.arobs.service.expense.ExpenseCategoryService;
import com.arobs.service.expense.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/expense")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;
    @Autowired
    private ExpenseCategoryService expenseCategoryService;

    @RequestMapping(value = "/season-tree", method = RequestMethod.GET)
    public ResponseEntity<List<ExpenseSeasonTreeModel>> getExpenseSeasonTreeModels(HttpSession session) {
        Long tenantId = (Long) session.getAttribute("tenant");
        List<ExpenseSeasonTreeModel> models = expenseService.getExpenseSeasonTreeModels(tenantId);
        return ResponseEntity.ok(models);
    }


    @RequestMapping(value = "/{cropSeasonId}", method = RequestMethod.GET)
    public ResponseEntity<List<ExpenseModel>> getList(@PathVariable Long cropSeasonId, HttpSession session) {
        Long tenantId = (Long) session.getAttribute("tenant");

        List<Expense> expenses = expenseService.find(tenantId, cropSeasonId);
        List<ExpenseModel> models = new ArrayList<>();
        Map<Long, ExpenseCategory> expenseCategoryMap = new HashMap<>();

        for (Expense expense : expenses) {
            ExpenseCategory expenseCategory = expenseCategoryMap
                    .computeIfAbsent(expense.getCategoryId(), k -> expenseCategoryService.findOne(expense.getCategoryId()));
            models.add(new ExpenseModel(expense, expenseCategory));
        }

        return ResponseEntity.ok(models);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<ExpenseModel> save(@RequestBody ExpenseModel model, HttpSession session) {
        Long tenantId = (Long) session.getAttribute("tenant");
        Expense expense = expenseService.save(model, tenantId);
        return ResponseEntity.ok(new ExpenseModel(expense, expense.getCategory()));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void remove(@PathVariable Long id) {
        expenseService.remove(id);
    }

}
