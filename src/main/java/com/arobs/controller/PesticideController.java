package com.arobs.controller;

import com.arobs.entity.Pesticide;
import com.arobs.model.chemicals.PesticideModel;
import com.arobs.service.chemicals.PesticideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(value = "/{typeId}", method = RequestMethod.GET)
    public ResponseEntity<List<PesticideModel>> getPesticides(@PathVariable("typeId") final Long typeId) {

        List<Pesticide> organisms = pesticideService.find(typeId);
        List<PesticideModel> models = organisms.stream().map(PesticideModel::new).collect(Collectors.toList());

        return ResponseEntity.ok(models);
    }
}
