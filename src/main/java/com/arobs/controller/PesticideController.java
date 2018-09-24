package com.arobs.controller;

import com.arobs.entity.Pesticide;
import com.arobs.model.chemicals.PesticideModel;
import com.arobs.model.expense.MachineryExpenseModel;
import com.arobs.service.chemicals.PesticideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pesticide")
public class PesticideController {

    @Autowired
    private PesticideService pesticideService;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<PesticideModel>> getPesticides() {

        List<Pesticide> organisms = pesticideService.find();
        List<PesticideModel> models = organisms.stream().map(PesticideModel::new).collect(Collectors.toList());

        return ResponseEntity.ok(models);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<PesticideModel> getModel(@PathVariable Long id) {
        PesticideModel pesticideModel = pesticideService.findOneModel(id);
        return ResponseEntity.ok(pesticideModel);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<Pesticide> save(@RequestBody PesticideModel model,
                                                      HttpSession session) {
        Long tenant = (Long) session.getAttribute("tenant");
        return ResponseEntity.ok(pesticideService.save(model));
    }

//    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//    public void remove(@PathVariable Long id) {
//        pesticideService.remove(id);
//    }



//    @RequestMapping(value = "/{typeId}", method = RequestMethod.GET)
//    public ResponseEntity<List<PesticideModel>> getPesticides(@PathVariable("typeId") final Long typeId) {
//
//        List<Pesticide> organisms = pesticideService.find(typeId);
//        List<PesticideModel> models = organisms.stream().map(PesticideModel::new).collect(Collectors.toList());
//
//        return ResponseEntity.ok(models);
//    }
}
