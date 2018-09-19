package com.arobs.controller;

import com.arobs.model.expense.MachineryExpenseListModel;
import com.arobs.model.expense.MachineryExpenseModel;
import com.arobs.service.expense.MachineryExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/machinery-expense")
public class MachineryExpenseController {

    @Autowired
    private MachineryExpenseService machineryExpenseService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<MachineryExpenseListModel>> getModels(HttpSession session) {
        Long tenant = (Long) session.getAttribute("tenant");
        List<MachineryExpenseListModel> models = machineryExpenseService.find(tenant);
        return ResponseEntity.ok(models);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<MachineryExpenseModel> getModel(@PathVariable Long id) {
        MachineryExpenseModel expenseModel = machineryExpenseService.findOne(id);
        return ResponseEntity.ok(expenseModel);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<MachineryExpenseModel> save(@RequestBody MachineryExpenseModel model,
                                                      HttpSession session) {
        Long tenant = (Long) session.getAttribute("tenant");
        return ResponseEntity.ok(machineryExpenseService.save(model, tenant));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void remove(@PathVariable Long id) {
        machineryExpenseService.remove(id);
    }

}
