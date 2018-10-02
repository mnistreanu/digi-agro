package com.arobs.service.expense;

import com.arobs.entity.*;
import com.arobs.model.expense.ExpenseItemModel;
import com.arobs.model.expense.ExpenseModel;
import com.arobs.model.expense.MachineryExpenseListModel;
import com.arobs.model.expense.MachineryExpenseModel;
import com.arobs.service.EmployeeService;
import com.arobs.service.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Deprecated
public class MachineryExpenseService {

    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private ExpenseItemService expenseItemService;

    @Autowired
    private ExpenseResourceService expenseResourceService;
//
//    public List<MachineryExpenseListModel> find(Long tenantId) {
//        List<Expense> expenses = expenseService.find(tenantId, ExpenseCategory.MACHINERY);
//        List<MachineryExpenseListModel> models = new ArrayList<>();
//
//        for (Expense expense : expenses) {
//            ExpenseModel expenseModel = this.getModel(expense);
//            List<Employee> employees = employeeService.findAll(expenseModel.getEmployees());
//            List<Machine> machines = machineService.findAll(expenseModel.getMachines());
//
//            String employeeStr = employees.stream().map(e -> e.getFirstName() + " " + e.getLastName()).collect(Collectors.joining(", "));
//            String machinesStr = machines.stream().map(m -> m.getIdentifier() + " " + m.getBrand().getName() + " " + m.getModel()).collect(Collectors.joining(", "));
//
//
//            for (ExpenseItemModel expenseItemModel : expenseModel.getExpenseItems()) {
//                MachineryExpenseListModel listModel = new MachineryExpenseListModel();
//                models.add(listModel);
//                listModel.setExpenseId(expense.getId());
//                listModel.setPlantingDate(expense.getExpenseDate());
//                listModel.setSparePart(expenseItemModel.getTitle());
//                listModel.setSparePartPrice(expenseItemModel.getTotalCost());
//                listModel.setEmployees(employeeStr);
//                listModel.setMachines(machinesStr);
//                listModel.setCreatedAt(expense.getCreatedAt());
//                listModel.setCreatedBy("" + expense.getCreatedBy());
//            }
//        }
//
//        return models;
//    }
//
//    public ExpenseModel findOne(Long expenseId) {
//        Expense expense = expenseService.findOne(expenseId);
//        return expenseService.getModel(expense);
//    }
//
//    @Transactional
//    public ExpenseModel save(ExpenseModel model, Long tenant) {
//
//        Expense expense = expenseService.save(model, tenant);
//        expenseItemService.save(model.getExpenseItems(), expense);
//        expenseResourceService.save(expense.getId(), model.getMachines(), "machine");
//        expenseResourceService.save(expense.getId(), model.getEmployees(), "employee");
//
//        return getModel(expense);
//    }
//
//    @Transactional
//    public void remove(Long expenseId) {
//        expenseService.remove(expenseId);
//    }
}
