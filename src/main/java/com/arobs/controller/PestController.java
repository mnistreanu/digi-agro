package com.arobs.controller;

import com.arobs.entity.Pest;
import com.arobs.model.chemicals.PestModel;
import com.arobs.service.chemicals.PestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pest")
public class PestController {

    @Autowired
    private PestService pestService;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<PestModel>> getPestTypes(HttpSession session) {

        List<Pest> pests = pestService.find();
        List<PestModel> models = pests.stream().map(PestModel::new).collect(Collectors.toList());

        return ResponseEntity.ok(models);
    }
}
