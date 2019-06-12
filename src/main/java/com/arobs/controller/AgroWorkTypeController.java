package com.arobs.controller;

import com.arobs.entity.agro.AgroWorkType;
import com.arobs.model.agrowork.AgroWorkTypeModel;
import com.arobs.service.agrowork.AgroWorkTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/work-type")
public class AgroWorkTypeController {

    @Autowired
    private AgroWorkTypeService agroWorkTypeService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<AgroWorkTypeModel>> getAgroWorkTypes(HttpSession session) {
        Long tenantId = (Long) session.getAttribute("tenant");

        List<AgroWorkType> workTypes = agroWorkTypeService.find(tenantId);
        List<AgroWorkTypeModel> models = workTypes.stream().map(AgroWorkTypeModel::new).collect(Collectors.toList());

        return ResponseEntity.ok(models);
    }
}
