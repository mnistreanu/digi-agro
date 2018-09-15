package com.arobs.controller;

import com.arobs.entity.AgroWorkType;
import com.arobs.entity.HarmfulOrganism;
import com.arobs.model.agrowork.AgroWorkTypeModel;
import com.arobs.model.chemicals.HarmfulOrganismModel;
import com.arobs.service.chemicals.HarmfulOrganismService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/harmful-organisms")
public class HarmfulOrganismController {

    @Autowired
    private HarmfulOrganismService harmfulOrganismService;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<HarmfulOrganismModel>> getHarmfulOrganismTypes(HttpSession session) {

        List<HarmfulOrganism> organisms = harmfulOrganismService.find();
        List<HarmfulOrganismModel> models = organisms.stream().map(HarmfulOrganismModel::new).collect(Collectors.toList());

        return ResponseEntity.ok(models);
    }
}
