package com.arobs.controller;

import com.arobs.entity.Fertilizer;
import com.arobs.enums.FertilizerType;
import com.arobs.model.chemicals.FertilizerApplicationModel;
import com.arobs.model.chemicals.FertilizerModel;
import com.arobs.service.chemicals.FertilizerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/fertilizer-application")
public class FertilizerApplicationController {

    @Autowired
    private FertilizerService fertilizerService;

//
//    @RequestMapping(value = "/", method = RequestMethod.GET)
//    public ResponseEntity<List<FertilizerModel>> getFertilizers() {
//
//        List<Fertilizer> fertilizers = fertilizerService.find();
//        List<FertilizerModel> models = fertilizers.stream().map(FertilizerModel::new).collect(Collectors.toList());
//
//        return ResponseEntity.ok(models);
//    }
//
//    @RequestMapping(value = "/by-type/{type}", method = RequestMethod.GET)
//    public ResponseEntity<List<FertilizerModel>> getFertilizers(@PathVariable("type") FertilizerType type) {
//
//        List<Fertilizer> fertilizers = fertilizerService.find(type);
//        List<FertilizerModel> models = fertilizers.stream().map(FertilizerModel::new).collect(Collectors.toList());
//
//        return ResponseEntity.ok(models);
//    }
//
//
//    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
//    public ResponseEntity<FertilizerModel> getModel(@PathVariable Long id) {
//        Fertilizer item = fertilizerService.findOne(id);
//        return ResponseEntity.ok(new FertilizerModel(item));
//    }
//
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
//    @RequestMapping(value = "/", method = RequestMethod.POST)
//    public ResponseEntity<FertilizerModel> save(@RequestBody FertilizerModel model, HttpSession session) {
//        Long tenantId = (Long) session.getAttribute("tenant");
//        return ResponseEntity.ok(new FertilizerModel(fertilizerService.save(model)));
//    }
//
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
//    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//    public void remove(@PathVariable Long id) {
//        fertilizerService.remove(id);
//    }

    @RequestMapping(value = "/{parcelId}/{harvestYear}", method = RequestMethod.GET)
    public ResponseEntity<List<FertilizerApplicationModel>> getFertilizerApplications(@PathVariable("parcelId") Long parcelId,
                                                                           @PathVariable("harvestYear") Long harvestYear) {
        List<FertilizerApplicationModel> models = new ArrayList<>();

        FertilizerApplicationModel m1 = new FertilizerApplicationModel();
        m1.setId(1L);
        m1.setParcelId(1L);
        m1.setApplicationDate(Calendar.getInstance().getTime());
        m1.setComments("Totul este bine");
        m1.setPlacementType(2);
        m1.setFertilizerType(3);
        m1.setFertilizerId(10L);
        m1.setFertilizerNameRo("Produs ROM");
        m1.setFertilizerNameRu("Produs RUS");
        m1.setTonePrice(BigDecimal.valueOf(123.45));
        m1.setFertilizedArea(98.6);
        m1.setRate(1.5);
        m1.setRateUnitOfMeasure("L/t");
        m1.setHectareCost(BigDecimal.valueOf(17.5));
        models.add(m1);

        FertilizerApplicationModel m2 = new FertilizerApplicationModel();
        m2.setId(1L);
        m2.setParcelId(1L);
        m2.setApplicationDate(Calendar.getInstance().getTime());
        m2.setComments("Era vint din sud");
        m2.setPlacementType(3);
        m2.setFertilizerType(2);
        m2.setFertilizerId(11L);
        m2.setFertilizerNameRo("Ametist ROM");
        m2.setFertilizerNameRu("Ametist RUS");
        m2.setTonePrice(BigDecimal.valueOf(45.67));
        m2.setFertilizedArea(106.5);
        m2.setRate(1.9);
        m2.setRateUnitOfMeasure("L/t");
        m2.setHectareCost(BigDecimal.valueOf(23.2));
        models.add(m2);

        return ResponseEntity.ok(models);
    }


}
