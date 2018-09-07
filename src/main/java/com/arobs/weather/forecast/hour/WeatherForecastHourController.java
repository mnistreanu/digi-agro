package com.arobs.weather.forecast.hour;

import com.arobs.weather.WeatherUtils;
import com.arobs.weather.entity.PayloadModel;
import com.arobs.weather.entity.WeatherForecastHour;
import com.arobs.weather.entity.WeatherForecastHourModel;

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
public class WeatherForecastHourController {

    @Autowired
    private WeatherForecastHourRepository weatherForecastHourRepository;

    /**
     * Returneaza toate articolele din istoria inregistrarilor meteo.
     * @return lista de istorii
     */
    @RequestMapping(value = "/histories", method = RequestMethod.GET)
    public ResponseEntity<PayloadModel<WeatherForecastHourModel>> getWeatherHistories() {
        PayloadModel<WeatherForecastHourModel> payloadModel = new PayloadModel<>();

        try {
            List<WeatherForecastHour> weatherForecastHour = weatherForecastHourRepository.findAll();
            if (!weatherForecastHour.isEmpty()) {
                List<WeatherForecastHourModel> models = weatherForecastHour.stream().map(WeatherForecastHourModel::new).collect(Collectors.toList());
                WeatherForecastHourModel[] payload = models.toArray(new WeatherForecastHourModel[models.size()]);
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
    public ResponseEntity<PayloadModel<WeatherForecastHourModel>> getWeatherForecastHourByLocation(@PathVariable("locationId") Integer locationId) {
        PayloadModel<WeatherForecastHourModel> payloadModel = new PayloadModel<>();

        try {
            List<WeatherForecastHour> weatherForecastHour = weatherForecastHourRepository.find(locationId);
            if (! weatherForecastHour.isEmpty()) {
                if (!weatherForecastHour.isEmpty()) {
                    List<WeatherForecastHourModel> models = weatherForecastHour.stream().map(WeatherForecastHourModel::new).collect(Collectors.toList());
                    WeatherForecastHourModel[] payload = models.toArray(new WeatherForecastHourModel[models.size()]);
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
    public ResponseEntity<PayloadModel<WeatherForecastHourModel>> getWeatherForecastHourInterval(@RequestParam("locationId") Integer locationId,
                                                    @RequestParam("dateFrom") @DateTimeFormat(pattern="yyyyMMdd") Date dateFrom,
                                                    @RequestParam("dateTo") @DateTimeFormat(pattern="yyyyMMdd") Date dateTo) {
        PayloadModel<WeatherForecastHourModel> payloadModel = new PayloadModel<>();

        try {
            List<WeatherForecastHour> weatherForecastHour = weatherForecastHourRepository.find(locationId, WeatherUtils.getUnixTime(dateFrom), WeatherUtils.getUnixTime(dateTo));
            if (!weatherForecastHour.isEmpty()) {
                List<WeatherForecastHourModel> models = weatherForecastHour.stream().map(WeatherForecastHourModel::new).collect(Collectors.toList());
                WeatherForecastHourModel[] payload = models.toArray(new WeatherForecastHourModel[models.size()]);
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
    public ResponseEntity<PayloadModel<WeatherForecastHourModel>> getWeatherForecastHourByDate(@PathVariable(value="date") @DateTimeFormat(pattern="yyyyMMdd") Date referenceDate) {
        PayloadModel<WeatherForecastHourModel> payloadModel = new PayloadModel<>();

        try {
            List<WeatherForecastHour> weatherForecastHour = weatherForecastHourRepository.find(WeatherUtils.getUnixTime(referenceDate));
            if (! weatherForecastHour.isEmpty()) {
                if (!weatherForecastHour.isEmpty()) {
                    List<WeatherForecastHourModel> models = weatherForecastHour.stream().map(WeatherForecastHourModel::new).collect(Collectors.toList());
                    WeatherForecastHourModel[] payload = models.toArray(new WeatherForecastHourModel[models.size()]);
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
