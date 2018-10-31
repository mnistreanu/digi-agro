package com.arobs.controller.expense;

import com.arobs.entity.Expense;
import com.arobs.entity.ExpenseCategory;
import com.arobs.entity.Parcel;
import com.arobs.enums.FertilizerType;
import com.arobs.model.crop.CropModel;
import com.arobs.model.chemicals.FertilizerModel;
import com.arobs.model.expense.ExpenseModel;
import com.arobs.model.expense.FertilizerExpenseListModel;
import com.arobs.model.parcel.ParcelModel;
import com.arobs.service.expense.ExpenseService;
import com.arobs.service.parcel.ParcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/fertilizer-expense")
public class FertilizerExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private ParcelService parcelService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<FertilizerExpenseListModel>> getModels(HttpSession session) {
        Long tenantId = (Long) session.getAttribute("tenant");

        List<Expense> expenses = expenseService.find(tenantId, ExpenseCategory.MACHINERY);
        List<FertilizerExpenseListModel> resultModels = new ArrayList<>();

        for (Expense expense : expenses) {
            ExpenseModel expenseModel = expenseService.getModel(expense);

            List<ParcelModel> parcelModels = new ArrayList<>();
            List<Parcel> parcels = parcelService.findAll(expenseModel.getMachines());
            for (Parcel parcel:parcels) {
                parcelModels.add(new ParcelModel(parcel));
            }

            CropModel porumb = new CropModel();
            porumb.setNameRo("Porumb");

            FertilizerModel azotat = new FertilizerModel();
            azotat.setFertilizerType(FertilizerType.MINERAL);
            azotat.setNameRo("AZOTAT (NITRAT) DE AMONIU granulat");

            FertilizerExpenseListModel model1 = new FertilizerExpenseListModel();
            model1.setExpenseId(expense.getId());
            model1.setExpenseDate(expense.getExpenseDate());
            model1.setCropModel(porumb);
            model1.setFertilizerModel(azotat);
            model1.setPhase("Umflarea mugurilor");
            model1.setResult("A avut efect pozitiv");
            model1.setComments("Se putea si mai bine");
            model1.setCreatedAt(expense.getCreatedAt());
            model1.setCreatedBy("Mihalici");
            model1.setParcels(parcelModels);
            resultModels.add(model1);

            CropModel cartof = new CropModel();
            porumb.setNameRo("Cartof");

            FertilizerModel uree = new FertilizerModel();
            uree.setFertilizerType(FertilizerType.CHEMICAL);
            uree.setNameRo("UREE granulata");

            FertilizerExpenseListModel model2 = new FertilizerExpenseListModel();
            model2.setExpenseId(expense.getId());
            model2.setExpenseDate(expense.getExpenseDate());
            model2.setCropModel(cartof);
            model2.setFertilizerModel(uree);
            model2.setPhase("Butonul roz");
            model2.setResult("Diverse probleme, dar merge");
            model2.setComments("Se putea si mai bine");
            model2.setCreatedAt(expense.getCreatedAt());
            model2.setCreatedBy("Ivan Dulin");
            model2.setParcels(parcelModels);
            resultModels.add(model2);

            CropModel griu = new CropModel();
            griu.setNameRo("Griu");

            FertilizerModel nitrocalcar = new FertilizerModel();
            nitrocalcar.setFertilizerType(FertilizerType.ORGANIC);
            nitrocalcar.setNameRo("NITROCALCAR (CAN) granulat");

            FertilizerExpenseListModel model3 = new FertilizerExpenseListModel();
            model3.setExpenseId(expense.getId());
            model3.setExpenseDate(expense.getExpenseDate());
            model3.setCropModel(griu);
            model3.setFertilizerModel(nitrocalcar);
            model3.setPhase("in perioada infloirtului");
            model3.setResult("Vau vau ce interesant");
            model3.setComments("Se putea si mai bine");
            model3.setCreatedAt(expense.getCreatedAt());
            model3.setCreatedBy("Jorik Vartanov");
            model3.setParcels(parcelModels);
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
