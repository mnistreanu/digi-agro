package com.arobs.controller;

import com.arobs.entity.MachineTelemetry;
import com.arobs.model.UpdateFieldModel;
import com.arobs.model.telemetry.MachineTelemetryModel;
import com.arobs.service.AuthService;
import com.arobs.service.TelemetryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/machine-telemetry")
public class MachineTelemetryController {

    @Autowired
    private TelemetryService telemetryService;
    @Autowired
    private AuthService authService;


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<MachineTelemetryModel> getModel(@PathVariable Long id) {
        return ResponseEntity.ok(new MachineTelemetryModel(telemetryService.findOne(id)));
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<MachineTelemetryModel>> find(@RequestParam("machineId") Long machineId) {
        Long userId = authService.getCurrentUser().getId();
        List<MachineTelemetry> telemetries = telemetryService.find(machineId, userId);
        List<MachineTelemetryModel> models = telemetries.stream().map(MachineTelemetryModel::new).collect(Collectors.toList());
        return ResponseEntity.ok(models);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<MachineTelemetryModel> save(@RequestBody MachineTelemetryModel model) {
        Long userId = authService.getCurrentUser().getId();
        return ResponseEntity.ok(new MachineTelemetryModel(telemetryService.save(model, userId)));
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
