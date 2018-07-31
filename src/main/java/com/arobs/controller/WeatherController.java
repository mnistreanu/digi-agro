package com.arobs.controller;

import com.arobs.entity.Weather;
import com.arobs.model.PayloadModel;
import com.arobs.model.WeatherModel;
import com.arobs.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<PayloadModel> getWeathers(@RequestParam("parcelId") Long parcelId,
                                                    @RequestParam("dateFrom") Date dateFrom,
                                                    @RequestParam("dateTo") Date dateTo) {
        parcelId = parcelId == null ? 1L : parcelId;
        dateFrom = dateFrom == null ? new Date(System.currentTimeMillis() - 1000*60*60*24*365) : dateFrom;
        dateTo = dateTo == null ? new Date() : dateTo;

        PayloadModel<WeatherModel> payloadModel = new PayloadModel<>();

        try {
            List<Weather> weathers = weatherService.find(parcelId, dateFrom, dateTo);
            if (!weathers.isEmpty()) {
                List<WeatherModel> models = weathers.stream().map(WeatherModel::new).collect(Collectors.toList());
                WeatherModel[] payload = models.toArray(new WeatherModel[models.size()]);
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

}
