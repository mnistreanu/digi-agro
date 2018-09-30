package com.arobs.controller.expense;

import com.arobs.entity.Employee;
import com.arobs.entity.Expense;
import com.arobs.entity.ExpenseCategory;
import com.arobs.entity.Machine;
import com.arobs.model.EmployeeModel;
import com.arobs.model.MachineModel;
import com.arobs.model.expense.ExpenseItemModel;
import com.arobs.model.expense.ExpenseModel;
import com.arobs.model.expense.MachineryExpenseListModel;
import com.arobs.model.expense.MachineryExpenseModel;
import com.arobs.service.EmployeeService;
import com.arobs.service.MachineService;
import com.arobs.service.expense.ExpenseItemService;
import com.arobs.service.expense.ExpenseResourceService;
import com.arobs.service.expense.ExpenseService;
import com.arobs.service.expense.MachineryExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/machinery-expense")
public class MachineryExpenseController {

    @Autowired
    private ExpenseService expenseService;

//    @Autowired
//    private ExpenseItemService expenseItemService;
//
//    @Autowired
//    private ExpenseResourceService expenseResourceService;
//
//    @Autowired
//    private MachineryExpenseService machineryExpenseService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private MachineService machineService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<MachineryExpenseListModel>> getModels(HttpSession session) {
        Long tenantId = (Long) session.getAttribute("tenant");

        List<Expense> expenses = expenseService.find(tenantId, ExpenseCategory.MACHINERY);
        List<MachineryExpenseListModel> resultModels = new ArrayList<>();

        for (Expense expense : expenses) {
            ExpenseModel expenseModel = expenseService.getModel(expense);

            List<EmployeeModel> employeeModels = new ArrayList<>();
            List<Employee> employees = employeeService.findAll(expenseModel.getEmployees());
            for (Employee emp:employees) {
                employeeModels.add(new EmployeeModel(emp));
            }

            List<MachineModel> machineModels = new ArrayList<>();
            List<Machine> machines = machineService.findAll(expenseModel.getMachines());
            for (Machine machine:machines) {
                machineModels.add(new MachineModel(machine));
            }


            for (ExpenseItemModel expenseItemModel : expenseModel.getExpenseItems()) {
                MachineryExpenseListModel listModel = new MachineryExpenseListModel();
                resultModels.add(listModel);
                listModel.setExpenseId(expense.getId());
                listModel.setExpenseDate(expense.getExpenseDate());
                listModel.setSparePart(expenseItemModel.getTitle());
                listModel.setSparePartPrice(expenseItemModel.getTotalCost());
                listModel.setEmployees(employeeModels);
                listModel.setMachines(machineModels);
                listModel.setCreatedAt(expense.getCreatedAt());
                listModel.setCreatedBy("" + expense.getCreatedBy());
            }
        }

        return ResponseEntity.ok(resultModels);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ExpenseModel> getModel(@PathVariable Long id) {
        ExpenseModel expenseModel = expenseService.findOneModel(id);
        return ResponseEntity.ok(expenseModel);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<ExpenseModel> save(@RequestBody ExpenseModel model,
                                             HttpSession session) {
        Long tenant = (Long) session.getAttribute("tenant");
        return ResponseEntity.ok(expenseService.saveModel(model, tenant));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void remove(@PathVariable Long id) {
        expenseService.remove(id);
    }

}
