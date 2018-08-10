package com.arobs.controller;

import com.arobs.model.forecast.ForecastModel;
import com.arobs.service.CropCategoryService;
import com.arobs.service.CropService;
import com.arobs.service.CropVarietyService;
import com.arobs.service.ForecastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/forecast")
public class ForecastController {

    @Autowired
    private CropCategoryService cropCategoryService;
    @Autowired
    private CropService cropService;
    @Autowired
    private CropVarietyService cropVarietyService;

    @Autowired
    private ForecastService forecastService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public void create(@RequestBody ForecastModel model, HttpSession session) {
        Long tenantId = (Long) session.getAttribute("tenant");
        this.forecastService.create(model, tenantId);
    }


}
