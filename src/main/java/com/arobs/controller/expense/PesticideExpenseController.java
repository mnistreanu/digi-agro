package com.arobs.controller.expense;

import com.arobs.entity.Expense;
import com.arobs.entity.ExpenseCategory;
import com.arobs.entity.Parcel;
import com.arobs.enums.UnitOfMeasure;
import com.arobs.model.expense.ExpenseModel;
import com.arobs.model.expense.PesticideExpenseListModel;
import com.arobs.model.parcel.ParcelModel;
import com.arobs.service.expense.ExpenseService;
import com.arobs.service.parcel.ParcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/pesticide-expense")
public class PesticideExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private ParcelService parcelService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<PesticideExpenseListModel>> getModels(HttpSession session) {
        Long tenantId = (Long) session.getAttribute("tenant");

        List<Expense> expenses = expenseService.find(tenantId, ExpenseCategory.MACHINERY);
        List<PesticideExpenseListModel> resultModels = new ArrayList<>();

        for (Expense expense : expenses) {
            ExpenseModel expenseModel = expenseService.getModel(expense);

            List<ParcelModel> parcelModels = new ArrayList<>();
            List<Parcel> parcels = parcelService.findAll(expenseModel.getMachines());
            for (Parcel parcel:parcels) {
                parcelModels.add(new ParcelModel(parcel));
            }

            PesticideExpenseListModel model1 = new PesticideExpenseListModel();
            model1.setExpenseId(expense.getId());
            model1.setExpenseDate(expense.getExpenseDate());
            model1.setCrop("Porumb");
            model1.setPesticideType("INSECTICIDE");
            model1.setPesticideName("Armetil cobre");
            model1.setPhase("Umflarea mugurilor");
            model1.setResult("A avut efect pozitiv");
            model1.setComments("Se putea si mai bine");
            model1.setCreatedAt(expense.getCreatedAt());
            model1.setCreatedBy("Mihalici");
            model1.setParcels(parcelModels);
            resultModels.add(model1);

            PesticideExpenseListModel model2 = new PesticideExpenseListModel();
            model2.setExpenseId(expense.getId());
            model2.setExpenseDate(expense.getExpenseDate());
            model2.setCrop("Cartof");
            model2.setPesticideType("HERBICIDE");
            model2.setPesticideName("Herbastres");
            model2.setPhase("Butonul roz");
            model2.setResult("Diverse probleme, dar merge");
            model2.setComments("Se putea si mai bine");
            model2.setCreatedAt(expense.getCreatedAt());
            model2.setCreatedBy("Ivan Dulin");
            model2.setParcels(parcelModels);
            resultModels.add(model2);

            PesticideExpenseListModel model3 = new PesticideExpenseListModel();
            model3.setExpenseId(expense.getId());
            model3.setExpenseDate(expense.getExpenseDate());
            model3.setCrop("Griu");
            model3.setPesticideType("INSECTICIDE");
            model3.setPesticideName("Arahno killer 2000");
            model3.setPhase("in perioada infloirtului");
            model3.setResult("Vau vau ce interesant");
            model3.setComments("Se putea si mai bine");
            model3.setCreatedAt(expense.getCreatedAt());
            model3.setCreatedBy("Jorik Vartanov");
            model3.setParcels(parcelModels);
            resultModels.add(model3);

//            for (ExpenseItemModel expenseItemModel : expenseModel.getExpenseItems()) {
//                PesticideExpenseListModel listModel = new PesticideExpenseListModel();
//                resultModels.add(listModel);
//                listModel.setExpenseId(expense.getId());
//                listModel.setExpenseDate(expense.getExpenseDate());
//                listModel.setSparePart(expenseItemModel.getTitle());
//                listModel.setSparePartPrice(expenseItemModel.getTotalCost());
//                listModel.setParcels(parcelModels);
//                listModel.setCreatedAt(expense.getCreatedAt());
//                listModel.setCreatedBy("" + expense.getCreatedBy());
//            }
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
