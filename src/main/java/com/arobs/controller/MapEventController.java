package com.arobs.controller;

import com.arobs.model.MapEventModel;
import com.arobs.model.UpdateFieldModel;
import com.arobs.service.MapEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/map-event")
public class MapEventController {

    @Autowired
    private MapEventService mapEventService;

    @RequestMapping(value = "/findByMachineIdentifierAndUsername", method = RequestMethod.GET)
    public ResponseEntity<List<MapEventModel>> findByMachineIdentifierAndUsername(
            @RequestParam("machineIdentifier") String machineIdentifier,
            @RequestParam("username") String username) {
        return ResponseEntity.ok(mapEventService.findByMachineIdentifierAndUsername(machineIdentifier, username));
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<MapEventModel> save(@RequestBody MapEventModel model) {
        return ResponseEntity.ok(new MapEventModel(mapEventService.save(model)));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void remove(@PathVariable Long id) {
        mapEventService.remove(id);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public void update(@RequestBody UpdateFieldModel model) {

        mapEventService.update(model);
    }

}
