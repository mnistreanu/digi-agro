package com.arobs.controller.expense;

import com.arobs.entity.*;
import com.arobs.enums.UnitOfMeasure;
import com.arobs.model.EmployeeModel;
import com.arobs.model.machine.MachineModel;
import com.arobs.model.expense.ExpenseModel;
import com.arobs.model.expense.WorksExpenseListModel;
import com.arobs.service.EmployeeService;
import com.arobs.service.MachineService;
import com.arobs.service.expense.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/works-expense")
public class WorksExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private MachineService machineService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<WorksExpenseListModel>> getModels(HttpSession session) {
        Long tenantId = (Long) session.getAttribute("tenant");

        List<Expense> expenses = expenseService.find(tenantId, ExpenseCategory.MACHINERY);
        List<WorksExpenseListModel> resultModels = new ArrayList<>();

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

            WorksExpenseListModel model1 = new WorksExpenseListModel();
            model1.setExpenseId(expense.getId());
            model1.setExpenseDate(expense.getExpenseDate());
            model1.setWorkType("Plivit");
            model1.setMachines(machineModels);
            model1.setEmployees(employeeModels);
            model1.setCrop("Porumb");
            model1.setUnitOfMeasure(UnitOfMeasure.Ha.getUnitOfMeasure());
            model1.setQuantity((double)((int)(Math.random() * 10000))/100);
            model1.setQuantityNorm((double)((int)(Math.random() * 10000))/100);
            model1.setQuantityDefacto((double)((int)(Math.random() * 10000))/100);
            model1.setPrice1Norm(BigDecimal.valueOf((double)((int)(Math.random() * 10000))/100));
            model1.setCreatedAt(expense.getCreatedAt());
            model1.setCreatedBy("Mihalici");
            resultModels.add(model1);

            WorksExpenseListModel model2 = new WorksExpenseListModel();
            model2.setExpenseId(expense.getId());
            model2.setExpenseDate(expense.getExpenseDate());
            model2.setWorkType("Carat apa");
            model2.setMachines(machineModels);
            model2.setEmployees(employeeModels);
            model2.setCrop("Cartof");
            model2.setUnitOfMeasure(UnitOfMeasure.Ha.getUnitOfMeasure());
            model2.setQuantity((double)((int)(Math.random() * 10000))/100);
            model2.setQuantityNorm((double)((int)(Math.random() * 10000))/100);
            model2.setQuantityDefacto((double)((int)(Math.random() * 10000))/100);
            model2.setPrice1Norm(BigDecimal.valueOf((double)((int)(Math.random() * 10000))/100));
            model2.setCreatedAt(expense.getCreatedAt());
            model2.setCreatedBy("Ivan Dulin");
            resultModels.add(model2);

            WorksExpenseListModel model3 = new WorksExpenseListModel();
            model3.setExpenseId(expense.getId());
            model3.setExpenseDate(expense.getExpenseDate());
            model3.setWorkType("Dezapezire");
            model3.setMachines(machineModels);
            model3.setEmployees(employeeModels);
            model3.setCrop("Griu");
            model3.setUnitOfMeasure(UnitOfMeasure.Ha.getUnitOfMeasure());
            model3.setQuantity((double)((int)(Math.random() * 10000))/100);
            model3.setQuantityNorm((double)((int)(Math.random() * 10000))/100);
            model3.setQuantityDefacto((double)((int)(Math.random() * 10000))/100);
            model3.setPrice1Norm(BigDecimal.valueOf((double)((int)(Math.random() * 10000))/100));
            model3.setCreatedAt(expense.getCreatedAt());
            model3.setCreatedBy("Jorik Vartanov");
            resultModels.add(model3);
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
        return ResponseEntity.ok(expenseService.saveModel(model, tenant, ExpenseCategory.MACHINERY));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void remove(@PathVariable Long id) {
        expenseService.remove(id);
    }

}
