package com.arobs.controller;

import com.arobs.model.expense.MachineryExpenseListModel;
import com.arobs.model.expense.MachineryExpenseModel;
import com.arobs.service.expense.MachineryExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
//
//    @RequestMapping(value = "/unique", method = RequestMethod.GET)
//    public ResponseEntity<Boolean> isUnique(@RequestParam(value = "id", required = false) Long id,
//                                            @RequestParam("field") String field, @RequestParam("value") String value) {
//        return ResponseEntity.ok(machineService.isUnique(id, field, value));
//    }
//
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
//    @RequestMapping(value = "/", method = RequestMethod.POST)
//    public ResponseEntity<MachineModel> save(@RequestBody MachineModel model, HttpSession session) {
//        Long tenant = (Long) session.getAttribute("tenant");
//        return ResponseEntity.ok(new MachineModel(machineService.save(model, tenant)));
//    }
//
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
//    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//    public void remove(@PathVariable Long id) {
//        machineService.remove(id);
//    }
//

}
