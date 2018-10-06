package com.arobs.controller.expense;

import com.arobs.model.expense.ExpenseListModel;
import com.arobs.service.expense.ManageExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/expense")
public class ExpenseController {

    @Autowired
    private ManageExpenseService manageExpenseService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<ExpenseListModel>> getModels(HttpSession session) {
        Long tenant = (Long) session.getAttribute("tenant");
        List<ExpenseListModel> models = manageExpenseService.find(tenant);
        return ResponseEntity.ok(models);
    }
}
