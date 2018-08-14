package com.arobs.controller;

import com.arobs.entity.Forecast;
import com.arobs.model.PayloadModel;
import com.arobs.model.forecast.ForecastModel;
import com.arobs.service.CropCategoryService;
import com.arobs.service.CropService;
import com.arobs.service.CropVarietyService;
import com.arobs.service.ForecastService;
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
    private CropCategoryService cropCategoryService;
    @Autowired
    private CropService cropService;
    @Autowired
    private CropVarietyService cropVarietyService;

    @Autowired
    private ForecastService forecastService;


    @RequestMapping(value = "/active-forecasts", method = RequestMethod.GET)
    public ResponseEntity<PayloadModel> findForecasts(@RequestParam("tenantId") Long tenantId) {
        PayloadModel<ForecastModel> payloadModel = new PayloadModel<>();

        try {
            List<Forecast> forecasts = forecastService.findForecasts(tenantId == null ? 1 : tenantId, true);
            if (!forecasts.isEmpty()) {
                List<ForecastModel> models = forecasts.stream().map(ForecastModel::new).collect(Collectors.toList());
                ForecastModel[] payload = models.toArray(new ForecastModel[models.size()]);
                payloadModel.setStatus(PayloadModel.STATUS_SUCCESS);
                payloadModel.setPayload(payload);
            } else {
                payloadModel.setStatus(PayloadModel.STATUS_WARNING);
            }
        } catch (Exception e) {
            payloadModel.setStatus(PayloadModel.STATUS_ERROR);
            payloadModel.setMessage(e.getLocalizedMessage());
        }

        return ResponseEntity.ok(payloadModel);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public void create(@RequestBody ForecastModel model, HttpSession session) {
        Long tenantId = (Long) session.getAttribute("tenant");
        this.forecastService.create(model, tenantId);
    }


}
