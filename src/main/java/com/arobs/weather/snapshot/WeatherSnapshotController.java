package com.arobs.weather.snapshot;

import com.arobs.weather.entity.PayloadModel;
import com.arobs.weather.entity.WeatherSnapshot;
import com.arobs.weather.entity.WeatherSnapshotModel;
import com.arobs.weather.provider.WeatherSnapshotProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/weather")
public class WeatherSnapshotController {

    @Autowired
    private WeatherSnapshotProvider weatherSnapshotProvider;

    /**
     * Returneaza toate observatiile meteo din ziua curenta
     * @return lista de observatii curente 
     */
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

    /**
     * Returneaza lisat observatiilor meteo curente in localitatea specificata
     * @param locationId - CITY_ID din Open weather
     * @return lista de observatii din ziua curenta
     */
    @RequestMapping(value = "/snapshot/{locationId}", method = RequestMethod.GET)
    public ResponseEntity<PayloadModel<WeatherSnapshotModel>> getWeatherSnapshotByLocation(@PathVariable("locationId") Integer locationId) {
        locationId = locationId == null ? 1 : locationId;
        PayloadModel<WeatherSnapshotModel> payloadModel = new PayloadModel<>();
        try {
            List<WeatherSnapshot> weatherSnapshots = weatherSnapshotProvider.find(locationId);
            if (!weatherSnapshots.isEmpty()) {
                List<WeatherSnapshotModel> models = weatherSnapshots.stream().map(WeatherSnapshotModel::new).collect(Collectors.toList());
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

    /**
     * Returneaza lista de obsevatii meteo curent pentru localitatea specificata in intervalu specificat
     * @param locationId - CITY_ID al localitatii conform Open weather
     * @param dateFrom - inceput de interval in format "HH:mm"
     * @param dateTo - sfirsit de interval in format "HH:mm"
     * @return lista de observatii meteo curente
     */
    @RequestMapping(value = "/snapshot_interval", method = RequestMethod.GET)
    public ResponseEntity<PayloadModel<WeatherSnapshotModel>> getWeatherSnaphotsInterval(@RequestParam("locationId") Integer locationId,
                                                    @RequestParam("dateFrom") @DateTimeFormat(pattern="HH:mm") Date dateFrom,
                                                    @RequestParam("dateTo") @DateTimeFormat(pattern="HH:mm") Date dateTo) {
    	Calendar now = Calendar.getInstance();
    	Calendar from = Calendar.getInstance();
    	from.clear();
    	from.setTime(dateFrom);
    	from.set(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DATE));
    	
    	Calendar to = Calendar.getInstance();
    	to.clear();
    	to.setTime(dateTo);
    	to.set(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DATE));

        PayloadModel<WeatherSnapshotModel> payloadModel = new PayloadModel<>();

        try {
            List<WeatherSnapshot> weatherSnapshots = weatherSnapshotProvider.find(locationId, from.getTime(), to.getTime());
            if (!weatherSnapshots.isEmpty()) {
                List<WeatherSnapshotModel> models = weatherSnapshots.stream().map(WeatherSnapshotModel::new).collect(Collectors.toList());
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

    /**
     * Returneaza starea meteo curenta in punctul cu coordonatele specificate. Informatia se extrage de pe site-ul openweather.
     * @param lat -  latitudine
     * @param lon - longitudine
     * @return starea meteo curent
     */
    @RequestMapping(value = "/snapshot_coord", method = RequestMethod.GET)
    public ResponseEntity<PayloadModel<WeatherSnapshotModel>> getWeatherSnapshotCurrent(@RequestParam("lat") Double lat, @RequestParam("lon") Double lon) {
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

