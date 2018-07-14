package com.arobs.controller;

import com.arobs.model.UpdateFieldModel;
import com.arobs.model.telemetry.TelemetryModel;
import com.arobs.service.TelemetryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/telemetry")
public class TelemetryController {

    @Autowired
    private TelemetryService telemetryService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<TelemetryModel> getModel(@PathVariable Long id) {
        return ResponseEntity.ok(telemetryService.findModelById(id));
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
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

    @RequestMapping(value = "/coordinates", method = RequestMethod.POST)
    public void updateCoordinate(@RequestBody UpdateFieldModel model) {
        telemetryService.updateCoordinate(model);
    }

}
