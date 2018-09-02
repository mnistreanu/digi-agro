package com.arobs.weather.snapshot;

import com.arobs.weather.entity.PayloadModel;
import com.arobs.weather.entity.WeatherSnapshot;
import com.arobs.weather.entity.WeatherSnapshotModel;
import com.arobs.weather.provider.WeatherSnapshotProvider;

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
public class WeatherSnapshotController {

    @Autowired
    private WeatherSnapshotProvider weatherSnapshotProvider;

    @RequestMapping(value = "/snapshots", method = RequestMethod.GET)
    public ResponseEntity<PayloadModel<WeatherSnapshotModel>> getWeatherSnapshots() {
        PayloadModel<WeatherSnapshotModel> payloadModel = new PayloadModel<>();

        try {
            List<WeatherSnapshot> weathers = weatherSnapshotProvider.findAll();
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

    @RequestMapping(value = "/snapshot0", method = RequestMethod.GET)
    public ResponseEntity<PayloadModel<WeatherSnapshotModel>> getWeatherSnaphotsFiltered(@RequestParam("parcelId") Long parcelId,
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

    @RequestMapping(value = "/snapshot/{locationId}", method = RequestMethod.GET)
    public ResponseEntity<PayloadModel<WeatherSnapshotModel>> getWeatherSnapshot(@RequestParam("locationId") Long locationId) {
        locationId = locationId == null ? 1L : locationId;
        PayloadModel<WeatherSnapshotModel> payloadModel = new PayloadModel<>();
        try {
            WeatherSnapshot weatherSnaposhot = weatherSnapshotProvider.findOne(locationId);
            if (weatherSnaposhot != null) {
                WeatherSnapshotModel model = new WeatherSnapshotModel(weatherSnaposhot);
                WeatherSnapshotModel[] payload = {model};
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

    @RequestMapping(value = "/snapshot", method = RequestMethod.GET)
    public ResponseEntity<PayloadModel<WeatherSnapshotModel>> getWeatherSnapshotCurrent(@RequestParam("lat") Double lat, @RequestParam("lat") Double lon) {
        PayloadModel<WeatherSnapshotModel> payloadModel = new PayloadModel<>();
        try {
            WeatherSnapshot weatherSnaposhot = weatherSnapshotProvider.getByCoord(lat, lon);
            if (weatherSnaposhot != null) {
                WeatherSnapshotModel model = new WeatherSnapshotModel(weatherSnaposhot);
                WeatherSnapshotModel[] payload = {model};
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

