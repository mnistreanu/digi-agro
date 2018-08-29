package com.arobs.controller;

import com.arobs.model.PayloadModel;
import com.arobs.weather.entity.WeatherSnapshot;
import com.arobs.weather.provider.WeatherSnapshotProvider;
import com.arobs.weather.snapshot.WeatherSnapshotModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    private WeatherSnapshotProvider weatherSnapshotProvider;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<PayloadModel> getWeathers(@RequestParam("parcelId") Long parcelId,
                                                    @RequestParam("dateFrom") Date dateFrom,
                                                    @RequestParam("dateTo") Date dateTo) {
        parcelId = parcelId == null ? 1L : parcelId;
        dateFrom = dateFrom == null ? new Date(System.currentTimeMillis() - 1000*60*60*24*365) : dateFrom;
        dateTo = dateTo == null ? new Date() : dateTo;

        PayloadModel<WeatherSnapshotModel> payloadModel = new PayloadModel<>();

        try {
            List<WeatherSnapshot> weathers = weatherSnapshotProvider.find(parcelId, dateFrom, dateTo);
            if (!weathers.isEmpty()) {
                List<WeatherSnapshotModel> models = weathers.stream().map(WeatherSnapshotModel::new).collect(Collectors.toList());
                WeatherSnapshotModel[] payload = models.toArray(new WeatherSnapshotModel[models.size()]);
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


    @RequestMapping(value = "/history", method = RequestMethod.GET) // TODO de revazut
    public ResponseEntity<PayloadModel> findWeatherHistory() {
        PayloadModel<WeatherSnapshotModel> payloadModel = new PayloadModel<>();

        WeatherSnapshotModel[] wModels = new WeatherSnapshotModel[2];

        WeatherSnapshotModel wm0 = new WeatherSnapshotModel();
        wm0.setDt(new Date());
        wm0.setCountry("md");
//        wm0.setCountyId("ns");
        wm0.setTempMax(32.0);
        wm0.setTempMin(17.0);
        wm0.setWindSpeed(BigDecimal.valueOf(14.0));
        wm0.setWindDeg(BigDecimal.valueOf(187.002));
        wm0.setMain("clouds");
        wm0.setHumidity(89);
        wModels[0] = wm0;

        WeatherSnapshotModel wm1 = new WeatherSnapshotModel();
        wm1.setDt(new Date(System.currentTimeMillis() - 1000*60*60*4));
        wm1.setCountry("md");
//        wm1.setCountyId("ns");
        wm1.setTempMax(31.0);
        wm1.setTempMin(16.0);
        wm1.setWindSpeed(BigDecimal.valueOf(18.0));
        wm1.setWindDeg(BigDecimal.valueOf(80.002));
        wm1.setMain("clouds");
        wm1.setHumidity(91);
        wModels[1] = wm1;

        payloadModel.setPayload(wModels);
        return ResponseEntity.ok(payloadModel);
    }
}
