package com.arobs.controller;

import com.arobs.entity.Telemetry;
import com.arobs.model.TelemetryModel;
import com.arobs.service.TelemetryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/telemetry")
public class TelemetryController {

    @Autowired
    private TelemetryService telemetryService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<TelemetryModel>> getModels() {

        List<Telemetry> items = telemetryService.findAll();
        List<TelemetryModel> models = items.stream().map(TelemetryModel::new).collect(Collectors.toList());

        return ResponseEntity.ok(models);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<TelemetryModel> getModel(@PathVariable Long id) {
        return ResponseEntity.ok(telemetryService.findModelById(id));
    }

    @RequestMapping(value = "/findByMachineIdentifierAndUsername", method = RequestMethod.GET)
    public ResponseEntity<List<TelemetryModel>> findByMachineIdentifierAndUsername(
            @RequestParam("machineIdentifier") String machineIdentifier,
            @RequestParam("username") String username) {
        return ResponseEntity.ok(telemetryService.findByMachineIdentifierAndUsername(machineIdentifier, username));
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<TelemetryModel> save(@RequestBody TelemetryModel model) {
        return ResponseEntity.ok(new TelemetryModel(telemetryService.save(model)));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void remove(@PathVariable Long id) {
        telemetryService.remove(id);
    }

    @RequestMapping(value = "/updateCoordinate", method = RequestMethod.GET)
    public void updateCoordinate(@RequestParam("id") Long id,
                                 @RequestParam("field") String field,
                                 @RequestParam("value") BigDecimal value) {

        telemetryService.updateCoordinate(id, field, value);
    }

}
