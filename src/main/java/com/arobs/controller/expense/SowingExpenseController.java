package com.arobs.controller.expense;

import com.arobs.model.expense.SowingExpenseModel;
import com.arobs.service.expense.SowingExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/sowing-expense")
public class SowingExpenseController {

    @Autowired
    private SowingExpenseService sowingExpenseService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<SowingExpenseModel> get(@PathVariable Long id) {
        return ResponseEntity.ok(sowingExpenseService.getModel(id));
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<SowingExpenseModel>> getModels(HttpSession session) {
        Long tenantId = (Long) session.getAttribute("tenant");
        return ResponseEntity.ok(sowingExpenseService.get(tenantId));
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<SowingExpenseModel> save(@RequestBody SowingExpenseModel model,
                                             HttpSession session) {
        Long tenant = (Long) session.getAttribute("tenant");
        return ResponseEntity.ok(sowingExpenseService.save(model, tenant));
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void remove(@PathVariable Long id) {
        sowingExpenseService.remove(id);
    }

}
