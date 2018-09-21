package com.arobs.controller;

import com.arobs.model.expense.FuelExpenseListModel;
import com.arobs.model.expense.FuelExpenseModel;
import com.arobs.service.expense.FuelExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/fuel-expense")
public class FuelExpenseController {

    @Autowired
    private FuelExpenseService fuelExpenseService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<FuelExpenseListModel>> getModels(HttpSession session) {
        Long tenant = (Long) session.getAttribute("tenant");
        List<FuelExpenseListModel> models = fuelExpenseService.find(tenant);
        return ResponseEntity.ok(models);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<FuelExpenseModel> getModel(@PathVariable Long id) {
        FuelExpenseModel expenseModel = fuelExpenseService.findOne(id);
        return ResponseEntity.ok(expenseModel);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<FuelExpenseModel> save(@RequestBody FuelExpenseModel model,
                                                      HttpSession session) {
        Long tenant = (Long) session.getAttribute("tenant");
        return ResponseEntity.ok(fuelExpenseService.save(model, tenant));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void remove(@PathVariable Long id) {
        fuelExpenseService.remove(id);
    }

}
