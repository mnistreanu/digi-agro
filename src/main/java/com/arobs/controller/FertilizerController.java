package com.arobs.controller;

import com.arobs.entity.Fertilizer;
import com.arobs.model.chemicals.FertilizerModel;
import com.arobs.service.chemicals.FertilizerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/fertilizer")
public class FertilizerController {

    @Autowired
    private FertilizerService fertilizerService;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<FertilizerModel>> getFertilizers() {

        List<Fertilizer> fertilizers = fertilizerService.find();
        List<FertilizerModel> models = fertilizers.stream().map(FertilizerModel::new).collect(Collectors.toList());

        return ResponseEntity.ok(models);
    }

    @RequestMapping(value = "/{typeId}", method = RequestMethod.GET)
    public ResponseEntity<List<FertilizerModel>> getFertilizers(@PathVariable("typeId") final Long typeId) {

        List<Fertilizer> organisms = fertilizerService.find(typeId);
        List<FertilizerModel> models = organisms.stream().map(FertilizerModel::new).collect(Collectors.toList());

        return ResponseEntity.ok(models);
    }
}
