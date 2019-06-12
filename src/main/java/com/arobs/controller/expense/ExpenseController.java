package com.arobs.controller.expense;

import com.arobs.entity.expense.Expense;
import com.arobs.model.expense.ExpenseModel;
import com.arobs.model.expense.ExpenseSeasonTreeModel;
import com.arobs.model.expense.ExpenseSummaryModel;
import com.arobs.service.expense.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/expense")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @RequestMapping(value = "/season-tree", method = RequestMethod.GET)
    public ResponseEntity<List<ExpenseSeasonTreeModel>> getExpenseSeasonTreeModels(HttpSession session) {
        Long tenantId = (Long) session.getAttribute("tenant");
        List<ExpenseSeasonTreeModel> models = expenseService.getExpenseSeasonTreeModels(tenantId);
        return ResponseEntity.ok(models);
    }

    @RequestMapping(value = "/summary/{cropSeasonId}", method = RequestMethod.GET)
    public ResponseEntity<List<ExpenseSummaryModel>> getSummaryModels(@PathVariable Long cropSeasonId, HttpSession session) {
        Long tenantId = (Long) session.getAttribute("tenant");
        return ResponseEntity.ok(expenseService.getSummaryModels(tenantId, cropSeasonId));
    }

    @RequestMapping(value = "/{cropSeasonId}", method = RequestMethod.GET)
    public ResponseEntity<List<ExpenseModel>> getList(@PathVariable Long cropSeasonId, HttpSession session) {
        Long tenantId = (Long) session.getAttribute("tenant");

        List<Expense> expenses = expenseService.find(tenantId, cropSeasonId);
        List<ExpenseModel> models = expenses.stream().map(ExpenseModel::new).collect(Collectors.toList());

        return ResponseEntity.ok(models);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<ExpenseModel> save(@RequestBody ExpenseModel model, HttpSession session) {
        Long tenantId = (Long) session.getAttribute("tenant");
        Expense expense = expenseService.save(model, tenantId);
        return ResponseEntity.ok(new ExpenseModel(expense));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void remove(@PathVariable Long id) {
        expenseService.delete(id);
    }

}
