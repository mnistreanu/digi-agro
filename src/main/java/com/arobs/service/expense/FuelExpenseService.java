package com.arobs.service.expense;

import com.arobs.entity.*;
import com.arobs.model.expense.ExpenseItemModel;
import com.arobs.model.expense.FuelExpenseListModel;
import com.arobs.model.expense.FuelExpenseModel;
import com.arobs.service.EmployeeService;
import com.arobs.service.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Deprecated
public class FuelExpenseService {

    @Autowired
    private ExpenseService expenseService;
    @Autowired
    private ExpenseItemService expenseItemService;
    @Autowired
    private ExpenseResourceService expenseResourceService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private MachineService machineService;
//
//    public List<FuelExpenseListModel> find(Long tenantId) {
//        List<Expense> expenses = expenseService.find(tenantId);
//        List<FuelExpenseListModel> models = new ArrayList<>();
//
//        for (Expense expense : expenses) {
//            FuelExpenseModel expenseModel = this.getModel(expense);
//            List<Employee> employees = employeeService.findAll(expenseModel.getEmployees());
//            List<Machine> machines = machineService.findAll(expenseModel.getMachines());
//
//            String employeeStr = employees.stream().map(e -> e.getFirstName() + " " + e.getLastName()).collect(Collectors.joining(", "));
//            String machinesStr = machines.stream().map(m -> m.getIdentifier() + " " + m.getBrand().getName() + " " + m.getModel()).collect(Collectors.joining(", "));
//
//
//            for (ExpenseItemModel expenseItemModel : expenseModel.getExpenseItems()) {
//                FuelExpenseListModel listModel = new FuelExpenseListModel();
//                models.add(listModel);
//                listModel.setExpenseId(expense.getId());
//                listModel.setPlantingDate(expense.getExpenseDate());
//                listModel.setEmployee(employeeStr);
//                listModel.setMachine(machinesStr);
//            }
//        }
//
//        return models;
//    }
//
//    public FuelExpenseModel findOne(Long expenseId) {
//        Expense expense = expenseService.findOne(expenseId);
//        return getModel(expense);
//    }
//
//    public FuelExpenseModel getModel(Expense expense) {
//        FuelExpenseModel expenseModel = new FuelExpenseModel(expense);
//        List<ExpenseItem> expenseItems = expenseItemService.find(expense.getId());
//        for (ExpenseItem expenseItem : expenseItems) {
//            expenseModel.getExpenseItems().add(new ExpenseItemModel(expenseItem));
//        }
//
//        List<ExpenseResource> expenseResources = expenseResourceService.find(expense.getId());
//        for (ExpenseResource expenseResource : expenseResources) {
//            if (expenseResource.getTableName().equals("machine")) {
//                expenseModel.getMachines().add(expenseResource.getResourceId());
//            }
//            else if (expenseResource.getTableName().equals("employee")) {
//                expenseModel.getEmployees().add(expenseResource.getResourceId());
//            }
//        }
//
//        return expenseModel;
//    }
//
//    @Transactional
//    public FuelExpenseModel save(FuelExpenseModel model, Long tenant) {
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
