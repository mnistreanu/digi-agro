package com.arobs.controller.expense;

import com.arobs.entity.Employee;
import com.arobs.entity.Expense;
import com.arobs.entity.ExpenseCategory;
import com.arobs.entity.Machine;
import com.arobs.model.EmployeeModel;
import com.arobs.model.machine.MachineModel;
import com.arobs.model.expense.ExpenseModel;
import com.arobs.model.expense.FuelExpenseListModel;
import com.arobs.service.EmployeeService;
import com.arobs.service.MachineService;
import com.arobs.service.expense.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/fuel-expense")
public class FuelExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private MachineService machineService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<FuelExpenseListModel>> getModels(HttpSession session) {
        Long tenantId = (Long) session.getAttribute("tenant");
        List<Expense> expenses = expenseService.find(tenantId, ExpenseCategory.FUEL);

        List<FuelExpenseListModel> resultModels = new ArrayList<>();

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

            FuelExpenseListModel listModel = new FuelExpenseListModel();
            listModel.setExpenseId(expense.getId());
            listModel.setExpenseDate(expense.getExpenseDate());
            listModel.setEmployees(employeeModels);
            listModel.setMachines(machineModels);
            listModel.setFuels(expenseModel.getExpenseItems());
            listModel.setCreatedAt(expense.getCreatedAt());
            listModel.setCreatedBy("" + expense.getCreatedBy());
            resultModels.add(listModel);

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
        return ResponseEntity.ok(expenseService.saveModel(model, tenant, ExpenseCategory.FUEL));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void remove(@PathVariable Long id) {
        expenseService.remove(id);
    }
}
