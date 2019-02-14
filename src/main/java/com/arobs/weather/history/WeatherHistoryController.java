package com.arobs.weather.history;

import com.arobs.weather.WeatherUtils;
import com.arobs.weather.entity.PayloadModel;
import com.arobs.weather.entity.WeatherHistory;
import com.arobs.weather.entity.WeatherHistoryModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/weather")
public class WeatherHistoryController {

    @Autowired
    private WeatherHistoryRepository weatherHistoryRepository;

    /**
     * Returneaza toate articolele din istoria inregistrarilor meteo.
     * @return lista de istorii
     */
    @RequestMapping(value = "/histories", method = RequestMethod.GET)
    @Deprecated
    public ResponseEntity<PayloadModel<WeatherHistoryModel>> getWeatherHistories() {
        PayloadModel<WeatherHistoryModel> payloadModel = new PayloadModel<>();

        try {
            List<WeatherHistory> weatherHistory = weatherHistoryRepository.findAll();
            if (!weatherHistory.isEmpty()) {
                List<WeatherHistoryModel> models = weatherHistory.stream().map(WeatherHistoryModel::new).collect(Collectors.toList());
                WeatherHistoryModel[] payload = models.toArray(new WeatherHistoryModel[models.size()]);
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
     * Returneaza lista tuturor istoriilor meteo pentru localitatea specificata<br/>
     * Exemplu URL: host:port/weather/history_location/617255
     * @param locationId - CITY_ID din Open weather
     * @return lista de istorii
     */
    @RequestMapping(value = "/history_location/{locationId}", method = RequestMethod.GET)
    @Deprecated
    public ResponseEntity<PayloadModel<WeatherHistoryModel>> getWeatherHistoryByLocation(@PathVariable("locationId") Integer locationId) {
        PayloadModel<WeatherHistoryModel> payloadModel = new PayloadModel<>();

        try {
            List<WeatherHistory> weatherHistory = weatherHistoryRepository.find(locationId);
            if (! weatherHistory.isEmpty()) {
                if (!weatherHistory.isEmpty()) {
                    List<WeatherHistoryModel> models = weatherHistory.stream().map(WeatherHistoryModel::new).collect(Collectors.toList());
                    WeatherHistoryModel[] payload = models.toArray(new WeatherHistoryModel[models.size()]);
                    payloadModel.setStatus(PayloadModel.STATUS_SUCCESS);
                    payloadModel.setPayload(payload);
                } else {
                    payloadModel.setStatus(PayloadModel.STATUS_WARNING);
                }
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
     * Returneaza lista de istorii meteo pentru localitatea specificata din intervalul de timp specificat  
     * @param locationId - ID localitate in conformitate cu CITY_ID din Open weather.<br/>
     * Exemplu URL: host:port/weather/history_interval?locationId=684038&dateFrom=20180901&dateTo=20180902 
     * @param dateFrom - inceput de interval in format "yyyyMMdd"
     * @param dateTo - sfirsit de interval in format "yyyyMMdd"
     * @return lista de istorii.
     */
    @RequestMapping(value = "/history_interval", method = RequestMethod.GET)
    public ResponseEntity<PayloadModel<WeatherHistoryModel>> getWeatherHistoryInterval(@RequestParam("locationId") Integer locationId,
                                                    @RequestParam("dateFrom") @DateTimeFormat(pattern="yyyyMMdd") Date dateFrom,
                                                    @RequestParam("dateTo") @DateTimeFormat(pattern="yyyyMMdd") Date dateTo) {
        PayloadModel<WeatherHistoryModel> payloadModel = new PayloadModel<>();

        try {
            List<WeatherHistory> weatherHistory = weatherHistoryRepository.find(locationId, WeatherUtils.getUnixTime(dateFrom), WeatherUtils.getUnixTime(dateTo));
            if (!weatherHistory.isEmpty()) {
                List<WeatherHistoryModel> models = weatherHistory.stream().map(WeatherHistoryModel::new).collect(Collectors.toList());
                WeatherHistoryModel[] payload = models.toArray(new WeatherHistoryModel[models.size()]);
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
     * Returneaza lista tuturor istoriilor meteo pentru data specificata<br/>
     * Exemplu URL: host:port/weather/history_date/20180902 
     * @param referenceDate - data specificata in formt "yyyyMMdd"
     * @return lista de observatii meteo
     */
    @RequestMapping(value = "/history_date/{date}", method = RequestMethod.GET)
    public ResponseEntity<PayloadModel<WeatherHistoryModel>> getWeatherHistoryByDate(@PathVariable(value="date") @DateTimeFormat(pattern="yyyyMMdd") Date referenceDate) {
        PayloadModel<WeatherHistoryModel> payloadModel = new PayloadModel<>();

        try {
            List<WeatherHistory> weatherHistory = weatherHistoryRepository.find(referenceDate);
            if (! weatherHistory.isEmpty()) {
                if (!weatherHistory.isEmpty()) {
                    List<WeatherHistoryModel> models = weatherHistory.stream().map(WeatherHistoryModel::new).collect(Collectors.toList());
                    WeatherHistoryModel[] payload = models.toArray(new WeatherHistoryModel[models.size()]);
                    payloadModel.setStatus(PayloadModel.STATUS_SUCCESS);
                    payloadModel.setPayload(payload);
                } else {
                    payloadModel.setStatus(PayloadModel.STATUS_WARNING);
                }
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
