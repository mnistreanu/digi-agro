package com.arobs.controller;

import com.arobs.entity.Forecast;
import com.arobs.model.PayloadModel;
import com.arobs.model.forecast.ForecastModel;
import com.arobs.service.forecast.ForecastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/forecast")
public class ForecastController {

    @Autowired
    private ForecastService forecastService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<ForecastModel>> find(HttpSession session) {
        Long tenant = (Long) session.getAttribute("tenant");
        List<Forecast> forecasts = forecastService.find(tenant, true);
        List<ForecastModel> models = forecasts.stream().map(ForecastModel::new).collect(Collectors.toList());
        return ResponseEntity.ok(models);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ForecastModel> getModel(@PathVariable("id") Long id) {
        ForecastModel model = forecastService.getModel(id);
        return ResponseEntity.ok(model);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void remove(@PathVariable("id") Long id) {
        forecastService.remove(id);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<ForecastModel> create(@RequestBody ForecastModel model, HttpSession session) {
        Long tenantId = (Long) session.getAttribute("tenant");
        Forecast forecast = this.forecastService.create(model, tenantId);
        model = forecastService.getModel(forecast.getId());
        return ResponseEntity.ok(model);
    }


}
