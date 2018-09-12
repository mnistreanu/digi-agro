package com.arobs.weather.forecast.hour;

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

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/weather")
public class WeatherForecastHourController {

    @Autowired
    private WeatherForecastHourRepository weatherForecastHourRepository;

    /**
     * Returneaza toate prognozele meteo 5/3 inregistrate in baza de date.
     * @return lista de prognoze
     */
    @RequestMapping(value = "/forecasts/hour", method = RequestMethod.GET)
    public ResponseEntity<PayloadModel<WeatherForecastHourModel>> getWeatherForecastsHour() {
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
     * Returneaza lista tuturor prognozelor meteo 5/3 pentru localitatea specificata<br/>
     * Exemplu URL: host:port/weather/forecasts/hour/617255
     * @param locationId - CITY_ID din Open weather
     * @return lista de prognoze
     */
    @RequestMapping(value = "/forecasts/hour/{locationId}", method = RequestMethod.GET)
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
     * Returneaza lista de prognoze meteo 5/3 pentru localitatea specificata din intervalul de timp specificat  
     * @param locationId - ID localitate in conformitate cu CITY_ID din Open weather.<br/>
     * Exemplu URL: host:port/weather/forecasts/hour/interval?locationId=665849&dateFrom=20180801&dateTo=20180824 
     * @param dateFrom - inceput de interval in format "yyyyMMdd"
     * @param dateTo - sfirsit de interval in format "yyyyMMdd"
     * @return lista de prognoze.
     */
    @RequestMapping(value = "/forecasts/hour/interval", method = RequestMethod.GET)
    public ResponseEntity<PayloadModel<WeatherForecastHourModel>> getWeatherForecastHourInterval(@RequestParam("locationId") Integer locationId,
                                                    @RequestParam("dateFrom") @DateTimeFormat(pattern="yyyyMMdd") Date dateFrom,
                                                    @RequestParam("dateTo") @DateTimeFormat(pattern="yyyyMMdd") Date dateTo) {
        PayloadModel<WeatherForecastHourModel> payloadModel = new PayloadModel<>();

        try {
            List<WeatherForecastHour> weatherForecastHour = weatherForecastHourRepository.find(locationId, dateFrom, dateTo);
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
     * Returneaza lista tuturor prognozelor  meteo 5/3 pentru data specificata<br/>
     * Exemplu URL: host:port/weather/forecast/hour/20180824 
     * @param referenceDate - data specificata in formt "yyyyMMdd"
     * @return lista de prognoze meteo
     */
    @RequestMapping(value = "/forecast/hour/{date}", method = RequestMethod.GET)
    public ResponseEntity<PayloadModel<WeatherForecastHourModel>> getWeatherForecastHourByDate(@PathVariable(value="date") @DateTimeFormat(pattern="yyyyMMdd") Date referenceDate) {
        PayloadModel<WeatherForecastHourModel> payloadModel = new PayloadModel<>();
        Calendar referenceCalendar = Calendar.getInstance();
        referenceCalendar.setTime(referenceDate);
        try {
            List<WeatherForecastHour> weatherForecastHour = weatherForecastHourRepository.find(referenceCalendar.get(Calendar.YEAR), referenceCalendar.get(Calendar.MONTH) + 1, referenceCalendar.get(Calendar.DATE), referenceCalendar.get(Calendar.HOUR));
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
