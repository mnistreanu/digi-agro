package com.arobs.controller.expense;

import com.arobs.entity.Expense;
import com.arobs.entity.ExpenseCategory;
import com.arobs.entity.Parcel;
import com.arobs.enums.UnitOfMeasure;
import com.arobs.model.expense.ExpenseModel;
import com.arobs.model.expense.SowingExpenseModel;
import com.arobs.service.expense.ExpenseService;
import com.arobs.service.parcel.ParcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sowing-expense")
public class SowingExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private ParcelService parcelService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<SowingExpenseModel> get(@PathVariable Long id, HttpSession session) {
        Long tenantId = (Long) session.getAttribute("tenant");

        SowingExpenseModel model = new SowingExpenseModel();
        model.setExpenseId(id);
        model.setExpenseDate(new Date());
        model.setCrop("Porumb");
        model.setVariety("Mama");
        model.setIcon("corn.png");
        model.setUnitOfMeasure(UnitOfMeasure.SowingUnit.getUnitOfMeasure());
        model.setArea((double) ((int) (Math.random() * 10000)) / 100);
        model.setNormSown1Ha((double) ((int) (Math.random() * 10000)) / 100);
        model.setActualSown1Ha((double) ((int) (Math.random() * 10000)) / 100);
        model.setUnitPrice(BigDecimal.valueOf((double) ((int) (Math.random() * 10000)) / 100));
        model.setCreatedAt(new Date());
        model.setCreatedBy("Jorik Vartanov");

        List<Parcel> parcels = parcelService.find(tenantId);
        List<Long> parcelIds = parcels.stream().map(Parcel::getId).collect(Collectors.toList());
        model.setParcels(parcelIds);

        return ResponseEntity.ok(model);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<SowingExpenseModel>> getModels(HttpSession session) {
        Long tenantId = (Long) session.getAttribute("tenant");

        List<Expense> expenses = expenseService.find(tenantId, ExpenseCategory.MACHINERY);//SOWING
        List<SowingExpenseModel> resultModels = new ArrayList<>();

        for (Expense expense : expenses) {
            ExpenseModel expenseModel = expenseService.getModel(expense);

            List<Parcel> parcels = parcelService.findAll(expenseModel.getMachines());
            List<Long> parcelIds = parcels.stream().map(Parcel::getId).collect(Collectors.toList());

            SowingExpenseModel model1 = new SowingExpenseModel();
            model1.setExpenseId(expense.getId());
            model1.setExpenseDate(expense.getExpenseDate());
            model1.setCrop("Porumb");
            model1.setVariety("Mama");
            model1.setIcon("corn.png");
            model1.setUnitOfMeasure(UnitOfMeasure.SowingUnit.getUnitOfMeasure());
            model1.setArea((double) ((int) (Math.random() * 10000)) / 100);
            model1.setNormSown1Ha((double) ((int) (Math.random() * 10000)) / 100);
            model1.setActualSown1Ha((double) ((int) (Math.random() * 10000)) / 100);
            model1.setUnitPrice(BigDecimal.valueOf((double) ((int) (Math.random() * 10000)) / 100));
            model1.setCreatedAt(expense.getCreatedAt());
            model1.setCreatedBy("Mihalici");
            model1.setParcels(parcelIds);
            resultModels.add(model1);

            SowingExpenseModel model2 = new SowingExpenseModel();
            model2.setExpenseId(expense.getId());
            model2.setExpenseDate(expense.getExpenseDate());
            model2.setCrop("Cartof");
            model2.setVariety("Irga");
            model1.setIcon("potatoes.png");
            model2.setUnitOfMeasure(UnitOfMeasure.SowingUnit.getUnitOfMeasure());
            model2.setArea((double) ((int) (Math.random() * 10000)) / 100);
            model2.setNormSown1Ha((double) ((int) (Math.random() * 10000)) / 100);
            model2.setActualSown1Ha((double) ((int) (Math.random() * 10000)) / 100);
            model2.setUnitPrice(BigDecimal.valueOf((double) ((int) (Math.random() * 10000)) / 100));
            model2.setCreatedAt(expense.getCreatedAt());
            model2.setCreatedBy("Ivan Dulin");
            model2.setParcels(parcelIds);
            resultModels.add(model2);

            SowingExpenseModel model3 = new SowingExpenseModel();
            model3.setExpenseId(expense.getId());
            model3.setExpenseDate(expense.getExpenseDate());
            model3.setCrop("Griu");
            model3.setVariety("Aktios");
            model1.setIcon("wheat.png");
            model3.setUnitOfMeasure(UnitOfMeasure.SowingUnit.getUnitOfMeasure());
            model3.setArea((double) ((int) (Math.random() * 10000)) / 100);
            model3.setNormSown1Ha((double) ((int) (Math.random() * 10000)) / 100);
            model3.setActualSown1Ha((double) ((int) (Math.random() * 10000)) / 100);
            model3.setUnitPrice(BigDecimal.valueOf((double) ((int) (Math.random() * 10000)) / 100));
            model3.setCreatedAt(expense.getCreatedAt());
            model3.setCreatedBy("Jorik Vartanov");
            model3.setParcels(parcelIds);
            resultModels.add(model3);
        }

        return ResponseEntity.ok(resultModels);
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
